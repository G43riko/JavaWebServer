package com.html.editor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Element {
	private StringBuilder text = new StringBuilder();
	private List<Object> content = new LinkedList<Object>();
	private String title;
	private boolean generated = false;
	private Map<String, String> styles = new HashMap<String, String>();
	private Map<String, String> attributes = new HashMap<String, String>();
	
	//CONSTRUCTORS
	
	public Element(String title){
		this(title, "");
	}
	
	public Element(String title, String text){
		this.title = title;
		if(text != null && !text.isEmpty())
			addContent(text);
	}
	
	//OTHERS
	
	public String generate(String offset){
		text.setLength(0);
		text.append(offset + "<" + title);
		attributes.forEach((a, b) -> text.append(" " +a + "='" + b + "'"));
		if(!styles.isEmpty()){
			text.append(" style='");
			styles.forEach((a, b) -> text.append(" " + a + ": " + b + ";"));
			text.append("'");
		}
		if(!content.isEmpty()){
			text.append(">\n");
			content.forEach(a -> {
				if(a instanceof Element)
					text.append(((Element)a).generate(offset + "\t"));
				else
					text.append(offset + "\t" + a.toString() + "\n"); 
				
			});
			text.append(offset + "</" + title + ">\n");
		}
		else
			text.append("/>\n");
			
		generated = true;
		return this.toString();
	}
	
	@Override
	public String toString() {
		if(!generated)
			System.err.println("element musí byť najprv vygenerovaný");
		return text.toString();
	}
	
	//ADDERS

	public Element addContent(String text){
		this.content.add(text);
		generated = false;
		return this;
	}
	
	public Element addContent(Element text){
		this.content.add(text);
		generated = false;
		return this;
	}
	
	public Element addAttribute(String key, String value){
		this.attributes.put(key, value);
		generated = false;
		return this;
	}
	
	public Element addStyle(String key, String value){
		this.styles.put(key, value);
		generated = false;
		return this;
	}
	
	//GETTERS
	
	//SETTERS

	public Element setContent(String text){
		this.content.clear();
		generated = false;
		return addContent("text");
	}
	
	public Element setContent(Element text){
		this.content.clear();
		generated = false;
		return addContent("text");
	}
}
