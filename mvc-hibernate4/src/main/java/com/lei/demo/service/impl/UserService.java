package com.lei.demo.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lei.demo.dao.IUserDao;
import com.lei.demo.dao.common.IOperations;
import com.lei.demo.model.User;
import com.lei.demo.service.IUserService;
import com.lei.demo.service.common.AbstractService;

@Service("userService")
public class UserService extends AbstractService<User> implements IUserService {

	@Resource(name="usersDao")
	private IUserDao dao;
	
	public UserService() {
		super();
	}


	@Override
	protected IOperations<User> getDao() {
		return this.dao;
	}

}
