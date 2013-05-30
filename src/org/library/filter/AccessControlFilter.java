package org.library.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.library.dao.UriDao;
import org.library.model.User;

public class AccessControlFilter implements Filter
{
	private Map<String, String> uris;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("Filter Started!");
		uris = UriDao.findAll();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;	
		
		String requestURI = req.getRequestURI();
		
		
		HttpSession session = req.getSession();
		
		//������֤��URI
		if(!uris.containsKey(requestURI))
		{
			chain.doFilter(request,response);
			return;										//do not need to check
		}
		
		//����֤��URI
		User user = (User)session.getAttribute("user");
		

		//��¼��֤
		if(user == null)
		{
			//���µ�¼
			((HttpServletResponse)response).sendRedirect("SignIn.jsp");
			return;
		}	
		
		//�ѵ�¼����ɫ��֤
		String role = uris.get(requestURI);	
		String userRole = user.getRole();
		
		if(!role.equals(userRole))
		{
			//���µ�¼
			((HttpServletResponse)response).sendRedirect("SignIn.jsp");
			return;
		}
		
		//��֤ͨ��
		chain.doFilter(request,response);		
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}