package com.revature.repo;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {

	//Read
	List<Ticket> selectAllTickets();
	
	List<Ticket> selectAllTickets(String username);

	List<Ticket> selectTicketsByStatus(String username, String status);

	//Create
	boolean insertNewTicket(Ticket ticket);
	
	//Update
	boolean updateTicketStatus(int id, String status);
}
