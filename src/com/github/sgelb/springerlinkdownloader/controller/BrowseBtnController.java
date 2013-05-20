/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     sgelb - initial API and implementation
 ******************************************************************************/
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
		File saveFolder = SaveFolderDialog.getPath(saveFolderLabel.getText());
		if (saveFolder != null) {
			saveFolderLabel.setText(saveFolder.toString());
			saveFolderLabel.setToolTipText(saveFolder.toString());
		}
		else {
			saveFolderLabel.setToolTipText(saveFolderLabel.getText());
		}
	}
}
