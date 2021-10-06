package com.revature.controller;

import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;

import io.javalin.http.Context;

public class Authenticate {

	UserDAO userDao = new UserDAOImpl();
	AuthenticateUser userAuth = new AuthenticateUserImpl(userDao);
	
	public void authenticate(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(userAuth.authenticate(username, password)) {
			System.out.println("login successful");
		}
		else {
			System.out.println("login unsuccessful");
		}
	}
}
