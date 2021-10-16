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
		app.get("/dashboard", ctx -> {
			if(auth.checkAccess(ctx)){
				tCon.getDashboard(ctx);
			}
			else {
				ctx.redirect("/");
			}
		});
		
		app.get("/viewTickets", ctx -> {
			if(auth.checkAccess(ctx)) {
				tCon.viewTickets(ctx);
			}
			else {
				ctx.redirect("/");
			}
		});
		
		app.get("/edit", ctx -> {
			if(auth.checkAccess(ctx)) {
				tCon.editTickets(ctx);
			}
			else {
				ctx.redirect("/");
			}
		});
		
		
		//get all tickets
		app.get("/tickets", ctx -> {ctx.json(tCon.getAllTickets(ctx));});
		
		//update ticket status
		app.post("/approve", ctx -> {tCon.updateTicket(ctx, "APPROVED");});
		
		app.post("/reject", ctx -> {tCon.updateTicket(ctx, "REJECTED");});
				
		//post ticket
		app.post("/addTicket", ctx -> {tCon.addTicket(ctx);});
	}

}
