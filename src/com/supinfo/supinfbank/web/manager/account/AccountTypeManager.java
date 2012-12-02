package com.supinfo.supinfbank.web.manager.account;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.AccountType;


@ManagedBean(name="accountType")
@RequestScoped
public class AccountTypeManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributs
	//********************
	@EJB private DaoFactory daoFactory;
	private List<AccountType> accountTypes;
	
	
	//********************
	// Constructor
	//********************
	@PostConstruct
	public void init()
	{
		accountTypes = daoFactory.getAccountTypeDao().findAll();
	}
	
	
	//********************
	// Getters - Setters
	//********************
	public List<AccountType> getAccountTypes()
	{
		return accountTypes;
	}


	public void setAccountTypes(List<AccountType> accountTypes) 
	{
		this.accountTypes = accountTypes;
	}	
}
