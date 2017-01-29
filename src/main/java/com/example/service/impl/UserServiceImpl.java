package com.example.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDAO;
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Override
	public User findById(int userId) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		User user = userDAO.findById(userId);
		if(user != null){ //update last visit time to current time
			user.setLastUsageTime(new Timestamp(System.currentTimeMillis()));
			userDAO.update(user);
		}		
		transactionManager.commit(transactionStatus);
		return user;
	}
	
	@Override
	public int insert(User user) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		int userId = userDAO.insert(user);
		transactionManager.commit(transactionStatus);
		return userId ;
	}

	@Override
	public void update(User user) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		userDAO.update(user);
		transactionManager.commit(transactionStatus);
		
	}

	@Override
	public User findByName(String userName) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		User user = userDAO.findByName(userName);
		transactionManager.commit(transactionStatus);
		return user;
	}

	
}
