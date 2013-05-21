package org.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.library.dao.BookDao;
import org.library.model.Book;

public class booksEntering extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String publisher = req.getParameter("publisher");
		String callNumber = req.getParameter("callNumber");
		String ISBNandPricing = req.getParameter("ISBNandPricing");
		String subject = req.getParameter("subject");
		String page = req.getParameter("page");
		String list = req.getParameter("list");
		String content = req.getParameter("content");
		String barcode = req.getParameter("barcode");
		String condition = req.getParameter("condition");
		String lib = req.getParameter("lib");
		
		String result = "";
		
		if(null == title || "".equals(title))
		{
			result += "题名必须要填写! ";
		}
		
		if(null == author || "".equals(author))
		{
			result += "责任者必须要填写！";
		}
		
		if(null == publisher || "".equals(publisher))
		{
			result += "出版社必须要填写！";
		}
		
		if(null == callNumber || "".equals(callNumber))
		{
			result += "索引号必须要填写! ";
		}
		
		if(null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN及定价必须要填写！";
		}
		
		if(null == subject || "".equals(subject))
		{
			result += "科学主题必须要填写！";
		}
		
		if(null == page || "".equals(page))
		{
			result += "载体形态项必须要填写！";
		}
		
		if(null == list || "".equals(list))
		{
			result += "书目录必须要填写！";
		}
		
		if(null == content || "".equals(content))
		{
			result += "内容简介必须要填写！";
		}
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		if(result == "")
		{
			Book book = new Book();
			
			book.setTitle(title);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setCallNumber(callNumber);
			book.setISBNandPricing(ISBNandPricing);
			book.setSubject(subject);
			book.setPage(page);
			book.setList(list);
			book.setContent(content);
			book.setBarcode(barcode);
			book.setCondition(condition);
			book.setLib(lib);
			
			BookDao.saveBook(book);//将书目信息存入数据库
			
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body><h1>书目录入成功</h1><br>");
			out.println("<a href = 'http://localhost:8080/MyLibrary/booksEntering.jsp' target= '_blank'>返回</a><br>");
			
			out.flush();
		}
		
		if(result != "")
		{
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>"+ result +"<br>");
			out.println("<a href = 'http://localhost:8080/MyLibrary/booksEntering.jsp' target= '_blank'>返回</a><br>");
			
			out.flush();
		}
			
		
	}

}
