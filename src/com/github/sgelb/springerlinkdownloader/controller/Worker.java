package com.github.sgelb.springerlinkdownloader.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.github.sgelb.springerlinkdownloader.model.Book;
import com.github.sgelb.springerlinkdownloader.model.Parser;
import com.github.sgelb.springerlinkdownloader.model.Pdf;
import com.itextpdf.text.DocumentException;

public class Worker extends SwingWorker<Void, Integer> {

	private Book book = new Book();
	private JTextField urlField;
	private JProgressBar progressBar;
	private File saveFolder;
	private JLabel progressText;
	private JButton startBtn;
	private JButton browseBtn;
	private Pdf pdf;
	private TreeMap<String, URL> chapters = new TreeMap<>();

	public Worker(JTextField urlField, JLabel saveFolderLabel,
			JProgressBar progressBar, JLabel progressBarLabel,
			JButton startBtn, JButton browseBtn) {

		this.urlField = urlField;
		this.progressBar = progressBar;
		this.saveFolder = new File(saveFolderLabel.getText());
		this.progressText = progressBarLabel;
		this.startBtn = startBtn;
		this.browseBtn = browseBtn;
	}

	protected Void doInBackground() throws Exception {
		if (!isCancelled()) {
			browseBtn.setEnabled(false);
			startBtn.setEnabled(false);
			urlField.setEditable(false);

			progressText.setText("Parsing page…");
			progressBar.setIndeterminate(true);
			
			Parser parsePage = new Parser(urlField.getText(), book);
			parsePage.run();
			chapters = book.getChapters();
		}

		if (!isCancelled()) {
			progressText.setText("Downloading files…");
			progressBar.setIndeterminate(false);
			progressBar.setMaximum(chapters.size());

			pdf = new Pdf(book, saveFolder);
			Integer count = 1;
			for (Entry<String, URL> chapter : chapters.entrySet()) {
				if (!isCancelled()) {
					pdf.download(chapter);
				}
				progressBar.setString("[" + count + "/" + chapters.size() + "]");
				progressBar.setValue(count++);
			}
			progressBar.setString("");
		}

		if (!isCancelled()) {
			progressText.setText("Merging files…");
			try {
				pdf.create();
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
		}
		
		if (isCancelled()) {
			progressText.setText("Cancelled.");
			progressBar.setValue(0);
			if (book.getInfo("saveFile") != null) {
				File saveFile = new File(book.getInfo("saveFile"));
				if (saveFile.exists()) {
					saveFile.delete();
				}
			}
		} else {
			progressText.setText("Created " + book.getInfo("saveFile")+ ".");
		}
		
		progressBar.setIndeterminate(false);
		browseBtn.setEnabled(true);
		startBtn.setEnabled(true);
		urlField.setEditable(true);
		pdf.deleteTemp();
		return null;
	}

	protected void done() {
		try {
			super.get();
		} catch (CancellationException e) {
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
