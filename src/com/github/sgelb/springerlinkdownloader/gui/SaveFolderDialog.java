package com.github.sgelb.springerlinkdownloader.gui;

import java.io.File;

import javax.swing.JFileChooser;

public class SaveFolderDialog {

	public static File getPath(String startFolder) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setCurrentDirectory(new File(startFolder));
		int response = jfc.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			return new File(jfc.getSelectedFile().getAbsolutePath());
		}
		else {
			return null;
		}
	}
}
