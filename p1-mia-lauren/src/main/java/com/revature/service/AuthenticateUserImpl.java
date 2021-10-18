package com.revature.service;

import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.User;

public class AuthenticateUserImpl implements AuthenticateUser {
	

	public UserDAO userDao;
	final static Logger loggy = Logger.getLogger(AuthenticateUserImpl.class);
	
	public AuthenticateUserImpl(UserDAO userDao) {
		this.userDao = userDao;
	}

	public User authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
//		UserDAO user = userDao.getUsername();
		
		loggy.info(username + " information requested from the database.");
		User user = getUser(username);
		
		boolean authenticated = false;
		
		if(user.getUsername() != null && user.getPassword() != null) {
			loggy.info(username + "exists and is not null.");

			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				loggy.info("Entered credentials match stored credentials.");
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