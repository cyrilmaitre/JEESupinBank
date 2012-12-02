package com.supinfo.supinfbank.dao;

import java.util.List;

import javax.ejb.Local;


@Local
public interface Dao<T>
{
	T add(T newObject);
    void update(T object);
    void remove(int id);
    T findById(int id);
    T findById(int id, boolean refreshing);
    List<T> findAll();
    List<T> findAll(boolean refreshing);
}
