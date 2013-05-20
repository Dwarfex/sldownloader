package com.github.sgelb.springerlinkdownloader.helper;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import com.github.sgelb.springerlinkdownloader.controller.Worker;

public class GuiErrorMessage {
	
	public static void show(Worker worker, JProgressBar progressBar, Exception e) {
		progressBar.setIndeterminate(false);
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
		worker.cancel(true);
	}

}
