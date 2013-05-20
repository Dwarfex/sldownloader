package com.github.sgelb.springerlinkdownloader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class jmGUI extends JPanel{
	
    JFileChooser fileChooser = new JFileChooser();
	
	JPanel Oben = new JPanel();
	JButton StartButton = new JButton ("Start");
	JLabel info = new JLabel("Enter Link here:");
	JTextField Link = new JTextField("", 60);
	JTextArea Ausgabe = new JTextArea(40, 80);
	JCheckBox merge = new JCheckBox("Merge", true);
	
	File Output;
	
	JPanel Unten = new JPanel();
	Core kern = new Core();
	
	
	public jmGUI(){
		
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Save PDF to ...");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		setLayout(new BorderLayout());
		// Die Objekte die nach oben sollen:
		Oben.add(info);
		Oben.add(Link);
		Oben.add(merge);
		Oben.add(StartButton);
		
        
		StartButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		startDownload();
	    	}
	    });
		// Die Objekte die nach unten sollen:
//		Unten.add(comp);
		
		// Die Bereiche hinzufügen
		add(Oben,BorderLayout.NORTH);
        add(Ausgabe,BorderLayout.CENTER);
        add(Unten,BorderLayout.SOUTH);
	
		
	}
	private void startDownload(){
		kern.initalisieren(merge.isSelected(),Link.getText());
		kern.parse();

    	int resulut = fileChooser.showSaveDialog(this);
    	if(resulut == JFileChooser.APPROVE_OPTION){
    		Output = fileChooser.getSelectedFile();	
    	}
    	
		kern.start(Output);
		
	}
	public void ausgeben(String ausgabe){
		Ausgabe.append(ausgabe);
	}


}