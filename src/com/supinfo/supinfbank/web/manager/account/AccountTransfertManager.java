package com.supinfo.supinfbank.web.manager.account;

import java.io.Serializable;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Account;
import com.supinfo.supinfbank.entity.Operation;
import com.supinfo.supinfbank.entity.validator.OperationValidator;
import com.supinfo.supinfbank.web.manager.SessionManager;

@ManagedBean(name="accountTransfert")
@RequestScoped
public class AccountTransfertManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private DaoFactory daoFactory;
	@EJB private OperationValidator operationValidator;
	private Account accountSender;
	private Account accountReceiver;
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

	public Account getAccountSender() 
	{
		return accountSender;
	}

	public void setAccountSender(Account accountSender) 
	{
		this.accountSender = accountSender;
	}

	public Account getAccountReceiver() 
	{
		return accountReceiver;
	}

	public void setAccountReceiver(Account accountReceiver) 
	{
		this.accountReceiver = accountReceiver;
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
		boolean accountsValid = true;
		if(this.accountSender.equals(this.accountReceiver))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, "From account and To account must be different", null );
			context.addMessage("myform:accountsFrom", message);
			accountsValid = false;
		}	
		
		if(operationValidator.validateObject(this.operation) && accountsValid)
		{
			// Sender
			Operation senderOperation = new Operation();
			senderOperation.setAmount(-this.operation.getAmount());
			senderOperation.setWording(this.operation.getWording());
			senderOperation.setDate(Calendar.getInstance());
			senderOperation.setAccount(this.accountSender);
			this.daoFactory.getOperationDao().add(senderOperation);
			
			this.accountSender.setBalance(this.accountSender.getBalance() + senderOperation.getAmount());
			this.daoFactory.getAccountDao().update(this.accountSender);
			
			// Receiver
			Operation receiverOperation = new Operation();
			receiverOperation.setAmount(this.operation.getAmount());
			receiverOperation.setWording(this.operation.getWording());
			receiverOperation.setDate(Calendar.getInstance());
			receiverOperation.setAccount(this.accountReceiver);
			this.daoFactory.getOperationDao().add(receiverOperation);
			
			this.accountReceiver.setBalance(this.accountReceiver.getBalance() + receiverOperation.getAmount());
			this.daoFactory.getAccountDao().update(this.accountReceiver);
			
			return "detailcustomer";
		}
		return null;
	}

	public String cancel()
	{
		return "detailcustomer";
	}
}
