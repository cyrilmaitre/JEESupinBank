package com.supinfo.supinfbank.web.manager.account;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Account;
import com.supinfo.supinfbank.entity.validator.AccountValidator;
import com.supinfo.supinfbank.web.manager.SessionManager;


@ManagedBean(name="addAccount")
@RequestScoped
public class AddAccountManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private DaoFactory daoFactory;
	@EJB private AccountValidator validatorAccount;
	private Account account = new Account();
	
		
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
	
	public AccountValidator getValidatorAccount() 
	{
		return validatorAccount;
	}

	public void setValidatorAccount(AccountValidator validatorAccount)
	{
		this.validatorAccount = validatorAccount;
	}
	
	public Account getAccount() 
	{
		return account;
	}
	
	public void setAccount(Account account) 
	{
		this.account = account;
	}	
	
	
	//********************
	// Methods
	//********************
	public String submit()
	{
		if(this.getSessionManager().getCustomerManaged() == null)
			return "index";
		
		if(validatorAccount.validateObject(account))
		{
			if(getSessionManager().isCustomerManagedShouldBeAdded())
			{
				this.getSessionManager().setCustomerManaged(this.daoFactory.getCustomerDao().addCustomer(this.getSessionManager().getCustomerManaged()));
				this.getSessionManager().setCustomerManagedShouldBeAdded(false);
			}
			
			this.account.setCustomer(this.getSessionManager().getCustomerManaged());
			this.account = this.daoFactory.getAccountDao().addAccount(this.account);
			this.getSessionManager().getCustomerManaged().getAccounts().add(this.account); // Try use em.refresh
			return "detailcustomer";
		}
		return null;
	}
	
	public String cancel()
	{
		if(this.getSessionManager().isCustomerManagedShouldBeAdded())
		{
			this.getSessionManager().setCustomerManaged(null);
			this.getSessionManager().setCustomerManagedShouldBeAdded(false);
			return "index";
		}
		else
		{
			// Return to detail of current customer
			return "index";
		}
	}
}
