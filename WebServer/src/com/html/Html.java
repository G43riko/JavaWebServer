package com.html;

public abstract class Html {
	private StringBuilder content;
	
	public Html(){
		this("sk");
	}

	public Html(String language){
		content = new StringBuilder();
	}
	
	protected void put(String text){
		content.append(text);
	}
	
	protected void putLine(String text){
		content.append(text + "\n");
	}
	
	protected void clean(){
		content.setLength(0);
	}
	
	public int length(){
		return content.length();
	}
	
	public String getContent(){
		return content.toString();
	}
}
