package com.github.sgelb.springerlinkdownloader.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import com.github.sgelb.springerlinkdownloader.view.SaveFolderDialog;

public class BrowseBtnController implements ActionListener {

	private JTextField saveFolderField;

	public BrowseBtnController(JTextField saveFolderField) {
		this.saveFolderField = saveFolderField;
	}
	
	public void actionPerformed(ActionEvent e) {
		File saveFolder = SaveFolderDialog.getPath(System.getProperty("user.home"));
		if (saveFolder != null) {
			saveFolderField.setText(saveFolder.toString());
		}
	}
}
