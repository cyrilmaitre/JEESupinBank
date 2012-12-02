package com.supinfo.supinfbank.dao.jpa;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.supinfbank.dao.UserDao;
import com.supinfo.supinfbank.entity.User;

@Stateless
public class JpaUserDao extends AbstractJpaDao<User> implements UserDao
{
	public JpaUserDao()
	{
		super(User.class);
	}

	@Override
	public User authenticate(User user)
	{
    	Query query = em.createQuery(	"SELECT user " +
    									"FROM User as user " +
    									"WHERE user.email = :email " +
    										"AND user.password = :password");
    	
    	query.setParameter("email", user.getEmail());
    	query.setParameter("password", user.getPassword());
    		    	
    	try
    	{
    		user = (User)query.getSingleResult();
    		em.refresh(user);
    	}
    	catch(NoResultException e)
    	{
    		System.out.println("Wrong user: "+user.getEmail()+" / "+user.getPassword());
    		return null;
    	}
    	
    	return user;
	}
}
