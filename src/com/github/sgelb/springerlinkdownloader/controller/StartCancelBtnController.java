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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class StartCancelBtnController implements ActionListener {

	private JTextField urlField;
	private JLabel saveFolderLabel;
	private JProgressBar progressBar;
	private JLabel progressText;
	private JButton startBtn;
	private JButton cancelBtn;
	private JButton browseBtn;
	private Worker worker = null;

	public StartCancelBtnController(JTextField urlField,
			JLabel saveFolderLabel, JLabel progressText,
			JProgressBar progressBar, JButton startBtn, JButton browseBtn,
			JButton cancelBtn) {

		this.urlField = urlField;
		this.saveFolderLabel = saveFolderLabel;
		this.progressText = progressText;
		this.progressBar = progressBar;
		this.startBtn = startBtn;
		this.browseBtn = browseBtn;
		this.cancelBtn = cancelBtn;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBtn) {
			worker = new Worker(urlField, saveFolderLabel, progressBar,
					progressText, startBtn, browseBtn);
			worker.execute();
		} else if (e.getSource() == cancelBtn) {
			if (worker != null) {
				worker.cancel(true);
			}
		}
	}

}
