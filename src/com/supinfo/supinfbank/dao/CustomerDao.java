package com.supinfo.supinfbank.dao;

import javax.ejb.Local;

import com.supinfo.supinfbank.entity.Customer;


@Local
public interface CustomerDao extends Dao<Customer>
{
	Customer addCustomer(Customer customer);
}
