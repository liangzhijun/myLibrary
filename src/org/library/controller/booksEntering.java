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
			result += "��������Ҫ��д! ";
		}
		
		if(null == author || "".equals(author))
		{
			result += "�����߱���Ҫ��д��";
		}
		
		if(null == publisher || "".equals(publisher))
		{
			result += "���������Ҫ��д��";
		}
		
		if(null == callNumber || "".equals(callNumber))
		{
			result += "�����ű���Ҫ��д! ";
		}
		
		if(null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN�����۱���Ҫ��д��";
		}
		
		if(null == subject || "".equals(subject))
		{
			result += "��ѧ�������Ҫ��д��";
		}
		
		if(null == page || "".equals(page))
		{
			result += "������̬�����Ҫ��д��";
		}
		
		if(null == list || "".equals(list))
		{
			result += "��Ŀ¼����Ҫ��д��";
		}
		
		if(null == content || "".equals(content))
		{
			result += "���ݼ�����Ҫ��д��";
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
			
			BookDao.saveBook(book);//����Ŀ��Ϣ�������ݿ�
			
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body><h1>��Ŀ¼��ɹ�</h1><br>");
			out.println("<a href = 'http://localhost:8080/MyLibrary/booksEntering.jsp' target= '_blank'>����</a><br>");
			
			out.flush();
		}
		
		if(result != "")
		{
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>"+ result +"<br>");
			out.println("<a href = 'http://localhost:8080/MyLibrary/booksEntering.jsp' target= '_blank'>����</a><br>");
			
			out.flush();
		}
			
		
	}

}
