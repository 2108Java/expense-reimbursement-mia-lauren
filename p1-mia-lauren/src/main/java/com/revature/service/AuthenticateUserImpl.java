package com.revature.service;

import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.models.User;

public class AuthenticateUserImpl implements AuthenticateUser {
	

	public UserDAO userDao;
	
	public AuthenticateUserImpl(UserDAO userDao) {
		this.userDao = userDao;
	}

	public User authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
//		UserDAO user = userDao.getUsername();
		
		
		User user = getUser(username);
		
		boolean authenticated = false;
		
		if(user != null) {

			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				authenticated = true;
			}
		}
		
		if(authenticated) {
			return user;
		}
		else {
			return null;
		}
	}

	public User getUser(String username) {
		// TODO Auto-generated method stub

		User user = userDao.selectUserByUsername(username);
		return user;
	}

}