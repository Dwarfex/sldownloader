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

import javax.swing.SwingUtilities;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.jsoup.HttpStatusException;

import com.github.sgelb.springerlinkdownloader.helper.NoAccessException;
import com.github.sgelb.springerlinkdownloader.helper.RegEx;
import com.github.sgelb.springerlinkdownloader.model.Book;
import com.github.sgelb.springerlinkdownloader.model.Parser;
import com.github.sgelb.springerlinkdownloader.model.Pdf;
import com.github.sgelb.springerlinkdownloader.view.Gui;
import com.itextpdf.text.DocumentException;

public class SpringerLinkDownloader {

	// Example url:
	// "http://link.springer.com/book/10.1007/978-3-8348-9931-6";

	public void runCLI(String[] args) {

		// get command line options
		Options options = new Options();

		options.addOption("g", "gui", false, "start GUI");
		options.addOption("u", "url", true, "Url");
		options.addOption("o", "output", true, "save folder [default: $home]");

		CommandLineParser parser = new BasicParser();

		if (args.length == 0) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("SpringerLinkDownloader", options, true);
			return;
		}

		CommandLine cmdline = null;
		try {
			cmdline = parser.parse(options, args);
		} catch (org.apache.commons.cli.ParseException e) {
			e.printStackTrace();
		}

		if (cmdline.hasOption("g")) {
			runGUI();
			return;
		}

		String url = null;
		if (cmdline.hasOption("u")
				&& RegEx.matchUrl(cmdline.getOptionValue("u"))) {
			url = cmdline.getOptionValue("u");
		} else {
			System.out.println("Please use a valid url");
			System.exit(-1);
		}

		File saveFolder = new File(System.getProperty("user.home"));
		if (cmdline.getOptionValue("o") != null) {
			File tmpFile = new File(cmdline.getOptionValue("o"));
			if (tmpFile.isDirectory()) {
				saveFolder = new File(cmdline.getOptionValue("o"));
			}
		}

		System.out.println("Download " + url);
		System.out.println("Save to " + saveFolder);

		Book book = new Book();
		Parser parsePage = new Parser(url, book);
		try {
			parsePage.parseHtml();
		} catch (NoAccessException e) {
			System.out.println("You don't have access to this book.");
			System.exit(-1);
		} catch (HttpStatusException e) {
			System.out.println("Error " + e.getStatusCode());
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Error: " + e.getStackTrace());
			System.exit(-1);
		}

		parsePage.setBookData();
		Pdf pdf = null;
		try {
			pdf = new Pdf(book, saveFolder);
			pdf.downloadAll();
			pdf.mergePdfs();
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	public void runGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Gui gb = new Gui();
				gb.run();
			}
		});
	}

	public static void main(String[] args) {
		SpringerLinkDownloader sl = new SpringerLinkDownloader();
		if (System.console() != null) { // started from console
			sl.runCLI(args);
		} else {
			sl.runGUI();
		}
	}

}
