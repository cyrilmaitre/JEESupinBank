package com.supinfo.supinfbank.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "supinbank_customer")
@XmlRootElement(name = "Customer")
public class Customer extends User
{
	private static final long serialVersionUID = 1L;
	
	
	//********************
	// Attributes
	//********************
	@NotNull @Size(min = 1, message = "First Name is required")
	@XmlElement(name = "firstName")
	private String firstName;
	
	@NotNull @Size(min = 1, message = "Last Name is required")
	@XmlElement(name = "lastName")
	private String lastName;
	
	@NotNull @Size(min = 1, message = "Adresse is required")
	@XmlElement(name = "adresse")
	private String adresse;
	
	@NotNull @Size(min = 1, message = "Zip Code is required")
	@XmlElement(name = "zipCode")
	private String zipCode;
	
	@NotNull @Size(min = 1, message = "City is required")
	@XmlElement(name = "city")
	private String city;
	
	@NotNull @Size(min = 1, message = "Phone is required")
	@XmlElement(name = "phone")
	private String phone;
	
	@OneToMany(mappedBy = "customer")
	@LazyCollection(LazyCollectionOption.FALSE)
	@XmlElement(name = "accounts")
    private Collection<Account> accounts;
	
	
	//********************
	// Constructors
	//********************
	public Customer()
	{
		this(null, null, null, null, null, null, null, null);
	}
	
	public Customer(String email, String password, String firstName, String lastName, String adresse, String zipCode, String city, String phone)
	{
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
		this.zipCode = zipCode;
		this.city = city;
		this.phone = phone;
	}
	
	public Customer(int idUser, String email, String password, String firstName, String lastName, String adresse, String zipCode, String city, String phone)
	{
		super(idUser, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
		this.zipCode = zipCode;
		this.city = city;
		this.phone = phone;
	}

	
	//********************
	// Getters - Setters
	//********************
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	public String getZipCode() 
	{
		return zipCode;
	}

	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Collection<Account> getAccounts() 
	{
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) 
	{
		this.accounts = accounts;
	}
	
	
	//********************
	// Others
	//********************
}
