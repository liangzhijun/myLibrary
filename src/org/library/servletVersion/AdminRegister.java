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
			result += "���䲻����Ϊ��";
		}
		
		if(null == name || "".equals(name))
		{
			result += "����������Ϊ�գ�";
		}
		
		if(null == username || "".equals(username))
		{
			result += " �û���������Ϊ�գ�";
		}
		
		else if(username.length() < 4 || username.length() > 10)
		{
			result += " �û�������Ӧ����4��10֮�䣡";
		}
		
		//����ĳ��Ⱦ�����4---10֮��
		
		if(!password.equals(repassword))
		{
			result += "���벻һ�£�";
		}
		
		if(password == null || password.length() < 4 || password.length() > 10)
		{
			result += "����ĳ�������4---10֮��";
		}
		
		if(null == gender)
		{
			result += "�Ա����Ҫѡ��";
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
			
			
			
			UserDao.saveUser(user);//��Ԫ�ش������ݿ�
			
			out.println("<html><head><title>Login Result</title></head>");

			out.println("<body><h1>ע��ɹ�</h1><br>");
			out.println("<a href = '/SignIn.jsp' target= '_blank'>���ص�¼ҳ��</a><br>");
			
			out.flush();
		}
		
		else//�û���д������Ҫ�����Ҫ��������Ի���
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