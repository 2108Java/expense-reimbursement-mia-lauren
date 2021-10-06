package com.revature;

import com.revature.presentation.UserLogin;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;

import io.javalin.Javalin;

public class MainDriver {
	
	public static void main(String[] args) {
		

		UserDAO userDao = new UserDAOImpl();
		AuthenticateUser auth = new AuthenticateUserImpl(userDao);
		UserLogin login = new UserLogin(auth);
		
		login.display();
		
		Javalin app = Javalin.create(config -> config.addStaticFiles(
				staticFiles -> {staticFiles.directory = "/public";}
				)).start(8000);
		
	}

}
