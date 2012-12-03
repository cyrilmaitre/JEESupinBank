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
import com.supinfo.supinfbank.entity.Customer;
import com.supinfo.supinfbank.entity.validator.CustomerValidator;


@Path("/customer")
public class CustomerResource
{
	@EJB private DaoFactory daoFactory;
	@EJB private CustomerValidator validatorCustomer;
	
	public CustomerResource() throws NamingException 
	{
		this.daoFactory = (DaoFactory) (new InitialContext()).lookup("java:global/app/webapp/DaoFactory!com.supinfo.supinfbank.dao.DaoFactory");
		this.validatorCustomer = (CustomerValidator) (new InitialContext()).lookup("java:global/app/webapp/CustomerValidator!com.supinfo.supinfbank.entity.validator.CustomerValidator");
	}
	
	@GET 
	@Path("/findCustomer/{id}")
	public Customer findCustomer(@PathParam("id") int id)
	{
		return daoFactory.getCustomerDao().findById(id);
	}
	
	@GET 
	@Path("/findAllCustomer")
	public List<Customer> findAllCustomer()
	{
		return daoFactory.getCustomerDao().findAll(true);
	}
	
	@POST
	@Path("/addCustomer")
	public void addCustomer(Customer customer)
	{
		if(validatorCustomer.validateObject(customer) && customer.getAccounts().size() > 0)
		{
			customer = this.daoFactory.getCustomerDao().add(customer);
			for (Account current : customer.getAccounts()) 
			{
				if(current.getIdAccount() == 0)
				{
					current.setCustomer(customer);
					this.daoFactory.getAccountDao().add(current);
				}
			}
		}
	}
}
