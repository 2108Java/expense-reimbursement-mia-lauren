package com.revature.service;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketService {

		//Read
		List<Ticket> viewAllTickets();
		
		List<Ticket> viewAllTickets(String username);

		List<Ticket> viewTicketsByStatus(String username, String status);

		//Create
		boolean createNewTicket(Ticket ticket);
		
		//Update
		boolean updateTicketStatus(int id, String status);
}
