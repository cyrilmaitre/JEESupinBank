package com.supinfo.supinfbank.web.manager.customer;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Customer;
import com.supinfo.supinfbank.entity.validator.CustomerValidator;
import com.supinfo.supinfbank.web.manager.SessionManager;


@ManagedBean(name="addCustomer")
@RequestScoped
public class AddCustomerManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private CustomerValidator validatorCustomer;
	@EJB private DaoFactory daoFactory;
	private Customer customer = new Customer();
	
	
	//********************
	// Getters - Setters
	//********************
	public Customer getCustomer() 
	{
		return customer;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	public SessionManager getSessionManager() 
	{
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) 
	{
		this.sessionManager = sessionManager;
	}

	public CustomerValidator getValidatorCustomer() 
	{
		return validatorCustomer;
	}
	
	public void setValidatorCustomer(CustomerValidator validatorCustomer)
	{
		this.validatorCustomer = validatorCustomer;
	}
	
	
	//********************
	// Methods
	//********************
	public String nextStep()
	{
		if(validatorCustomer.validateObject(customer))
		{
			this.getSessionManager().setCustomerManaged(customer);
			this.getSessionManager().setCustomerManagedShouldBeAdded(true);
			return "addaccount";
		}
		return null;
	}
}
