package com.revature.controller;

import com.revature.models.User;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;

import io.javalin.http.Context;

public class Authenticate {

	AuthenticateUser userAuth;
	
	public Authenticate(AuthenticateUser userAuth) {
		this.userAuth = userAuth;
	}
	
	public void authenticate(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		User user = userAuth.authenticate(username, password);
		
		if(user != null) { 
			ctx.status(200);
			ctx.sessionAttribute("access", true);
			ctx.sessionAttribute("userType", user.getUserType());
		}
		else {
			ctx.status(401);
			ctx.sessionAttribute("access", false);
			ctx.sessionAttribute("userType", null);
		}
	}

	public void logout(Context ctx) {
		ctx.consumeSessionAttribute("access");
		ctx.consumeSessionAttribute("userType");		
	}
}
