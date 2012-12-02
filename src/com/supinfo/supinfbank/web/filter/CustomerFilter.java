package com.supinfo.supinfbank.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supinfbank.web.manager.SessionManager;

@WebFilter(urlPatterns="/customer/*")
public class CustomerFilter implements Filter
{
	//********************
	// Constructor
	//********************
	public CustomerFilter()
	{
		
	}
	
	
	//********************
	// Methods
	//********************
	@Override
	public void destroy() 
	{
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		SessionManager session = (SessionManager) ((HttpServletRequest)request).getSession(true).getAttribute("sessionManager");
		if(session.isCustomer() || session.isAdvisor())
			chain.doFilter(request, response);
		else
			((HttpServletResponse)response).sendRedirect("/supinBank");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{

	}
}
