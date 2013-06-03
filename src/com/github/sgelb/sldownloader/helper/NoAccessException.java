/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.github.sgelb.sldownloader.helper;

public class NoAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String msg = "You don't have access to this book.";

	public NoAccessException() {
		super(msg);
	}

}
