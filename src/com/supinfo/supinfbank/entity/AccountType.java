package com.supinfo.supinfbank.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "supinbank_accounttype")
public class AccountType implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	//********************
	// Attributes
	//********************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAccountType;
	
	private String name;
	
	
	//********************
	// Constructors
	//********************
	public AccountType()
	{
		this(null);
	}
	
	public AccountType(String name)
	{
		this.name = name;
	}
	
	public AccountType(int idAccountType, String name)
	{
		this(name);
		this.idAccountType = idAccountType;
	}

	
	//********************
	// Getters - Setters
	//********************
	public int getIdAccountType() 
	{
		return idAccountType;
	}

	public void setIdAccountType(int idAccountType) 
	{
		this.idAccountType = idAccountType;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
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
        if(myObject instanceof AccountType)
        {
            if(this.getIdAccountType() == ((AccountType)myObject).getIdAccountType())
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
