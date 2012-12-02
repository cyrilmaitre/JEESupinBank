package com.supinfo.supinfbank.dao.jpa;

import javax.ejb.Stateless;

import com.supinfo.supinfbank.dao.AdvisorDao;
import com.supinfo.supinfbank.entity.Advisor;


@Stateless
public class JpaAdvisorDao extends AbstractJpaDao<Advisor> implements AdvisorDao
{
	public JpaAdvisorDao() 
	{
		super(Advisor.class);
	}
}
