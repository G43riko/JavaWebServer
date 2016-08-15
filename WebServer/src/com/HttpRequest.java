package com;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private String filename;
	private Map<String, String> data = new HashMap<String, String>();
	
	public HttpRequest(String request) {
		String[] lines = request.split("\n");
		
		
		filename = lines[0].split(" ")[1];
		String[] tmp = filename.split("\\?");
		filename = tmp[0];
		
		if(tmp.length > 1)
			processData(tmp[1].split("&"));
	}
	
	
	public String getFileType(){
		switch (filename.split("\\.")[1]) {
			case "html": return "text/html";
			case "css":  return "text/css";
			default: return "text/html";
		}
	}
	
	private void processData(String[] parameters){
		for(String parameter : parameters){
			String[] argument = parameter.split("=");
			if(argument.length > 1)
				data.put(argument[0], argument[1]);
		}
	}

	public String getFilename() {
		return filename;
	}

	
}
