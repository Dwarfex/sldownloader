/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     sgelb - initial API and implementation
 ******************************************************************************/
package com.github.sgelb.springerlinkdownloader.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
	private String url;
	private String urlBase;
	private Book book;
	private Document doc = null;
	private TreeMap<String, URL> chapters = new TreeMap<>();

	public Parser(String url, Book book) {
		System.setProperty("java.net.useSystemProxies", "true");
		this.book = book;
		this.url = url.replaceAll("/page/\\d+$", "");
		this.urlBase = url + "/page/";
	}

	public void run() {
		parseHtml();
		setBookData();
	}

	public void parseHtml() {

		try {
			doc = Jsoup.connect(url).timeout(5000).get();
		} catch (HttpStatusException e) {
			System.out.println("Error: " + e.getStatusCode());
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		if (doc.getElementsByClass("access-link") != null) {
			System.out
					.println("You have no access to this book. Are you connecting from the right network?");
			System.exit(-1);
		}

		Integer totalPages = 1;
		try {
			totalPages = Integer.parseInt(doc
					.getElementsByClass("number-of-pages").first().text());
		} catch (Exception e) {
		}

		getChapters(chapters);

		for (int i = 2; i <= totalPages; i++) {
			url = urlBase + i;
			try {
				doc = Jsoup.connect(url).timeout(5000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			chapters.putAll(getChapters(chapters));
		}
	}

	public void setBookData() {
		Element summary = doc.getElementsByClass("summary").first();
		HashMap<String, String> cssClasses = new HashMap<>();
		// <key name, css class name>
		cssClasses.put("author", "person");
		cssClasses.put("title", "abstract-about-title");
		cssClasses.put("subtitle", "abstract-about-book-subtitle");
		cssClasses.put("year", "abstract-about-book-chapter-copyright-year");
		cssClasses.put("doi", "abstract-about-book-chapter-doi");
		cssClasses.put("printIsbn", "abstract-about-book-print-isbn");
		cssClasses.put("onlineIsbn", "abstract-about-book-online-isbn");

		for (Entry<String, String> cssClass : cssClasses.entrySet()) {
			// FIXME: get all authors
			String text = summary.getElementsByClass(cssClass.getValue())
					.first().text();
			if (!text.isEmpty()) {
				book.setInfo(cssClass.getKey(), text);
			}
		}

		book.setInfo("url", url);
		book.setChapters(chapters);
	}

	public TreeMap<String, URL> getChapters(TreeMap<String, URL> chapters) {
		Elements items = doc.getElementsByClass("toc-item");

		for (Element item : items) {
			String pageString = item.getElementsByClass("page-range").first()
					.text();
			Matcher matcher = Pattern.compile("\\d+|[MDCLXVI]").matcher(
					pageString);

			String page = null;
			int count = 1;
			if (matcher.find()) {
				try { // decimal page numbers
					int factor = 100;
					page = String.format("%06d",
							Integer.parseInt(matcher.group()) * factor);
				} catch (NumberFormatException e) { // roman page numbers
					page = String.format("%06d", count++);
				}
			}

			URL pdfUrl = null;
			try {
				pdfUrl = new URL(item.select("a[href$=.pdf]").first()
						.attr("abs:href"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			chapters.put(page, pdfUrl);
		}
		return chapters;
	}
}
