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

import java.net.URL;
import java.util.HashMap;
import java.util.TreeMap;

public class Book {
	private TreeMap<String, URL> chapters = new TreeMap<>();
	private HashMap<String, String> infos;

	public TreeMap<String, URL> getChapters() {
		return chapters;
	}

	public void setChapters(TreeMap<String, URL> chapters) {
		this.chapters = chapters;
	}

	public void setInfos(HashMap<String, String> infos) {
		this.infos = infos;
	}

	
	public String getPdfTitle() {
		// TODO: check if author and title != null 
		return (infos.get("author") + "-" + infos.get("title")).replace(" ", "_");
	}
	
	public String getInfo(String info) {
		if (infos.containsKey(info)) {
			return infos.get(info);
		} else {
			return null;
		}
	}
}
