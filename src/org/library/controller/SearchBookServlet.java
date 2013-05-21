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

public class SearchBookServlet extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String strText = req.getParameter("strText");
		String strSearchType = req.getParameter("strSearchType");
		
		List<Book> bookList = BookDao.retrieval(strText, strSearchType);
		
		if(bookList == null)
		{
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>图书馆没有你要找的“" + strText +"”书刊<br>");
			out.println("<a href = 'http://localhost:8080/MyLibrary/Retrieval.jsp' target= '_blank'>返回</a><br>");
			
			out.flush();
		}
		
		else
		{
			req.setAttribute("bookList", bookList);
			
			RequestDispatcher rd = req.getRequestDispatcher("/SearchResult.jsp");
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
