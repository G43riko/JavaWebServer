package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.html.TextPage;

public class HttpResponse {
	private HttpRequest httpRequest;
	
	private String response;
	private String root = "/home/gabriel";
	
	private Map<String, String> cookies = new HashMap<String, String>();
	
	public HttpResponse(HttpRequest httpRequest) {
		cookies.put("a", "aa");
		cookies.put("b", "bb");
		this.httpRequest = httpRequest;
		
		File file = new File(root + httpRequest.getFilename());
		StringBuilder responseHeader = new StringBuilder();
		try{
			FileInputStream fileInputStream = new FileInputStream(file);
			responseHeader.append("HTTP/1.1 200\r\n");
			responseHeader.append("Server: Our Java Server/1.0 \r\n");
			responseHeader.append("Content-Type: " + httpRequest.getFileType() + " \r\n");

			for(Entry<String, String> e : cookies.entrySet())
				responseHeader.append("Set-Cookie: " + e.getKey() + "=" + e.getValue() + " \r\n");
			
			
			String page = new TextPage().getPage();
			
			responseHeader.append("Connection: close \r\n");
			//responseHeader.append("Content-Length: " + file.length() + "\r\n");
			responseHeader.append("Content-Length: " + page.length() + "\r\n");
			responseHeader.append("\r\n");
			
			/*
			int pointer;
			while((pointer = fileInputStream.read()) != -1)
				responseHeader.append((char)pointer);
			*/
			responseHeader.append(page);
			
			fileInputStream.close();
			//System.out.println(responseHeader);
			response = responseHeader.toString();
		}catch (FileNotFoundException e) {//404 error
			response = responseHeader.toString().replace("200", "404");
			System.err.println("súbor sa nenašiel" + e);
		}
		catch (Exception e) {//500 error
			response = responseHeader.toString().replace("200", "500");
			System.out.println("chyba: " + e);
		}
	}
	
	
	public String getResponse() {
		return response;
	}
}
