package com.github.sgelb.springerlinkdownloader.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;

import com.github.sgelb.springerlinkdownloader.view.SaveFolderDialog;

public class BrowseBtnController implements ActionListener {

	private JLabel saveFolderLabel;

	public BrowseBtnController(JLabel saveFolderLabel) {
		this.saveFolderLabel = saveFolderLabel;
	}
	
	public void actionPerformed(ActionEvent e) {
		File saveFolder = SaveFolderDialog.getPath(System.getProperty("user.home"));
		if (saveFolder != null) {
			saveFolderLabel.setText(saveFolder.toString());
		}
	}
}
