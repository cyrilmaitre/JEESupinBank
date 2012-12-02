package com.supinfo.supinfbank.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "supinbank_advisor")
public class Advisor extends User
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	
	
	//********************
	// Constructors
	//********************
	public Advisor()
	{
		this(null, null);
	}
	
	public Advisor(String email, String password)
	{
		super(email, password);
	}
	
	public Advisor(int idUser, String email, String password)
	{
		super(idUser, email, password);
	}
	
	
	//********************
	// Getters - Setters
	//********************
	
	
	//********************
	// Others
	//********************
}
