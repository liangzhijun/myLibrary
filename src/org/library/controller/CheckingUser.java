package org.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.library.dao.UserDao;
import org.library.model.User;

public class CheckingUser extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = UserDao.findUser(username);

		if (user != null && password.equals(user.getPassword()))
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			response.sendRedirect("myLibrary");
		}

		else
		{
			response.setContentType("text/html; charset=utf-8");

			PrintWriter out = response.getWriter();

			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>" + "µÇÂ¼Ãû»òÃÜÂë´íÎó" + "</body>");

			out.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.doGet(req, resp);
	}
}
