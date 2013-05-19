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


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;


public class Pdf {
	private Book book;
	private TreeMap<String, URL> chapters = new TreeMap<>();
	private File saveFolder;
	private ArrayList<File> src = new ArrayList<>();
	private File tmpDir;
	
	public Pdf(Book book, File saveFolder) {
		this.book = book;
		this.chapters = book.getChapters();
		this.saveFolder = saveFolder;
		try {
			tmpDir = Files.createTempDirectory(null).toFile();
			tmpDir.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

	public void download(Entry<String, URL> chapter) {
		try {
			URL url = chapter.getValue();
			url.openConnection();
			InputStream reader = url.openStream();
			
			File tmpPdfFile = File.createTempFile(chapter.getKey(), ".pdf", tmpDir);
			tmpPdfFile.deleteOnExit();
			
			FileOutputStream writer = new FileOutputStream(tmpPdfFile);
			byte[] buffer = new byte[131072];
			int bytesRead = 0;

			while ((bytesRead = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, bytesRead);
				buffer = new byte[131071];
			}
			writer.close();
			reader.close();

			src.add(tmpPdfFile);
		} catch (MalformedURLException e) {
			System.out.println(" failed.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(" failed.");
			e.printStackTrace();
		}
	}
	
	public void downloadAll() {
		int count = 1;
		System.out.println("Start downloading…");
		for (Entry<String, URL> chapter : chapters.entrySet()) {
			System.out.print(":: " + count++ + "/" + chapters.size());
			download(chapter);
			System.out.println(" succeed.");
		}
	}

	public void create() throws DocumentException, IOException {
		// TODO: refactor to merge(), mergeAll() 
		String title = book.getPdfTitle() + ".pdf";
		File saveFile = new File(saveFolder, title);

		int count = 1;
		while (saveFile.exists()) {
			title = book.getPdfTitle() + "_" + count++ + ".pdf";
			saveFile = new File(saveFolder, title);
		}
		book.setInfo("saveFile", saveFile.toString());

		Document document = new Document();
		PdfCopy destPdf = new PdfCopy(document, new FileOutputStream(saveFile));
		document.open();
		PdfReader reader;
		int page_offset = 0;
		int n;
		ArrayList<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> tmp;

		count = 1;
		System.out.println("Start mergin…");
		for (File srcPdf : src) {

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			
			System.out.print(":: " + count++ + "/" + src.size());
			reader = new PdfReader(srcPdf.toString());

			tmp = SimpleBookmark.getBookmark(reader);
			SimpleBookmark.shiftPageNumbers(tmp, page_offset, null);
			bookmarks.addAll(tmp);

			n = reader.getNumberOfPages();
			page_offset += n;
			for (int page = 0; page < n;) {
				destPdf.addPage(destPdf.getImportedPage(reader, ++page));
			}
			destPdf.freeReader(reader);
			reader.close();
			System.out.println(" succeed.");

		}
		destPdf.setOutlines(bookmarks);
		
		if (book.getInfo("author") != null) document.addAuthor(book.getInfo("author"));
		if (book.getInfo("title") != null) document.addTitle(book.getInfo("title"));
		if (book.getInfo("subtitle") != null) document.addSubject(book.getInfo("subtitle"));
		document.close();

		System.out.println("Merge complete. Saved to " + saveFile);
	}
	
	public void deleteTemp() {
		for (File srcPdf : src) {
			if (srcPdf.exists()) srcPdf.delete();
		}
		if (tmpDir.exists()) tmpDir.delete();
	}
}
