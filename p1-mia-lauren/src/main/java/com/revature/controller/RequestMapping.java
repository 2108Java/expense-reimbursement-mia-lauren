package com.revature.controller;

import io.javalin.Javalin;

public class RequestMapping {
	
	public static void setupEndpoints(Javalin app) {
		
		Authenticate auth = new Authenticate();
		
		//login and logout
		app.post("/Login.html", ctx -> {auth.authenticate(ctx);});
	}

}
