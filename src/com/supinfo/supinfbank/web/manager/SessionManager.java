package com.supinfo.supinfbank.web.manager;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.supinfo.supinfbank.entity.Account;
import com.supinfo.supinfbank.entity.Advisor;
import com.supinfo.supinfbank.entity.Customer;
import com.supinfo.supinfbank.entity.User;


@ManagedBean(name="sessionManager")
@SessionScoped
public class SessionManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributs
	//********************
	private User userAuthenticated = null;
	private Customer customerManaged = null;
	private boolean customerManagedShouldBeAdded = false;
	private Account accountManaged = null;
		
	
	//********************
	// Getters - Setters
	//********************	
	public User getUserAuthenticated() 
	{
		return userAuthenticated;
	}
	
	public void setUserAuthenticated(User userAuthenticated) 
	{
		this.userAuthenticated = userAuthenticated;
	}
	
	public Customer getCustomerManaged() 
	{
		return customerManaged;
	}
	
	public void setCustomerManaged(Customer customerManaged) 
	{
		this.customerManaged = customerManaged;
	}
	
	public Account getAccountManaged() 
	{
		return accountManaged;
	}
	
	public void setAccountManaged(Account accountManaged) 
	{
		this.accountManaged = accountManaged;
	}
	
	public boolean isCustomerManagedShouldBeAdded() 
	{
		return customerManagedShouldBeAdded;
	}

	public void setCustomerManagedShouldBeAdded(boolean customerManagedShouldBeAdded) 
	{
		this.customerManagedShouldBeAdded = customerManagedShouldBeAdded;
	}
	
	public boolean isAdvisor()
	{
		if(this.userAuthenticated != null && userAuthenticated instanceof Advisor)
			return true;
		else
			return false;
	}
	
	public boolean isCustomer()
	{
		if(this.userAuthenticated != null && userAuthenticated instanceof Customer)
			return true;
		else
			return false;
	}
	

	//********************
	// Methods
	//********************
	public void reset()
	{
		this.userAuthenticated = null;
		this.customerManaged = null;
		this.customerManagedShouldBeAdded = false;
		this.accountManaged = null;
	}
}
