package com.supinfo.supinfbank.web.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.AccountType;
import com.supinfo.supinfbank.entity.Advisor;
import com.supinfo.supinfbank.util.Constants;
import com.supinfo.supinfbank.util.Tools;


@WebFilter(urlPatterns="/*")
public class BasicFilter implements Filter
{
	//********************
	// Attributs
	//********************
	@EJB DaoFactory daoFactory;
	
	
	//********************
    // Constructor
    //********************
	public BasicFilter()
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
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{
		// Init database here for debug purpose bug must be removed for production environement
		if(daoFactory.getAccountTypeDao().findAll().size() == 0)
		{
			for (String name : Constants.ACCOUNTYPE)
			{
				daoFactory.getAccountTypeDao().add(new AccountType(name));
			}
		}
		
		if(daoFactory.getAdvisorDao().findAll().size() == 0)
		{
			Advisor firstAdvisor = new Advisor("cyril.maitre@supinfo.com", Tools.sha1("supinfo"));
			daoFactory.getAdvisorDao().add(firstAdvisor);
		}
	}
}
