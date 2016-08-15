package com.html;

import java.util.HashSet;
import java.util.Set;

public final class Head extends Html{
	private String language;
	private String charset;
	private String robots; // = "all";
	private String author; // = "Gabriel Csollei";
	private String keywords;
	private String description;
	private String title;
	private boolean generated = false;
	
	private Set<String> css = new HashSet<String>();
	private Set<String> js = new HashSet<String>();
	
	//CONSTRUCTORS
	
	public Head(String title){
		this(title,"SK", "UTF-8");
	}
	
	public Head(String title, String language, String charset){
		this.title = title;
		this.charset = charset;
		this.language = language;
	}
	
	//OTHERS
	
	public void generate() {
		String offset = "\t\t";
		
		clean();
		putLine(offset + "<meta charset='" + charset + "'>");
		
		if(robots != null)
			putLine(offset + "<meta name='robots' content='" + robots + "'/>");
		if(keywords != null)
			putLine(offset + "<meta name='keywords' content='" + keywords + "'/>");
		if(author != null)
			putLine(offset + "<meta name='author' content='" + author + "'/>");
		if(description != null)
			putLine(offset + "<meta name='description' content='" + description + "'/>");
		
		putLine(offset + "<title>" + title + "</title>");
		
		css.forEach(e -> putLine(offset + "<link rel='stylesheet' type='text/css' href='" + e + "'>"));
		js.forEach(e -> putLine(offset + "<script type='text/javascript' src='" + e + "'></script>"));
		generated = true;
	}
	
	//ADDERS

	public Head addJavaScript(String title){js.add(title); return this;}
	
	public Head addStyleSheets(String title){css.add(title); return this;}
	
	//GETTERS
	
	@Override
	public String getContent() {
		if(!generated)
			System.err.println("hlavička nebola vygenerovana");
		return super.getContent();
	}
	
	public String getLanguage(){
		return language;
	}
	
	//SETTERS

	public Head setLanguage(String language) {
		generated = false;
		this.language = language;
		return this;
	}

	public Head setCharset(String charset) {
		generated = false;
		this.charset = charset;
		return this;
	}

	public Head setRobots(String robots) {
		generated = false;
		this.robots = robots;
		return this;
	}

	public Head setAuthor(String author) {
		generated = false;
		this.author = author;
		return this;
	}

	public Head setKeywords(String keywords) {
		generated = false;
		this.keywords = keywords;
		return this;
	}

	public Head setDescription(String description) {
		generated = false;
		this.description = description;
		return this;
	}

	public Head setTitle(String title) {
		generated = false;
		this.title = title;
		return this;
	}
}
