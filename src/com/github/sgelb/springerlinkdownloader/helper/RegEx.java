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
