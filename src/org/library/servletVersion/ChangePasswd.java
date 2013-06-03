package org.library.servletVersion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.library.dao.UserDao;
import org.library.model.User;

public class ChangePasswd extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String repassword = req.getParameter("repassword");
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		if(!password.equals(user.getPassword()))
		{
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter out = resp.getWriter();

			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>" + "ԭ������������" + "</body>");

			out.flush();
			
			return;
		}
		else if(newPassword == null || newPassword.length() < 4 || newPassword.length() > 10)
		{			
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter out = resp.getWriter();

			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>" + "������ĳ�������4---10֮��" + "</body>");

			out.flush();
			
			return;
		}
		else if(!newPassword.equals(repassword))
		{
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter out = resp.getWriter();

			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>" + "�������벻һ��" + "</body>");

			out.flush();
			
			return;
		}
		
		else 
		{
			String username = user.getUsername();
			UserDao.changePasswd(username, repassword);
			
			user.setPassword(newPassword);
			
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter out = resp.getWriter();

			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body>" + "��������ɹ������<a href='http://localhost:8080/MyLibrary/myLibrary.jsp>����</a>" + "</body>");

			out.flush();
			
			return;
			
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
