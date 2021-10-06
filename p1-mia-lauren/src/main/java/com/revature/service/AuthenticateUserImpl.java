package com.revature.service;

import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.repo.UserLogin;
import com.revature.models.User;

public class AuthenticateUserImpl implements AuthenticateUser {
	

	public UserDAO userDao;
	
	public AuthenticateUserImpl(UserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
//		UserDAO user = userDao.getUsername();
		
		User user = getUser(username);
		
		boolean authenticated = false;
		
		if(user != null) {

			if(user.getPassword() == username && user.getPassword().equals(password)) {
				authenticated = true;
			}
			if((user.getUsername() == username) && (user.getPassword() == (password))) {
				authenticated = true;
			}else {
//				loginMenu.display();
			}
		}
		
		return authenticated;
	}

	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = userDao.selectUserByUsername(username);
		return user;
	}

}