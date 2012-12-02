package com.supinfo.supinfbank.entity.validator;

import java.util.Set;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Stateless
public abstract class AbstractValidator<T> implements com.supinfo.supinfbank.entity.validator.Validator<T>
{
	//********************
	// Attributes
	//********************
	Set<ConstraintViolation<T>> violations;
	
	
	//********************
	// Getters - Setters
	//********************
	public Set<ConstraintViolation<T>> getViolations() 
	{
		return violations;
	}
	
	
	//********************
	// Methodes
	//********************
	public boolean validateObject(T object)
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		violations = validator.validate(object);
		
		return violations.isEmpty();
	}
}
