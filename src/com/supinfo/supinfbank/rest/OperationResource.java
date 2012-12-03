package com.supinfo.supinfbank.rest;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Operation;
import com.supinfo.supinfbank.entity.validator.OperationValidator;

@Path("/operation")
public class OperationResource 
{
	@EJB private DaoFactory daoFactory;
	@EJB private OperationValidator validatorOperation;
	
	public OperationResource() throws NamingException 
	{
		this.daoFactory = (DaoFactory) (new InitialContext()).lookup("java:global/app/webapp/DaoFactory!com.supinfo.supinfbank.dao.DaoFactory");
		this.validatorOperation = (OperationValidator) (new InitialContext()).lookup("java:global/app/webapp/OperationValidator!com.supinfo.supinfbank.entity.validator.OperationValidator");
	}
		
	@POST
	@Path("/addOperation")
	public void addOperation(Operation opration)
	{
		if(validatorOperation.validateObject(opration) && opration.getAccount() != null)
		{
			this.daoFactory.getOperationDao().add(opration);
		}
	}
}
