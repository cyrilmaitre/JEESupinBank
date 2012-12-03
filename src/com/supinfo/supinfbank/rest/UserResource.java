package com.supinfo.supinfbank.rest;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.User;


@Path("/user")
public class UserResource 
{
	@EJB private DaoFactory daoFactory;
	
	public UserResource() throws NamingException 
	{
        this.daoFactory = (DaoFactory) (new InitialContext()).lookup("java:global/app/webapp/DaoFactory!com.supinfo.supinfbank.dao.DaoFactory");
	}
	
	@POST
	@Path("/authenticate")
	public User authenticate(User user)
	{
		return this.daoFactory.getUserDao().authenticate(user);
	}
}
