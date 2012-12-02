package com.supinfo.supinfbank.entity.validator;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class ValidatorFactory 
{
	//********************
	// Attributes
	//********************
	@EJB private AccountValidator accountValidator;
	@EJB private AccountTypeValidator accountTypeValidator;
	@EJB private AdvisorValidator advisorValidator;
	@EJB private CustomerValidator customerValidator;
	@EJB private OperationValidator operationValidator;
	@EJB private UserValidator userValidator;
	
	
	//********************
	// Constructor
	//********************
	public ValidatorFactory()
	{
		
	}
	
	
	//********************
	// Methods
	//********************
	public AccountValidator getAccountValidator()
	{
		return accountValidator;
	}
	
	public AccountTypeValidator getAccountTypeValidator()
	{
		return accountTypeValidator;
	}
	
	public AdvisorValidator getAdvisorValidator()
	{
		return advisorValidator;
	}
	
	public CustomerValidator getCustomerValidator()
	{
		return customerValidator;
	}
	
	public OperationValidator getOperationValidator()
	{
		return operationValidator;
	}
	
	public UserValidator getUserValidator()
	{
		return userValidator;
	}
}
