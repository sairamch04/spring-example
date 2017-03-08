package com.example.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserService;


@Service
// We need transaction management for every service call even for SELECT statements because
// we have to set both the  search_path and execute some other query in SAME connection.[since we are using POSTGRES]
// If the JDBC template that we are using to execute queries is not part of Transaction, Then it creates new connection
// for every single query statement.
//uses the bean with id as transactionManager for transaction management
@Transactional(rollbackFor = Exception.class) 

public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDAO;
	
	@Override
	public User findById(int userId) {
		User user = userDAO.findById(userId);
		if(user != null){ //update last visit time to current time
			user.setLastUsageTime(new Timestamp(System.currentTimeMillis()));
			userDAO.update(user);
		}		
		return user;
	}
	
	@Override
	public int insert(User user) {
		int userId = userDAO.insert(user);
		return userId;
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
		
	}

	@Override
	public User findByName(String userName) {
		User user = userDAO.findByName(userName);
		return user;
	}

	
}
