package com.revature.models;

public class User {

	// VARIABLES
	
	private int userID;
	private String username;
	private String password;
	
	// CONSTRUCTORS
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int userID, String username, String password) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
	}
	
	
	// GETTERS
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
	// SETTERS
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
