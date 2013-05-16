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


import java.net.URL;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Book {

	private String title;
	private String subtitle;
	private String author;
	private String year;
	private String doi;
	private String printIsbn;
	private String onlineIsbn;
	private String url;
	private TreeMap<String, URL> chapters = new TreeMap<>();

	public TreeMap<String, URL> getChapters() {
		return chapters;
	}

	public void setChapters(TreeMap<String, URL> chapters) {
		this.chapters = chapters;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getAuthor() {
		return author;
	}

	public String getYear() {
		return year;
	}

	public String getDoi() {
		return doi;
	}

	public String getPrintIsbn() {
		return printIsbn;
	}

	public String getOnlineIsbn() {
		return onlineIsbn;
	}

	public String getUrl() {
		return url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public void setPrintIsbn(String printIsbn) {
		this.printIsbn = printIsbn;
	}

	public void setOnlineIsbn(String onlineIsbn) {
		this.onlineIsbn = onlineIsbn;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPdfTitle() {
		return (author + "-" + title).replace(" ", "_");
	}

	public void print() {
		System.out.println(author);
		System.out.println(title + " - " + subtitle + " (" + year + ")");
		System.out.println("  " + doi);
		System.out.println("  " + printIsbn);
		System.out.println("  " + onlineIsbn);
		System.out.println("  " + url);

		for (Entry<String, URL> chapter : chapters.entrySet()) {
			System.out.println(chapter.getKey() + " -> " + chapter.getValue());
		}

	}
}
