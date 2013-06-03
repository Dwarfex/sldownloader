/*******************************************************************************
 * Copyright (c) 2013 sgelb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.github.sgelb.sldownloader.helper;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class Clipboard {
	public static String getUrlfromClipboard() {
		String url = null;
		
		Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		
		try {
			if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				url = (String) trans.getTransferData(DataFlavor.stringFlavor);
				if (RegEx.matchUrl(url)) {
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
