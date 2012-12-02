package com.supinfo.supinfbank.entity.validator;

import javax.ejb.Local;

@Local
public interface Validator<T>
{
	public boolean validateObject(T object);
}
