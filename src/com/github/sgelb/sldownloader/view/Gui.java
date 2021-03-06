/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.github.sgelb.sldownloader.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.sgelb.sldownloader.controller.BrowseBtnController;
import com.github.sgelb.sldownloader.controller.StartCancelBtnController;
import com.github.sgelb.sldownloader.controller.UrlFieldController;
import com.github.sgelb.sldownloader.helper.Clipboard;
import com.github.sgelb.sldownloader.helper.RegEx;

public class Gui {
	
	private JButton startBtn;
	private JButton browseBtn;
	private JTextField urlField;
	private JLabel progressText;
	private JProgressBar progressBar;
	private int textFieldWidth;

	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		JFrame frame = new JFrame("SpringerLink Downloader");
		frame.setLocationByPlatform(true);
		frame.setLayout(new BorderLayout());

		JPanel upperArea = new JPanel();
		upperArea.setLayout(new GridBagLayout());

		JPanel lowerArea = new JPanel();
		lowerArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		textFieldWidth = 40;

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
		upperArea.add(new JLabel("URL of book: "), c);

		// "Save folder"-Label
		c.gridy = 1;
		upperArea.add(new JLabel("Save PDF to: "), c);

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
		progressText = new JLabel(
				"Not started yet\u2026");
		progressText.setEnabled(false);
		upperArea.add(progressText, c);

		// Progressbar
		c.gridy = 4;
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString("");
		upperArea.add(progressBar, c);

		// COLUMN 1

		// "URL"-Textfield
		c.gridx = 1;
		c.gridy = 0;
		urlField = new JTextField(textFieldWidth);
		urlField.setText(Clipboard.getUrlfromClipboard());
		
		String urlToolTip = "Enter book download URL - it's the one ending in /page/1";
		urlField.setToolTipText(urlToolTip);
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

		browseBtn = new JButton("Browse\u2026");
		browseBtn.addActionListener(new BrowseBtnController(saveFolderLabel));
		browseBtn.setMnemonic(KeyEvent.VK_B);
		upperArea.add(browseBtn, c);

		frame.add(upperArea, BorderLayout.CENTER);

		// LOWER AREA

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setMnemonic(KeyEvent.VK_C);
		
		startBtn = new JButton("Start");
		startBtn.setEnabled(RegEx.matchUrl(urlField.getText()));
		startBtn.setMnemonic(KeyEvent.VK_S);

		lowerArea.add(cancelBtn);
		lowerArea.add(startBtn);

		frame.add(lowerArea, BorderLayout.SOUTH);

		////// add Listener/Controller
		
		urlField.getDocument().addDocumentListener(
				new UrlFieldController(startBtn));
		StartCancelBtnController startCancelBtnController;
		startBtn.addActionListener((startCancelBtnController = new StartCancelBtnController(
				urlField, saveFolderLabel, progressText, progressBar, startBtn,
				browseBtn, cancelBtn)));
		cancelBtn.addActionListener(startCancelBtnController);
		
		//////

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
		frame.setVisible(true);
	}

}
