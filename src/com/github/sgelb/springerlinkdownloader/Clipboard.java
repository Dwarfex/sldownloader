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
package com.github.sgelb.springerlinkdownloader;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clipboard {
	public static String getUrlfromClipboard() {
		String url = null;
		String pattern = "(?:http://)?link.springer.com/book/\\b(10[.][0-9]{4,}(?:[.][0-9]+)*/(?:(?![\"&\\'<>])\\S)+)\\b";
		
		Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		
		try {
			if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				url = (String) trans.getTransferData(DataFlavor.stringFlavor);
				Matcher matcher = Pattern.compile(pattern).matcher(url);
				if (matcher.matches()) {
					return url;
				}
				else {
					url = null;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
