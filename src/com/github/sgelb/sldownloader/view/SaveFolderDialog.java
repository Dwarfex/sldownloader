/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.github.sgelb.sldownloader.view;

import java.io.File;

import javax.swing.JFileChooser;

public class SaveFolderDialog {

	public static File getPath(String startFolder) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setCurrentDirectory(new File(startFolder));
		jfc.setDialogTitle("Save PDF to\u2026");
		int response = jfc.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			return new File(jfc.getSelectedFile().getAbsolutePath());
		}
		else {
			return null;
		}
	}
}
