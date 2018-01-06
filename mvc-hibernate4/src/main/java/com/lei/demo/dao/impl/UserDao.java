package com.lei.demo.dao.impl;


import org.springframework.stereotype.Repository;

import com.lei.demo.dao.IUserDao;
import com.lei.demo.dao.common.AbstractHibernateDao;
import com.lei.demo.model.User;

@Repository("usersDao")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

	public UserDao() {
		super();
		
		setClazz(User.class);
	}


}
