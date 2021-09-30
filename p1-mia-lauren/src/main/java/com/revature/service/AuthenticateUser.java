package com.revature.service;

import com.revature.models.User;

public interface AuthenticateUser {
	
	public boolean authenticate(String username, String password);

 	public User getUser(String username);
}