package com.revature.controller;

import io.javalin.http.Context;

import java.util.List;

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
	
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = null;
		
		ticketList = tServ.viewAllTickets();
		return ticketList;
	}

}
