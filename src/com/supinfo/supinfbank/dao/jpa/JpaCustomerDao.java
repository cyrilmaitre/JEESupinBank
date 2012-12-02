package com.supinfo.supinfbank.dao.jpa;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import com.supinfo.supinfbank.dao.CustomerDao;
import com.supinfo.supinfbank.entity.Customer;
import com.supinfo.supinfbank.util.Tools;


@Stateless
public class JpaCustomerDao extends AbstractJpaDao<Customer> implements CustomerDao
{
	public JpaCustomerDao() 
	{
		super(Customer.class);
	}
	
	@Override
	public Customer addCustomer(Customer newCustomer) 
	{
		String newPassword = Tools.generatePassword();
		newCustomer.setPassword(Tools.sha1(newPassword));
		this.sendEmailAsync(newCustomer, newPassword);
    	return this.add(newCustomer);
	}
	
	@Asynchronous
	public Future<Boolean> sendEmailAsync(final Customer customer, final String password) 
	{
		Thread sendEmailThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				Tools.sendEmail(customer.getEmail(), "Register successfully", "You have been registred successfully to SupinBank. Welcome. Your password: " + password);
			}
		});
		sendEmailThread.start();
		return new AsyncResult<Boolean>(true);
	}

}
