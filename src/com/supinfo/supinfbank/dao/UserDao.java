package com.supinfo.supinfbank.dao;

import javax.ejb.Local;
import com.supinfo.supinfbank.entity.User;


@Local
public interface UserDao extends Dao<User>
{
	User authenticate(User user);
}
