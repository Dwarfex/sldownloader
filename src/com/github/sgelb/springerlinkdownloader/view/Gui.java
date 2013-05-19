package com.github.sgelb.springerlinkdownloader.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.github.sgelb.springerlinkdownloader.controller.BrowseBtnController;
import com.github.sgelb.springerlinkdownloader.controller.StartCancelBtnController;
import com.github.sgelb.springerlinkdownloader.controller.UrlFieldController;
import com.github.sgelb.springerlinkdownloader.helper.Clipboard;
import com.github.sgelb.springerlinkdownloader.helper.RegEx;

public class Gui {

	private int textFieldWidth = 40;
	private JButton startBtn = new JButton("Start");

	public void run() {
		JFrame frame = new JFrame("SpringerLink Downloader");
		frame.setLayout(new BorderLayout());

		JPanel upperArea = new JPanel();
		upperArea.setLayout(new GridBagLayout());

		JPanel lowerArea = new JPanel();
		lowerArea.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// UPPER AREA

		GridBagConstraints c;
		Insets set = new Insets(6, 6, 6, 6);

		// COLUMN 0

		// "URL"-Label
		c = new GridBagConstraints();
		c.insets = set;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		upperArea.add(new JLabel("URL: "), c);

		// "Save folder"-Label
		c.gridy = 1;
		upperArea.add(new JLabel("Save folder: "), c);

		// Separator
		c = new GridBagConstraints();
		c.insets = set;
		c.weightx = 1.0;
		c.gridy = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		upperArea.add(new JSeparator(), c);

		// "Progress"-Label
		c.gridy = 3;
		JLabel progressText = new JLabel("Enter URL, select save folder and press »Start«");
		upperArea.add(progressText, c);

		// Progressbar
		c.gridy = 4;
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString("");
		upperArea.add(progressBar, c);

		// Separator
		c.gridy = 5;
		upperArea.add(new JSeparator(), c);

		// COLUMN 1

		// "URL"-Textfield
		c.gridx = 1;
		c.gridy = 0;
		JTextField urlField = new JTextField(textFieldWidth);
		urlField.setText(Clipboard.getUrlfromClipboard());
		urlField.getDocument().addDocumentListener(
				new UrlFieldController(startBtn));
		upperArea.add(urlField, c);

		// "save Folder"-Textfield
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		JLabel saveFolderLabel = new JLabel();
		saveFolderLabel.setText(System.getProperty("user.home"));
		saveFolderLabel.setToolTipText(saveFolderLabel.getText());
		upperArea.add(saveFolderLabel, c);

		// COLUMN 2

		// "Browse"-Button
		c = new GridBagConstraints();
		c.insets = set;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.EAST;

		JButton browseBtn = new JButton("Browse…");
		browseBtn.addActionListener(new BrowseBtnController(saveFolderLabel));
		upperArea.add(browseBtn, c);

		frame.add(upperArea, BorderLayout.CENTER);

		// LOWER AREA

		JButton cancelBtn = new JButton("Cancel");
		startBtn.setEnabled(RegEx.matchUrl(urlField.getText()));
		StartCancelBtnController startCancelBtnController;
		startBtn.addActionListener((startCancelBtnController = new StartCancelBtnController(urlField,
				saveFolderLabel, progressText, progressBar, startBtn, browseBtn, cancelBtn)));

		cancelBtn.addActionListener(startCancelBtnController);
		
		lowerArea.add(cancelBtn);
		lowerArea.add(startBtn);

		frame.add(lowerArea, BorderLayout.SOUTH);

		// ////

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(upperArea.getPreferredSize());
		frame.setVisible(true);
	}

}
