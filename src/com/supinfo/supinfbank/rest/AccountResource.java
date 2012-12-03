package com.supinfo.supinfbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Account;
import com.supinfo.supinfbank.entity.validator.AccountValidator;



@Path("/account")
public class AccountResource
{
	@EJB private DaoFactory daoFactory;
	@EJB private AccountValidator validatorAccount;
	
	public AccountResource() throws NamingException 
	{
		this.daoFactory = (DaoFactory) (new InitialContext()).lookup("java:global/app/webapp/DaoFactory!com.supinfo.supinfbank.dao.DaoFactory");
		this.validatorAccount = (AccountValidator) (new InitialContext()).lookup("java:global/app/webapp/AccountValidator!com.supinfo.supinfbank.entity.validator.AccountValidator");
	}
	
	@GET
	@Path("/customerAccounts/{idCustomer}")
	public List<Account> getCustomerAccounts(@PathParam("idCustomer") int idCustomer)
	{
		return (List<Account>) daoFactory.getCustomerDao().findById(idCustomer).getAccounts();
	}
	
	@POST
	@Path("/addAccount")
	public void addAccount(Account account)
	{
		if(validatorAccount.validateObject(account) && account.getCustomer() != null)
			this.daoFactory.getAccountDao().addAccount(account);
	}
}
