package com.github.sgelb.springerlinkdownloader.controller;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.github.sgelb.springerlinkdownloader.helper.RegEx;

public class UrlFieldController implements DocumentListener {

	private JButton startBtn;
	
	public UrlFieldController(JButton startBtn) {
		this.startBtn = startBtn;
	}
	
	public void changedUpdate(DocumentEvent e) {
	}

	public void insertUpdate(DocumentEvent e) {
		checkInput(e);
	}

	public void removeUpdate(DocumentEvent e) {
		checkInput(e);
	}
	
	public void checkInput(DocumentEvent e) {
		Document doc = e.getDocument();
		String input = null;
		
		try {
			input = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		if (RegEx.matchUrl(input)) {
			startBtn.setEnabled(true);
		}
		else {
			startBtn.setEnabled(false);
		}
	}

}