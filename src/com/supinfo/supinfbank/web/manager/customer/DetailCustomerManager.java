package com.supinfo.supinfbank.web.manager.customer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Account;
import com.supinfo.supinfbank.web.manager.SessionManager;


@ManagedBean(name="detailcustomer")
@RequestScoped
public class DetailCustomerManager implements Serializable
{
	private static final long serialVersionUID = 1L;

	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private DaoFactory daoFactory;
	private HtmlDataTable dataTable;
	
	
	//********************
	// Init
	//********************
	@PostConstruct
	public void init()
	{
		this.sessionManager.setCustomerManaged(daoFactory.getCustomerDao().findById(this.sessionManager.getCustomerManaged().getIdUser()));
	}
	
	
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

	public HtmlDataTable getDataTable() 
	{
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable)
	{
		this.dataTable = dataTable;
	}

	
	//********************
	// Methods
	//********************
	public String addAccount()
	{
		if(this.getSessionManager().isAdvisor())
		{
			this.getSessionManager().setCustomerManagedShouldBeAdded(false);
			return "addaccount";
		}
		return null;
	}
	
	public String addMoney()
	{
		if(this.getSessionManager().isAdvisor())
		{
			this.getSessionManager().setAccountManaged((Account) this.dataTable.getRowData());
			return "addmoney";
		}
		return null;
	}
	
	public String showOperations()
	{
		if(this.getSessionManager().isCustomer())
		{
			this.getSessionManager().setAccountManaged(this.daoFactory.getAccountDao().findById(((Account) this.dataTable.getRowData()).getIdAccount()));
			return "detailaccount";
		}
		return null;
	}
}
