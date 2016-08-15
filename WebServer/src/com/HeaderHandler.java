package com;

import java.util.HashMap;
import java.util.Map;

public class HeaderHandler {
	private String accept;
	private String acceptEncoding;
	private String acceptLanguage;
	private String cacheControl;
	private String connection;
	private String host;
	private String userAgent;
	private String referer;
	private RequestMethods type;
	private Map<String, String> cookies = new HashMap<String, String>();
	
	public HeaderHandler(String header){
		String[] lines = header.split("\n");
		
		proceesFirstLine(lines[0]);
		
		for(String line : lines){
			if(line.toLowerCase().startsWith("accept-encoding"))
				acceptEncoding = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("accept-language"))
				acceptLanguage = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("accept"))
				accept = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("cache-control"))
				cacheControl = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("connection"))
				connection = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("host"))
				host = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("referer"))
				referer = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("user-agent"))
				userAgent = line.split(" ", 1)[1];
			else if(line.toLowerCase().startsWith("cookies")){
				String[] data = line.split(" ", 1)[1].split("; ");
				for(String cookie : data){
					String[] item = cookie.split("=");
					cookies.put(item[0], item[1]);
				}
			}
			
		}
	}
	
	private void proceesFirstLine(String line){
		String[] data = line.split(" ");
		
		switch (data[0].toUpperCase()) {
		case "GET":
			type = RequestMethods.GET;
			break;
		case "POST":
			type = RequestMethods.POST;
			break;
		case "PUT":
			type = RequestMethods.PUT;
			break;
		default:
			break;
		}
	}
	
	//GETTERS
	
	public String getAccept() {
		return accept;
	}

	public String getAcceptEncoding() {
		return acceptEncoding;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public String getCacheControl() {
		return cacheControl;
	}

	public String getConnection() {
		return connection;
	}

	public String getHost() {
		return host;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getReferer() {
		return referer;
	}

	public RequestMethods getType() {
		return type;
	}
}
