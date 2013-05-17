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
package com.github.sgelb.springerlinkdownloader.model;


import java.net.URL;
import java.util.HashMap;
import java.util.TreeMap;

public class Book {

	private TreeMap<String, URL> chapters = new TreeMap<>();
	private HashMap<String, String> infos = new HashMap<>();

	public TreeMap<String, URL> getChapters() {
		return chapters;
	}

	public void setChapters(TreeMap<String, URL> chapters) {
		this.chapters = chapters;
	}

	public void setInfo(String key, String value) {
		this.infos.put(key, value);
	}
	
	public String getInfo(String key) {
		if (infos.containsKey(key)) {
			return infos.get(key);
		}
		else {
			return null;
		}
	}

	public String getPdfTitle() {
		return (infos.get("author") + "-" + infos.get("title")).replace(" ", "_");
	}

}
