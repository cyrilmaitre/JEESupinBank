package com.supinfo.supinfbank.dao.jpa;

import javax.ejb.Stateless;

import com.supinfo.supinfbank.dao.AccountTypeDao;
import com.supinfo.supinfbank.entity.AccountType;


@Stateless
public class JpaAccountTypeDao extends AbstractJpaDao<AccountType> implements AccountTypeDao
{
	public JpaAccountTypeDao() 
	{
		super(AccountType.class);
	}
}
