/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.github.sgelb.sldownloader.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

	public Pdf(Book book, File saveFolder) throws IOException {
		this.book = book;
		this.chapters = book.getChapters();
		this.saveFolder = saveFolder;
		tmpDir = Files.createTempDirectory(null).toFile();
		tmpDir.deleteOnExit();
	}

	public void download(Entry<String, URL> chapter) throws IOException {
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
	}

	public void downloadAll() throws IOException {
		int count = 1;
		System.out.println("Start downloading\u2026");
		for (Entry<String, URL> chapter : chapters.entrySet()) {
			System.out.print(":: " + count++ + "/" + chapters.size());
			download(chapter);
			System.out.println(" succeed.");
		}
	}
	
	public void mergePdfs() throws DocumentException, IOException {
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
		System.out.println("Start mergin\u2026");
		for (File srcPdf : src) {
			
			if (Thread.interrupted()) {
			    return;
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

		if (book.getInfo("author") != null)
			document.addAuthor(book.getInfo("author"));
		if (book.getInfo("title") != null)
			document.addTitle(book.getInfo("title"));
		if (book.getInfo("subtitle") != null)
			document.addSubject(book.getInfo("subtitle"));
		document.close();

		System.out.println("Merge complete. Saved to " + saveFile);
	}

	public void deleteTemp() {
		for (File srcPdf : src) {
			if (srcPdf.exists())
				srcPdf.delete();
		}
		if (tmpDir.exists())
			tmpDir.delete();
	}
}
