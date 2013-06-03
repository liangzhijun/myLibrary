package org.library.filter;

import java.io.IOException;
import java.util.Map;
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

public class AccessControlFilter implements Filter
{
	private Map<String, String> uris;
	private Set<String> shareUris;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("Filter Started!");
		uris = UriDao.findAll();//返回一个包含所有以uri为key，role(角色)为value的map
		shareUris = UriDao.findShareUris();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;	
		
		String requestURI = req.getRequestURI();
		
		
		HttpSession session = req.getSession();
		
		//无需验证的URI
		if(!uris.containsKey(requestURI))
		{
			chain.doFilter(request,response);
			return;										//do not need to check
		}
		
		//需验证的URI
		User user = (User)session.getAttribute("user");
		

		//登录验证
		if(user == null)
		{
			//重新登录
			((HttpServletResponse)response).sendRedirect("SignIn.jsp");
			return;
		}	
		
		//无需验证用户角色的URI，URI是共享的
		if(shareUris.contains(requestURI))
		{	
			//验证通过
			System.out.println("Share-URI");
			chain.doFilter(request,response);	
			return;
		}
		
		String role = uris.get(requestURI);	//方法返回请求uri相对role(角色)
		String userRole = user.getRole();
		
		//已登录，角色验证
		if(!role.equals(userRole))
		{
			//重新登录
			((HttpServletResponse)response).sendRedirect("SignIn.jsp");
			return;
		}
		
		//验证通过
		chain.doFilter(request,response);		
		
	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}
