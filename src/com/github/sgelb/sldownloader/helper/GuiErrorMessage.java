/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.github.sgelb.sldownloader.helper;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import com.github.sgelb.sldownloader.controller.Worker;

public class GuiErrorMessage {
	
	public static void show(Worker worker, JProgressBar progressBar, Exception e) {
		progressBar.setIndeterminate(false);
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
		worker.cancel(true);
	}

}
