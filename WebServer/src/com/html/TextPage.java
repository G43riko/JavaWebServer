package com.html;

public class TextPage extends Page{

	@Override
	public void head() {
		putLine("<meta charset='UTF-8'>");
		putLine("<base href='href'/>");
		putLine("<meta http-equiv='content-type' content='text/html; charset=utf-8' />");
		putLine("<meta name='robots' content='all'/>");
		putLine("<meta name='keywords' content='keyword'/>");
		putLine("<meta name='author' content='Gabriel Csöllei' />");
		putLine("<meta name='description' content='description'/>");
		putLine("<title>Title</title>");
		putLine("<link rel='stylesheet' type='text/css' href='test.css'>");
	}

	@Override
	public void body() {
		putLine("<h1 id='nadpis'>ahooooj</h1>");
		putLine("<p> ono to fungujeee</p>");
	}
}
