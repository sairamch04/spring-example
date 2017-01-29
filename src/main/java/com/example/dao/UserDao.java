package com.example.dao;

import com.example.model.User;

public interface UserDao{

	int  insert(User user);

	User findById(int userId);

	void update(User user);
	User findByName(String userName);

	void setSearchPath();
	
	
		
}
