package com.supinfo.supinfbank.dao;

import javax.ejb.Local;

import com.supinfo.supinfbank.entity.Account;


@Local
public interface AccountDao extends Dao<Account>
{
	Account addAccount(Account account);
}
