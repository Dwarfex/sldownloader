package com.github.sgelb.springerlinkdownloader;

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

public class Gui {

	public void run() {
		JFrame frame = new JFrame("GridBag Layout");
		frame.setLayout(new GridBagLayout());

		GridBagConstraints c;
		Insets set = new Insets(5, 5, 5, 5);

		// "URL"-Label
		c = new GridBagConstraints();
		c.insets = set;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		frame.add(new JLabel("URL: "), c);

		// "Save folder"-Label
		c.gridy = 1;
		frame.add(new JLabel("Save folder: "), c);


		// Separator
		c = new GridBagConstraints();
		c.insets = set;
		c.weightx = 1.0;
		c.gridy = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		frame.add(new JSeparator(), c);

		// "What is happening"-Label
		c.gridy = 3;
		JLabel updateText = new JLabel("n/a");
		frame.add(updateText, c);

		//
		c.gridy = 4;
		JProgressBar progressBar = new JProgressBar();
		frame.add(progressBar, c);

		// Separator
		c.gridy = 5;
		frame.add(new JSeparator(), c);

		// next column

		// "URL"-Textfield
		c.gridx = 1;
		c.gridy = 0;
		JTextField urlField = new JTextField(30);
		frame.add(urlField, c);

		// "save Folder"-Textfield
		c.gridy = 1;
		JTextField saveFolderField = new JTextField(39);
		frame.add(saveFolderField, c);

		// Buttons
		c = new GridBagConstraints();
		c.insets = set;
		c.gridx = 2;
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton cancelBtn = new JButton("Cancel");
		JButton startBtn = new JButton("Start");
		
		startBtn.setEnabled(false);
		buttonPanel.add(cancelBtn);
		buttonPanel.add(startBtn);
		frame.add(buttonPanel, c);

		// /////////////////////

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
		frame.setVisible(true);
	}

}
