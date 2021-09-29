package com.revature.models;

public class User {

	// VARIABLES
	
	private String username;
	private String password;
	
	// CONSTRUCTORS
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	// GETTERS
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	// SETTERS
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
