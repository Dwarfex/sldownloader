package com.github.sgelb.springerlinkdownloader.helper;

public class NoAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String msg = "You have no access to this book.";

	public NoAccessException() {
		super(msg);
	}

}
