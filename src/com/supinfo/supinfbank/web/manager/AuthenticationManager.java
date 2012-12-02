package com.supinfo.supinfbank.web.manager;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Customer;
import com.supinfo.supinfbank.entity.User;
import com.supinfo.supinfbank.util.Tools;


@ManagedBean(name="auth")
@RequestScoped
public class AuthenticationManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributs
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private DaoFactory daoFactory;
	private String email;
	private String password;
	private boolean authenticationFailed = false;
		
	
	//********************
	// Getters - Setters
	//********************
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public boolean isAuthenticationFailed()
	{
		return authenticationFailed;
	}
	public void setAuthenticationFailed(boolean authenticationFailed)
	{
		this.authenticationFailed = authenticationFailed;
	}
	
	public SessionManager getSessionManager() 
	{
		return sessionManager;
	}
	
	public void setSessionManager(SessionManager sessionManager)
	{
		this.sessionManager = sessionManager;
	}
	
	
	//********************
	// Methods
	//********************
	public String login()
	{		
		if(this.email != null && this.password != null)
		{
			User authenticatedUser = this.daoFactory.getUserDao().authenticate(new User(email, Tools.sha1(password)));
			if(authenticatedUser != null)
			{
				this.setAuthenticationFailed(false);
				this.getSessionManager().setUserAuthenticated(authenticatedUser);
				if(this.getSessionManager().isAdvisor())
				{
					return "listcustomer";
				}
				else if(this.getSessionManager().isCustomer())
				{
					this.getSessionManager().setCustomerManaged((Customer) authenticatedUser);
					return "detailcustomer";
				}
				else
				{
					return "index";
				}
			}
		}
		this.setAuthenticationFailed(true);
		return null;
	}
	
	public String logout()
	{
		this.getSessionManager().reset();
		return "index";
	}
}
