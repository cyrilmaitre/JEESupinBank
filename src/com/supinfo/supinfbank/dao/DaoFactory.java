package com.supinfo.supinfbank.dao;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class DaoFactory 
{
	//********************
	// Attributes
	//********************
	@EJB private AccountDao accountDao;
	@EJB private AccountTypeDao accountTypeDao;
	@EJB private AdvisorDao advisorDao;
	@EJB private CustomerDao customerDao;
	@EJB private OperationDao operationDao;
	@EJB private UserDao userDao;
	
	
	//********************
    // Constructor
    //********************
	public DaoFactory()
	{
		
	}
	
	//********************
    // Methods
    //********************
	public AccountDao getAccountDao()
	{
		return accountDao;
	}
	
	public AccountTypeDao getAccountTypeDao()
	{
		return accountTypeDao;
	}
	
	public AdvisorDao getAdvisorDao()
	{
		return advisorDao;
	}
	
	public CustomerDao getCustomerDao()
	{
		return customerDao;
	}
	
	public OperationDao getOperationDao()
	{
		return operationDao;
	}
	
	public UserDao getUserDao()
	{
		return userDao;
	}
}
