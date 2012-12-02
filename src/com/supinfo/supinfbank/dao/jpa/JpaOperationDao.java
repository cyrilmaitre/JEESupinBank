package com.supinfo.supinfbank.dao.jpa;

import javax.ejb.Stateless;

import com.supinfo.supinfbank.dao.OperationDao;
import com.supinfo.supinfbank.entity.Operation;


@Stateless
public class JpaOperationDao extends AbstractJpaDao<Operation> implements OperationDao
{
	public JpaOperationDao() 
	{
		super(Operation.class);
	}
}
