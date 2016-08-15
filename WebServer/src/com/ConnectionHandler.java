package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread{
	private Socket socket;
	private PrintWriter printWritter;
	private BufferedReader bufferedReader;
	
	public ConnectionHandler(Socket socket) throws IOException{
		this.socket = socket;
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWritter = new PrintWriter(socket.getOutputStream());
	}
	
	@Override
	public void run() {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			
			while(bufferedReader.ready() || stringBuilder.length() == 0)
				stringBuilder.append(bufferedReader.readLine() + "\n");
				
			System.out.println(stringBuilder.toString());
			HttpRequest httpRequest = new HttpRequest(stringBuilder.toString());
			
			HttpResponse httpResponse = new HttpResponse(httpRequest);
			printWritter.write(httpResponse.getResponse().toCharArray());
			
			printWritter.close();
			bufferedReader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
