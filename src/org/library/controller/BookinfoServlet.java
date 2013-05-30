package org.library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.library.dao.BookDao;
import org.library.model.Book;

public class BookinfoServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String title = req.getParameter("title");
		
		Book book = BookDao.getBookinfo(title);
		
		req.setAttribute("book", book);
		
		List<Book> list =  BookDao.bookinfos(title);
		req.setAttribute("list", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("bookinfo.jsp");
		
		rd.forward(req, resp);						
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.doGet(req, resp);
	}
}
