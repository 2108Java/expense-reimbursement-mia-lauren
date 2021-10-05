package com.revature.service;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketService {

		//Read
		List<Ticket> viewAllTickets();
		
		List<Ticket> viewTicketsByStatus(String status);

		List<Ticket> viewTicketsByStatus(int userId, String status);

		//Create
		boolean createNewTicket(Ticket ticket);
		
		//Update
		boolean updateTicketStatus(int id, String status);
}
