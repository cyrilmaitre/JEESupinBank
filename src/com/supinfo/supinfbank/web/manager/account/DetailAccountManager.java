package com.supinfo.supinfbank.web.manager.account;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.supinfo.supinfbank.web.manager.SessionManager;


@ManagedBean(name="detailAccount")
@RequestScoped
public class DetailAccountManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;

	
	//********************
	// Getters - Setters
	//********************
	public SessionManager getSessionManager() 
	{
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager)
	{
		this.sessionManager = sessionManager;
	}
}
