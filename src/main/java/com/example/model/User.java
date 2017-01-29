package com.example.model;

import java.sql.Timestamp;

public class User {
	private Integer id;
	private String userName;
	private Timestamp lastUsageTime;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getLastUsageTime() {
		return lastUsageTime;
	}
	public void setLastUsageTime(Timestamp l) {
		this.lastUsageTime = l;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
