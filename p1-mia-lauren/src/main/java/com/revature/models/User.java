package com.revature.models;

public class User {

	// VARIABLES
	
	private String username;
	private String password;
	private String userType;
	
	// CONSTRUCTORS
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	// GETTERS
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserType() {
		return userType;
	}
	
	// SETTERS
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
