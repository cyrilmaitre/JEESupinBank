package com.supinfo.supinfbank.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "supinbank_operation")
public class Operation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOperation;
	
	@NotNull @Size(min = 1, message = "Wording is required")
	private String wording;
	
	@Digits(fraction = 2, integer = 8, message = "Must be a number")
	@DecimalMin(value = "1", message = "Amount must be greater than 0")
	private double amount;
	
	@Temporal(TemporalType.DATE)
	private Calendar date;
	
	@ManyToOne
    @JoinColumn(name="idAccount")
    private Account account;

	
	//********************
	// Constructors
	//********************
	public Operation()
	{
		this(null, Calendar.getInstance(), 0, null);
	}
	
	public Operation(String wording, Calendar date, double amount, Account account)
	{
		this.wording = wording;
		this.date = date;
		this.amount = amount;
		this.account = account;
	}
	
	public Operation(int idOperation, String wording, Calendar date, double amount, Account account)
	{
		this(wording, date, amount, account);
		this.idOperation = idOperation;
	}
	
	
	//********************
	// Getters - Setters
	//********************
	public int getIdOperation() 
	{
		return idOperation;
	}

	public void setIdOperation(int idOperation) 
	{
		this.idOperation = idOperation;
	}

	public String getWording() 
	{
		return wording;
	}

	public void setWording(String wording)
	{
		this.wording = wording;
	}

	public Calendar getDate() 
	{
		return date;
	}

	public void setDate(Calendar date)
	{
		this.date = date;
	}

	public double getAmount() 
	{
		return amount;
	}

	public void setAmount(double amount) 
	{
		this.amount = amount;
	}

	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account) 
	{
		this.account = account;
	}
	
	
	//********************
	// Others
	//********************
	@Override
    public String toString()
    {
        return this.wording;
    }

    @Override
    public boolean equals(Object myObject)
    {
        if(myObject instanceof Operation)
        {
            if(this.getIdOperation() == ((Operation)myObject).getIdOperation())
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
