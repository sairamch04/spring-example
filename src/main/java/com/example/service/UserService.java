package com.example.service;

import com.example.model.User;

public interface UserService {
	int  insert(User user);

	User findById(int userId);

	void update(User user);

	User findByName(String userName);
	
}
