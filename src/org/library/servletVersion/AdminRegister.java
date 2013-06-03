package org.library.servletVersion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.library.dao.UserDao;
import org.library.model.User;

public class AdminRegister extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String gender = request.getParameter("gender");
		String unit = request.getParameter("unit");
		String phone = request.getParameter("phone");
		
		String result = "";
		
		if(null == email || "".equals(email))
		{
			result += "邮箱不可以为空";
		}
		
		if(null == name || "".equals(name))
		{
			result += "姓名不可以为空！";
		}
		
		if(null == username || "".equals(username))
		{
			result += " 用户名不可以为空！";
		}
		
		else if(username.length() < 4 || username.length() > 10)
		{
			result += " 用户名长度应该是4和10之间！";
		}
		
		//密码的长度均须在4---10之间
		
		if(!password.equals(repassword))
		{
			result += "密码不一致！";
		}
		
		if(password == null || password.length() < 4 || password.length() > 10)
		{
			result += "密码的长度须在4---10之间";
		}
		
		if(null == gender)
		{
			result += "性别必须要选！";
		}
			
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		if(result == "")
		{	
			User user = new User();
			
			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setUnit(unit);
			user.setPhone(phone);
			
			
			
			UserDao.saveUser(user);//将元素存入数据库
			
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body><h1>注册成功</h1><br>");
			out.println("<a href = '/SignIn.jsp' target= '_blank'>返回登录页面</a><br>");
			
			out.flush();
		}
		
		else//用户填写不符合要求的需要弹出警告对话框
		{
			out.println("<html><head><title>Login Result</title></head>");
			
			out.println("<body>"+ result + "</body>" );
			
			out.flush();
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
}