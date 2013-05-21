package org.library.filter;

import java.io.IOException;
import java.util.Set;

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
//123
public class CopyOfLoginFilter implements Filter
{
	private Set<String> uris;

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
		
		if(!uris.contains(requestURI))
		{
			chain.doFilter(request,response);			//do not need to check
		}
		
		//need to check
		User user = (User)session.getAttribute("user");
		
		if(user != null)
		{
			chain.doFilter(request,response);			//已登录,允许通过
		}	

		//重新登录
		((HttpServletResponse)response).sendRedirect("SignIn.jsp");
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}
