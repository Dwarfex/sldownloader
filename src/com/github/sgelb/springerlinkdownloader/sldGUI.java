package com.github.sgelb.springerlinkdownloader;

/*
 *   ACHTUNG: Diese Klasse macht nichts ausser jmGUI in ein JFrame zu packen ...
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class sldGUI extends JFrame{
	private jmGUI Hauptfenster = new jmGUI();
	
	public sldGUI(){
		super("Springer Link Downloader");
		add(Hauptfenster, BorderLayout.CENTER);
		pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame frame = new sldGUI();
		
		
		

	}

}
