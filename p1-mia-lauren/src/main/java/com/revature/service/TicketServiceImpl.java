package com.revature.service;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.repo.TicketDAO;

public class TicketServiceImpl implements TicketService {

	TicketDAO database;
	
	public TicketServiceImpl(TicketDAO ticketDao) {
		this.database = ticketDao;
	}
	@Override
	public List<Ticket> viewAllTickets() {
		return database.selectAllTickets();
	}

	@Override
	public List<Ticket> viewAllTickets(String username) {
		return database.selectAllTickets(username);
	}

	@Override
	public List<Ticket> viewTicketsByStatus(String username, String status) {
		return database.selectTicketsByStatus(username, status);
	}

	@Override
	public boolean createNewTicket(Ticket ticket) {
		return database.insertNewTicket(ticket);
	}

	@Override
	public boolean updateTicketStatus(int id, String status) {
		return database.updateTicketStatus(id, status);
	}

}
