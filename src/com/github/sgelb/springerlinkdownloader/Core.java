package com.github.sgelb.springerlinkdownloader;

import java.io.File;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class Core {
	String url = "";
	File saveFolder = new File(System.getProperty("user.home"));
	boolean delTmpPdfs = true;
	boolean merge = true;
	jmGUI Aufrufer;
	
	Book book = new Book();
	Parser parsePage;
	Pdf pdf;
	
	public void Core(jmGUI Aufrufer){
		this.Aufrufer = Aufrufer;
	}
	public void initalisieren(boolean merge, String url){
		
		// TODO delTmpFiles
		
		this.merge = merge;
		this.url = url;
		

		
	}
	public void parse(){
		//Aufrufer.ausgeben("Starte parsen ...");
		
		parsePage = new Parser(url, book);
		parsePage.run();
	}
	public void start(File Output){
		//Aufrufer.ausgeben("Starte downloaden ...");
		pdf.download(delTmpPdfs);
		
		pdf = new Pdf(book, Output, merge);

		
		try {
			pdf.create();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	

}
