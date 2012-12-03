package com.supinfo.supinfbank.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Email;


@Entity
@Inheritance (strategy=InheritanceType.JOINED)
@Table(name = "supinbank_user")
@XmlTransient
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	

	//********************
	// Attributes
	//********************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(name = "idUser")
	private int idUser;
	
	@NotNull @Size(min = 1, message = "Email is required")
	@Email(message = "Email format invalid")
	@XmlElement(name = "email")
	private String email;
	
	private String password;
	
	
	//********************
	// Constructors
	//********************
	public User()
	{
		this(null, null);
	}
	
	public User(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
	
	public User(int idUser, String email, String password)
	{
		this(email, password);
		this.idUser = idUser;
	}
	
	
	//********************
	// Getters - Setters
	//********************
	public int getIdUser() 
	{
		return idUser;
	}

	public void setIdUser(int idUser) 
	{
		this.idUser = idUser;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	//********************
	// Others
	//********************
	@Override
    public String toString()
    {
        return this.email;
    }

    @Override
    public boolean equals(Object myObject)
    {
        if(myObject instanceof User)
        {
            if(this.getIdUser() == ((User)myObject).getIdUser())
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
