package org.library.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.library.dao.BookDao;
import org.library.model.Book;

public class Retrieval extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String strText = req.getParameter("strText");
		String strSearchType = req.getParameter("strSearchType");
		
		Book book = BookDao.retrieval(strText, strSearchType);
		
		if(book == null)
		{
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>ͼ���û����Ҫ�ҵġ�" + strText +"���鿯<br>");
			out.println("<a href = 'http://localhost:8080/MyLibrary/Retrieval.jsp' target= '_blank'>����</a><br>");
			
			out.flush();
		}
		
		else
		{
			req.setAttribute("book", book);
			
			List<Book> list =  BookDao.bookinfos(book.getTitle());
			req.setAttribute("list", list);
			
			RequestDispatcher rd = req.getRequestDispatcher("bookinfo.jsp");
			rd.forward(req, resp);	
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
		
	
}
