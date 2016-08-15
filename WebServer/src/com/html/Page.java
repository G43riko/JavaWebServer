package com.html;

public abstract class Page extends Html{
	private Head head = null;
	private Body body;
	
	//CONSTRUCTORS
	
	public Page() {
		this(null, (Body)null);
	}
	
	public Page(Head head) {
		this(head, (Body)null);
	}
	
	public Page(Head head, Body body) {
		this.head = head;
		this.body = body;
	}
	
	public Page(Head head, String fileName) {
		this.head = head;
		this.body = new Body(fileName);
	}
	
	//OTHERS
	
	public void head(){};
	public abstract void body();
	
	//GETTERS
	
	public String getHttpText(){
		String res = getPage();
		return "Content-Length: " + res.length() + "\r\n\r\n" + res;
	}
	
	public String getPage(){
		String offset = "\t";
		putLine("<!DOCTYPE html>");
		putLine(offset + (head == null ? "<html>" : "<html lang='" + head.getLanguage() + "'>"));
		putLine(offset + "<head>");
		
		if(head != null)
			putLine(head.getContent()); 
		else
			head();
		
		putLine(offset + "</head>");
		putLine(offset + "<body>");
		
		if(body != null)
			putLine(offset + body.getContent());
		else
			body();
		
		putLine(offset + "</body>");
		putLine("</html>");
		
		return getContent();
	}
	//HTML Functions
	
	public void heading1(String text){
		putLine(text == null || text.isEmpty() ? "<h1/>" : "<h1>" + text + "</h1>");
	}
	public void heading2(String text){
		putLine(text == null || text.isEmpty() ? "<h2/>" : "<h2>" + text + "</h2>");
	}
	public void heading3(String text){
		putLine(text == null || text.isEmpty() ? "<h3/>" : "<h3>" + text + "</h3>");
	}
	public void heading4(String text){
		putLine(text == null || text.isEmpty() ? "<h4/>" : "<h4>" + text + "</h4>");
	}
	public void heading5(String text){
		putLine(text == null || text.isEmpty() ? "<h5/>" : "<h5>" + text + "</h5>");
	}
	public void heading6(String text){
		putLine(text == null || text.isEmpty() ? "<h6/>" : "<h6>" + text + "</h6>");
	}
	public void breakLine(){
		putLine("<br/>");
	}
	public void breakLine(int num){
		for(int i=0 ; i<num ; i++)
			breakLine();
	}
	
	//SETTERS
	
	public void setHead(Head head) {
		this.head = head;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
}
