package com.revature.controller;

import io.javalin.Javalin;

public class RequestMapping {
	
	Authenticate auth;
	TicketController tCon;
	
	public RequestMapping(Authenticate auth, TicketController tCon) {
		this.auth = auth;
		this.tCon = tCon;
	}
	public void setupEndpoints(Javalin app) {		
		
		//login and logout
		app.post("/authenticate", ctx -> {auth.authenticate(ctx);});
		
		app.get("/logout", ctx -> {auth.logout(ctx);});
		
		//user dashboards
//		app.get(null, null);
		
		//get all tickets
		app.get("/tickets", ctx -> {
			ctx.json(tCon.getAllTickets());
		});
		
		//get tickets by status
//		app.get(null, null);
		
		//update ticket status
//		app.put(null, null);
		
		//get tickets by username
//		app.get(null, null);
		
		//get tickets by username and status
//		app.get(null, null);
				
		//post ticket
//		app.post(null, null);
	}

}
