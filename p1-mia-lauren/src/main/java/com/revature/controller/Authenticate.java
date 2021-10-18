package com.revature.controller;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.User;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;

import io.javalin.http.Context;

public class Authenticate {

	AuthenticateUser userAuth;
	final static Logger loggy = Logger.getLogger(Authenticate.class);
	
	public Authenticate(AuthenticateUser userAuth) {
		this.userAuth = userAuth;
	}
	
	public void authenticate(Context ctx) {
		//get the username and password the user entered
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		loggy.info(username + " has attempted to login.");
		
		//pass the entered credentials to service level
		User user = userAuth.authenticate(username, password);
		String page = "";
		
		if(user != null) { 
			loggy.info("Login attempt was successful.");
			
			//set session information for successful login
			ctx.status(200);
			ctx.sessionAttribute("access", username);
			ctx.sessionAttribute("userType", user.getUserType());
			
			page = "/dashboard";
		}
		else {
			loggy.info("Login attempt was unsuccessful.");
			
			//set session information for unsuccessful login
			ctx.status(401);
			ctx.sessionAttribute("access", false);
			ctx.sessionAttribute("userType", null);
			
			page = "/";
		}
		
		//redirect the user to the specified resource
		loggy.info(username + " was redirected to " + page);
		ctx.redirect(page);
	}
	
	public boolean checkAccess(Context ctx) {
		boolean access = false;
		
		//check that the current user is associated with an active session
		if(ctx.sessionAttribute("access") != null) {
			access = true;
		}
		
		loggy.info("Session is active: " + access);
		return access;
	}

	public void logout(Context ctx) {
		
		//invalidate the current session
		ctx.sessionAttribute("access", null);
		ctx.sessionAttribute("userType", null);
		
		loggy.info("User has logged out.");
		ctx.redirect("/");
	}
}
