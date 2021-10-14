package com.revature.controller;

import io.javalin.http.Context;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.revature.models.Ticket;
import com.revature.repo.TicketDAO;
import com.revature.repo.TicketDAOImpl;
import com.revature.service.TicketService;
import com.revature.service.TicketServiceImpl;

public class TicketController {
	TicketService tServ;
	
	public TicketController(TicketService tServ) {
		this.tServ = tServ;
	}
	
	public void getDashboard(Context ctx) {
		String page = "";
		String userType = ctx.sessionAttribute("userType").toString();
		
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
			ctx.req.getRequestDispatcher(page).forward(ctx.req, ctx.res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewTickets(Context ctx) {
		String page = "";
		String userType = ctx.sessionAttribute("userType").toString();
		
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
			ctx.req.getRequestDispatcher(page).forward(ctx.req, ctx.res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editTickets(Context ctx) {
		String page = "";
		String userType = ctx.sessionAttribute("userType").toString();
		
		if(userType.equalsIgnoreCase("finance manager")) {
			page = "editTickets.html";
		}
		else if(userType.equalsIgnoreCase("employee")) {
			page = "createTicket.html";
		}
		else{
			ctx.redirect("/");
		}
		
		try {
			ctx.req.getRequestDispatcher(page).forward(ctx.req, ctx.res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Ticket> getAllTickets(Context ctx) {
		List<Ticket> ticketList = null;
		String userType = ctx.sessionAttribute("userType").toString();
		
		if(userType.equalsIgnoreCase("finance manager")) {
			ticketList = tServ.viewAllTickets();
		}
		else if(userType.equalsIgnoreCase("employee")) {
			String username = ctx.sessionAttribute("access").toString();
			ticketList = tServ.viewAllTickets(username);
		}
		
		return ticketList;
	}
	
	public void addTicket(Context ctx) {
		String username = ctx.sessionAttribute("access").toString();
		String expenseType = ctx.formParam("types");
		double amount = Double.parseDouble(ctx.formParam("amount"));
		String description = ctx.formParam("description");
		String status = "Pending";
		
		Ticket ticket = new Ticket(username, expenseType, amount, description, status);
		
		if(tServ.createNewTicket(ticket)) {
			ctx.status(200);
			ctx.redirect("/dashboard");
		}
		else {
			ctx.status(500);
			ctx.redirect("/edit");
		}
	}

}
