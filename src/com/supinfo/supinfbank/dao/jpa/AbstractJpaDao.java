package com.supinfo.supinfbank.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supinfo.supinfbank.dao.Dao;


@Stateless
public abstract class AbstractJpaDao<T> implements Dao<T> 
{
	//********************
    // Attributes
    //********************
	private Class<T> object;
	
	@PersistenceContext(unitName = "supinBankPU")
	protected EntityManager em;
	
	
	//********************
    // Constructor
    //********************
	protected AbstractJpaDao(Class<T> object)
	{
		this.object = object;
	}
	
	
	//********************
    // Methods
    //********************
	@Override
	public T add(T newObject) 
	{
    	em.persist(newObject);
    	return newObject;
	}

	@Override
	public void update(T object) 
	{
    	em.merge(object);
	}

	@Override
	public void remove(int id) 
	{
    	em.remove(em.find(object, id));		
	}

	@Override
	public T findById(int id) 
	{
    	return this.findById(id, true);
	}
	
	@Override
	public T findById(int id, boolean refreshing) 
	{
    	T find = (T) em.find(object, id);
    	if(refreshing)
    	{
    		em.refresh(find);
    	}
    	return find;
	}

	@Override
	public List<T> findAll() 
	{
		return this.findAll(false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(boolean resfreching) 
	{
    	Query query = em.createQuery("SELECT p FROM "+object.getSimpleName()+" AS p");
		List<T> list = query.getResultList();
		if(resfreching)
		{
			for (T current : list) 
			{
				em.refresh(current);
			}
		}
		return list;
	}
}