package com.github.sgelb.springerlinkdownloader.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Gui {

	private JTextField saveFolderField = new JTextField(30);
	
	public void run() {
		JFrame frame = new JFrame("SpringerLink Downloader");
		frame.setLayout(new BorderLayout());

		JPanel upperArea = new JPanel();
		upperArea.setLayout(new GridBagLayout());
		
		JPanel lowerArea = new JPanel();
		lowerArea.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// UPPER AREA
		
		GridBagConstraints c;
		Insets set = new Insets(5, 5, 5, 5);

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

		// "What is happening"-Label
		c.gridy = 3;
		JLabel updateText = new JLabel("n/a");
		upperArea.add(updateText, c);

		// Progressbar
		c.gridy = 4;
		JProgressBar progressBar = new JProgressBar();
		upperArea.add(progressBar, c);

		// Separator
		c.gridy = 5;
		upperArea.add(new JSeparator(), c);

		// COLUMN 1

		// "URL"-Textfield
		c.gridx = 1;
		c.gridy = 0;
		JTextField urlField = new JTextField(30);
		upperArea.add(urlField, c);

		// "save Folder"-Textfield
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		saveFolderField.setText(System.getProperty("user.home"));
		upperArea.add(saveFolderField, c);

		// COLUMN 2
		
		// "Browse"-Button
		c = new GridBagConstraints();
		c.insets = set;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.EAST;

		JButton browseBtn = new JButton("Browse…");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File saveFolder = SaveFolderDialog.getPath(System.getProperty("user.home"));
				saveFolderField.setText(saveFolder.toString());
			}
		});
		upperArea.add(browseBtn, c);
		
		frame.add(upperArea, BorderLayout.CENTER);
		
		// LOWER AREA
		
		JButton cancelBtn = new JButton("Cancel");
		JButton startBtn = new JButton("Start");
		startBtn.setEnabled(false);

		lowerArea.add(cancelBtn);
		lowerArea.add(startBtn, c);
		
		frame.add(lowerArea, BorderLayout.SOUTH);

		//////

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(upperArea.getPreferredSize());
		frame.setVisible(true);
	}

}
