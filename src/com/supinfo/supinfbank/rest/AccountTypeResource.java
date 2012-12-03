package com.supinfo.supinfbank.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.AccountType;

@Path("/accounttype")
public class AccountTypeResource 
{	
	@EJB private DaoFactory daoFactory;
	
	public AccountTypeResource() throws NamingException 
	{
        this.daoFactory = (DaoFactory) (new InitialContext()).lookup("java:global/app/webapp/DaoFactory!com.supinfo.supinfbank.dao.DaoFactory");
	}
	
	@GET 
	@Path("/available")
	public List<AccountType> getAvailableAccountType()
	{
		return daoFactory.getAccountTypeDao().findAll(true);
	}
}
