package com.supinfo.supinfbank.web.manager.account;

import java.io.Serializable;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Account;
import com.supinfo.supinfbank.entity.Operation;
import com.supinfo.supinfbank.entity.validator.OperationValidator;
import com.supinfo.supinfbank.web.manager.SessionManager;

@ManagedBean(name="addMoney")
@RequestScoped
public class AddMoneyManager implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private DaoFactory daoFactory;
	@EJB private OperationValidator operationValidator;
	private Operation operation = new Operation();

	
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
	
	public Operation getOperation() 
	{
		return operation;
	}

	public void setOperation(Operation operation)
	{
		this.operation = operation;
	}
	

	//********************
	// Methods
	//********************
	public String submit()
	{
		if(operationValidator.validateObject(this.operation))
		{
			this.operation.setAccount(this.getSessionManager().getAccountManaged());
			this.operation.setDate(Calendar.getInstance());
			this.daoFactory.getOperationDao().add(operation);
			
			Account updateAccount = this.getSessionManager().getAccountManaged();
			updateAccount.setBalance(updateAccount.getBalance() + this.operation.getAmount());
			this.daoFactory.getAccountDao().update(updateAccount);
			
			this.getSessionManager().setAccountManaged(null);
			return "detailcustomer";
		}
		return null;
	}
	
	public String cancel()
	{
		this.getSessionManager().setAccountManaged(null);
		return "detailcustomer";
	}
}
