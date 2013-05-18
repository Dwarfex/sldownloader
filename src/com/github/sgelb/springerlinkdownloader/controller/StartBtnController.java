package com.github.sgelb.springerlinkdownloader.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class StartBtnController implements ActionListener {

	private JTextField urlField;
	private JLabel saveFolderLabel;
	private JProgressBar progressBar;
	private JLabel progressText;
	private JButton startBtn;
	private JButton browseBtn;

	public StartBtnController(JTextField urlField, JLabel saveFolderLabel,
			JLabel progressText, JProgressBar progressBar, JButton startBtn, JButton browseBtn) {
		
		this.urlField = urlField;
		this.saveFolderLabel = saveFolderLabel;
		this.progressText = progressText;
		this.progressBar = progressBar;
		this.startBtn = startBtn;
		this.browseBtn = browseBtn;
	}

	public void actionPerformed(ActionEvent e) {
		Worker worker = new Worker(urlField, saveFolderLabel, progressBar, progressText, startBtn, browseBtn);
		worker.execute();
	}

}
