package com.revature.controller;

import io.javalin.http.Context;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.Ticket;
import com.revature.repo.TicketDAO;
import com.revature.repo.TicketDAOImpl;
import com.revature.service.TicketService;
import com.revature.service.TicketServiceImpl;

public class TicketController {
	TicketService tServ;
	final static Logger loggy = Logger.getLogger(TicketController.class);
	
	public TicketController(TicketService tServ) {
		this.tServ = tServ;
	}
	
	public void getDashboard(Context ctx) {
		String page = "";
		String userType = ctx.sessionAttribute("userType").toString();
		
		//determine which dashboard should be loaded
		if(userType.equalsIgnoreCase("finance manager")) {
			page = "ManagerMenu.html";
		}
		else if(userType.equalsIgnoreCase("employee")) {
			page = "EmployeeMenu.html";
		}
		else{
			ctx.redirect("/");
		}
		
		try {
			//load the correct resource
			loggy.info("Loaded resource: " + page);
			ctx.req.getRequestDispatcher(page).forward(ctx.req, ctx.res);
		} catch (ServletException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
	}
	
	public void viewTickets(Context ctx) {
		String page = "";
		String userType = ctx.sessionAttribute("userType").toString();
		
		//determine which ticket display to load
		if(userType.equalsIgnoreCase("finance manager")) {
			page = "viewAll.html";
		}
		else if(userType.equalsIgnoreCase("employee")) {
			page = "viewAllTickets.html";
		}
		else{
			ctx.redirect("/");
		}
		
		try {
			//load correct ticket display
			loggy.info("Loaded resource: " + page);
			ctx.req.getRequestDispatcher(page).forward(ctx.req, ctx.res);
		} catch (ServletException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
	}
	
	public void editTickets(Context ctx) {
		String page = "";
		String userType = ctx.sessionAttribute("userType").toString();
		
		//check that the current user in an employee
		if(userType.equalsIgnoreCase("employee")) {
			page = "createTicket.html";
		}
		else{
			ctx.redirect("/");
		}
		
		try {
			//load the correct resource
			loggy.info("Loaded resource: " + page);
			ctx.req.getRequestDispatcher(page).forward(ctx.req, ctx.res);
		} catch (ServletException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
	}
	
	public List<Ticket> getAllTickets(Context ctx) {
		List<Ticket> ticketList = null;
		String userType = ctx.sessionAttribute("userType").toString();
		
		//determine which set of tickets to retrieve
		if(userType.equalsIgnoreCase("finance manager")) {
			loggy.info("Request to retrieve all tickets.");
			ticketList = tServ.viewAllTickets();
		}
		else if(userType.equalsIgnoreCase("employee")) {
			String username = ctx.sessionAttribute("access").toString();
			loggy.info("Request to retrieve all tickets associated with " + username);
			ticketList = tServ.viewAllTickets(username);
		}
		
		return ticketList;
	}
	
	public void addTicket(Context ctx) {
		//get information from the user and the active session
		String username = ctx.sessionAttribute("access").toString();
		String expenseType = ctx.formParam("types");
		double amount = Double.parseDouble(ctx.formParam("cost"));
		String description = ctx.formParam("description");
		String status = "PENDING";
		
		//create a ticket with the gathered information
		loggy.info("New ticket was submitted.");
		Ticket ticket = new Ticket(username, expenseType, amount, description, status);
		
		//attempt to add the ticket to the database
		if(tServ.createNewTicket(ticket)) {
			loggy.info("Ticket submitted successfully.");
			ctx.status(200);
			ctx.redirect("/dashboard");
		}
		else {
			loggy.info("Ticket submission failed.");
			ctx.status(500);
			ctx.redirect("/edit");
		}
	}

	public void updateTicket(Context ctx, String status) {
		//get updated ticket Id
		int id = Integer.parseInt(ctx.formParam("id"));
		
		//attempt to update the stored ticket
		if(tServ.updateTicketStatus(id, status)) {
			loggy.info("Ticket " + id + " was successfully " + status);
			ctx.status(200);
			ctx.redirect("/dashboard");
		}
		else {
			loggy.info("Ticket " + id + " was not updated.");
			ctx.status(500);
			ctx.redirect("/viewTickets");
		}
	}

}
