package com.github.sgelb.springerlinkdownloader.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*******************************************************************************
 * Copyright (c) 2013 sgelb All rights reserved. This program and the
 * accompanying materials are made available under the terms of the GNU Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/


public class SpringerLinkDownloaderGui implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SpringerLinkDownloaderGui());
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("SpringerLink Downloader");
		frame.setMinimumSize(new Dimension(400, 300));
		frame.setSize(400, 301);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MainFrame());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
