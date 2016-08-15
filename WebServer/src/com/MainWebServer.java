package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.html.editor.Element;

public class MainWebServer {
	private ServerSocket serverSocket;
	private boolean running = true;
	
	public static void main(String[] args) {
		Element body = new Element("body").addAttribute("id", "idecko").addAttribute("class", "classa");
		
		body.addStyle("display", "block").addStyle("color", "red");
		body.addContent("nazdaaar");
		body.addContent("funguje tooooo");
		body.addContent(new Element("p").addContent("textik"));
		body.addContent(new Element("br"));
		
		System.out.println(body.generate("\t"));
		
		
		//new MainWebServer().runServer();
	}
	
	public void runServer(){
		
		try {
			serverSocket = new ServerSocket(1234);
			acceptRequests();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void acceptRequests() throws IOException{
		while(running){
			Socket socket = serverSocket.accept();
			System.out.println(socket.getPort() + " " + socket.getLocalPort() + " " + socket.getInetAddress() + " " + socket.getLocalAddress());
			ConnectionHandler connectionHandler = new ConnectionHandler(socket);
			connectionHandler.start();
		}
	}
}
