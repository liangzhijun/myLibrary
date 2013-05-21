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

public class modifyuserinfo extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String mobilePhone = req.getParameter("mobilePhone");
		String phone = req.getParameter("phone");
		String address= req.getParameter("address");
		String email = req.getParameter("email");
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		String username = user.getUsername();
		
		UserDao.modifyUserinfo(username, mobilePhone, phone, address, email);
		user.setMobilePhone(mobilePhone);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		
		
		resp.setContentType("text/html; charset=utf-8");

		PrintWriter out = resp.getWriter();

		out.println("<html><head><title>Login Result</title></head>");

		out.println("<body>" + "个人信息修改成功，点击<a href='http://localhost:8080/MyLibrary/myLibrary.jsp'_blank'>返回</a>" + "</body>");

		out.flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		
		this.doGet(req, resp);
	}
}
