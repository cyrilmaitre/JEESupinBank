package com.supinfo.supinfbank.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.supinfo.supinfbank.util.ToolsBank;


@Entity
@Table(name = "supinbank_account")
@XmlRootElement(name = "Account")
public class Account implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	//********************
	// Attributes
	//********************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(name = "idAccount")
	private int idAccount;
	
	@NotNull @Size(min = 1, message = "Name is required")
	@XmlElement(name = "name")
	private String name;
	
	@Digits(fraction = 2, integer = 8, message = "Must be a number")
	@XmlElement(name = "balance")
	private double balance;
	
	@XmlElement(name = "bban")
	private String bban;
	
	@ManyToOne
    @JoinColumn(name="idUser")
	@XmlElement(name = "customer")
    private Customer customer;
	
	@ManyToOne
    @JoinColumn(name="idAccountType")
	@NotNull(message = "Account Type is required")
	@XmlElement(name = "accounttype")
    private AccountType accountType;
	
	@OneToMany(mappedBy = "account")
	@LazyCollection(LazyCollectionOption.FALSE)
	@XmlElement(name = "operations")
    private Collection<Operation> operations;	
	
	
	//********************
	// Constructors
	//********************
	public Account()
	{
		this(null, 0, null, null);
	}
	
	public Account(String name, double balance, Customer customer, AccountType accountType)
	{
		this.name = name;
		this.balance = balance;
		this.customer = customer;
		this.accountType = accountType;
	}
	
	public Account(int idAccount, String name, double balance, Customer customer, AccountType accountType)
	{
		this(name, balance, customer, accountType);
		this.idAccount = idAccount;
	}

	
	//********************
	// Getters - Setters
	//********************
	public int getIdAccount() 
	{
		return idAccount;
	}

	public void setIdAccount(int idAccount)
	{
		this.idAccount = idAccount;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	public String getBban()
	{
		return bban;
	}

	public void setBban() 
	{
		this.bban = ToolsBank.computeBBAN(this.idAccount);
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}

	public AccountType getAccountType() 
	{
		return accountType;
	}

	public void setAccountType(AccountType accountType) 
	{
		this.accountType = accountType;
	}
	
	public Collection<Operation> getOperations() 
	{
		return operations;
	}

	public void setOperations(Collection<Operation> operations)
	{
		this.operations = operations;
	}

	
	//********************
	// Others
	//********************
	@Override
    public String toString()
    {
        return this.name;
    }

    @Override
    public boolean equals(Object myObject)
    {
        if(myObject instanceof Account)
        {
            if(this.getIdAccount() == ((Account)myObject).getIdAccount())
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }
}
