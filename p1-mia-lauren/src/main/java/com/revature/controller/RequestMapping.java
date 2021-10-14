package com.revature.controller;

import com.revature.repo.UserDAOImpl;
import com.revature.util.DBConnection;

import io.javalin.Javalin;

public class RequestMapping {
	
	Authenticate auth;
	TicketController tCon;
	
	public RequestMapping(Authenticate auth, TicketController tCon) {
		this.auth = auth;
		this.tCon = tCon;
	}
	public void setupEndpoints(Javalin app) {		
		
		//homepage
		app.get("/", ctx -> ctx.req.getRequestDispatcher("index.html").forward(ctx.req, ctx.res));
				
		//login and logout
		app.post("/authenticate", ctx -> {auth.authenticate(ctx);});
		
		app.post("/logout", ctx -> {auth.logout(ctx);});
		
		//user dashboards
		app.get("/dashboard", ctx -> {tCon.getDashboard(ctx);});
		
		app.get("/dashboard/viewAll", ctx -> ctx.req.getRequestDispatcher("viewAll.html").forward(ctx.req, ctx.res));
		
		app.get("/dashboard/edit", ctx -> ctx.req.getRequestDispatcher("editTickets.html").forward(ctx.req, ctx.res));
		
		
		//get all tickets
		app.get("/tickets", ctx -> {
			ctx.json(tCon.getAllTickets());
		});
		
		//get tickets by status
//		app.get(null, null);
		
		//update ticket status
//		app.put(null, null);
		
		//get tickets by username
//		app.get("/tickets/user, null);
		
		//get tickets by username and status
//		app.get(null, null);
				
		//post ticket
//		app.post(null, null);
	}

}
