package com.revature;

import com.revature.controller.RequestMapping;
import com.revature.presentation.UserLogin;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;

import io.javalin.Javalin;

public class MainDriver {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> config.addStaticFiles(
				staticFiles -> {staticFiles.directory = "/public";}
				)).start(8000);
		
		RequestMapping.setupEndpoints(app);
		
	}

}
