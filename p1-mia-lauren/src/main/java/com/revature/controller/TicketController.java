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
		
		if(ctx.sessionAttribute("userType").toString().equalsIgnoreCase("finance manager")) {
			page = "ManagerMenu.html";
		}
		else if(ctx.sessionAttribute("userType").toString().equalsIgnoreCase("employee")) {
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
	
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = null;
		
		ticketList = tServ.viewAllTickets();
		return ticketList;
	}

}
