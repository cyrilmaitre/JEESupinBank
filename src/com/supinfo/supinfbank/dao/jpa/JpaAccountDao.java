package com.supinfo.supinfbank.dao.jpa;

import javax.ejb.Stateless;

import com.supinfo.supinfbank.dao.AccountDao;
import com.supinfo.supinfbank.entity.Account;


@Stateless
public class JpaAccountDao extends AbstractJpaDao<Account> implements AccountDao
{
	public JpaAccountDao()
	{
		super(Account.class);
	}

	@Override
	public Account addAccount(Account account) 
	{
		account = this.add(account);
		account.setBban();
		this.update(account);
		return account;
	}
}
