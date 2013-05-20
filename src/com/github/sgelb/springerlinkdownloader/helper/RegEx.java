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
package com.github.sgelb.springerlinkdownloader.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
	
	public static boolean matchUrl(String input) {
		String pattern = "(?:http://)?link.springer.com/book/\\b(10[.][0-9]{4,}(?:[.][0-9]+)*/(?:(?![\"&\\'<>])\\S)+)\\b";
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		if (matcher.matches()) {
			return true;
		}
		else {
			return false;
		}
	}
}
