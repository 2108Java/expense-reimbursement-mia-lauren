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
		
		System.out.println(username);
		System.out.println(password);
		
		User user = userAuth.authenticate(username, password);
		String page = "";
		
		if(user != null) { 
			ctx.status(200);
			ctx.sessionAttribute("access", username);
			ctx.sessionAttribute("userType", user.getUserType());
			page = "/dashboard";
		}
		else {
			ctx.status(401);
			ctx.sessionAttribute("access", false);
			ctx.sessionAttribute("userType", null);
			page = "/";
		}
		ctx.redirect(page);
	}

	public void logout(Context ctx) {
		ctx.consumeSessionAttribute("access");
		ctx.consumeSessionAttribute("userType");
		
		ctx.redirect("/");
	}
}
