package com.supinfo.supinfbank.web.manager.customer;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.Customer;
import com.supinfo.supinfbank.web.manager.SessionManager;


@ManagedBean(name="listCustomer")
@RequestScoped
public class ListCustomerManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//********************
	// Attributes
	//********************
	@ManagedProperty(value = "#{sessionManager}")
	private SessionManager sessionManager;
	
	@EJB private DaoFactory daoFactory;
	private HtmlDataTable dataTable;
	private List<Customer> customers;
	
	
	//********************
	// Constructor
	//********************
	@PostConstruct
	public void init()
	{
		customers = daoFactory.getCustomerDao().findAll();
	}
	
	
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

	public List<Customer> getCustomers()
	{
		return customers;
	}

	public void setCustomers(List<Customer> customers)
	{
		this.customers = customers;
	}
	
	public HtmlDataTable getDataTable()
	{
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) 
	{
		this.dataTable = dataTable;
	}


	//********************
	// Methods
	//********************
	public String showDetail()
	{
		this.getSessionManager().setCustomerManaged(this.daoFactory.getCustomerDao().findById(((Customer) dataTable.getRowData()).getIdUser()));
		this.getSessionManager().setCustomerManagedShouldBeAdded(false);
		return "detailcustomer";
	}
}
