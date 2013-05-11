/*******************************************************************************
 * Copyright (c) 2013 sgelb
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

public class SpringerLinkDownloader {

	public static SpringerLinkDownloader sl = new SpringerLinkDownloader();
	private String version = "20130511";

	public void init() {

		// url to book
		// http://link.springer.com/book/${doi}/[page/1]
		// doi = ${prefix}/${onlineISBN}
		// /page/1 is optional
		String url = "http://link.springer.com/book/10.1007/978-3-8348-9931-6";
		File saveFolder = new File(System.getProperty("user.home"));
		boolean delTmpPdfs = true;
		
		System.out.println("SpringerLink Downloader (v" + version + ")");		
		
		Scanner scanner  = new Scanner(System.in);
		System.out.print("Enter url, i.e. http://link.springer.com/book/10.1007/978-3-8348-9931-6\n> ");
		url = scanner.nextLine().trim();
		
		System.out.print("Enter save folder [" + System.getProperty("user.home") + "]\n> ");
		saveFolder = new File(scanner.nextLine().trim());
		
		System.out.print("Delete temporary files? [Yn]\n> ");
		if (scanner.next().equalsIgnoreCase("n")) {
			delTmpPdfs = false;
		}
		scanner.close();

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
		sl.init();
	}

}
