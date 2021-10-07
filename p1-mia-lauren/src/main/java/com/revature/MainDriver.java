package com.revature;

import org.apache.log4j.Logger;

import com.revature.controller.Authenticate;
import com.revature.controller.RequestMapping;
import com.revature.controller.TicketController;
import com.revature.repo.TicketDAO;
import com.revature.repo.TicketDAOImpl;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;
import com.revature.service.TicketService;
import com.revature.service.TicketServiceImpl;
import com.revature.util.DBConnection;

import io.javalin.Javalin;

public class MainDriver {
	
	final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		loggy.info("Application started.");
		
		//javalin app configuration with static files and port number
		Javalin app = Javalin.create(config -> config.addStaticFiles(
				staticFiles -> {staticFiles.directory = "/public";}
				)).start(8000);
		
		//repository layer dependency
		DBConnection con = new DBConnection();
		
		//authentication dependencies
		UserDAO uDao = new UserDAOImpl(con);
		AuthenticateUser uAuth = new AuthenticateUserImpl(uDao);
		Authenticate auth = new Authenticate(uAuth);
		
		//ticket controller dependencies
		TicketDAO tDao = new TicketDAOImpl(con);
		TicketService tServ = new TicketServiceImpl(tDao);
		TicketController tCon = new TicketController(tServ);
		
		//pass http requests to endpoint handlers
		RequestMapping map = new RequestMapping(auth, tCon);
		map.setupEndpoints(app);
		
	}

}
