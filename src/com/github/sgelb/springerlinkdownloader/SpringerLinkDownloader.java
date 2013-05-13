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
package com.github.sgelb.springerlinkdownloader;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

public class SpringerLinkDownloader {

	public static SpringerLinkDownloader sl = new SpringerLinkDownloader();
	private String version = "20130513";

	public void run() {

		// url to book
		// http://link.springer.com/book/${doi}/[page/1]
		// doi = ${prefix}/${onlineISBN}
		// /page/1 is optional
		// String url = "http://link.springer.com/book/10.1007/978-3-8348-9931-6";
		
		String url = Clipboard.getUrlfromClipboard(); 
		File saveFolder = new File(System.getProperty("user.home"));
		boolean delTmpPdfs = true;

		// begin menu
		
		System.out.println("SpringerLink Downloader (v" + version + ")");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter url:");
		if (url != null) {
			System.out.print("[" + url + "]\n> ");
		}
		else {
			System.out.print("> ");
		}
		String tmp = scanner.nextLine().trim();
		if (!tmp.isEmpty()) url = tmp;

		System.out.print("Enter save folder ["
				+ System.getProperty("user.home") + "]\n> ");
		tmp = scanner.nextLine().trim();
		if (!tmp.isEmpty()) saveFolder = new File(tmp);

		System.out.print("Delete temporary files? [Yn]\n> ");
		if (scanner.nextLine().trim().equalsIgnoreCase("n")) {
			delTmpPdfs = false;
		}
		scanner.close();
		
		// end menu
		
		Book book = new Book();
		Parser parsePage = new Parser(url, book);
		parsePage.run();
		Pdf pdf = new Pdf(book, saveFolder);

		pdf.download(delTmpPdfs);
		try {
			pdf.create();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sl.run();
	}

}
