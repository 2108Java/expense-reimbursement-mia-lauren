package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.Ticket;
import com.revature.repo.TicketDAO;

public class TicketServiceImpl implements TicketService {

	TicketDAO database;
	final static Logger loggy = Logger.getLogger(TicketServiceImpl.class);
	
	public TicketServiceImpl(TicketDAO ticketDao) {
		this.database = ticketDao;
	}
	@Override
	public List<Ticket> viewAllTickets() {
		loggy.info("Request for all tickets passed from the Controller to the DAO.");
		return database.selectAllTickets();
	}

	@Override
	public List<Ticket> viewAllTickets(String username) {
		loggy.info("Request for all " + username + " tickets passed from the Controller to the DAO.");
		return database.selectAllTickets(username);
	}

	@Override
	public List<Ticket> viewTicketsByStatus(String username, String status) {
		return database.selectTicketsByStatus(username, status);
	}

	@Override
	public boolean createNewTicket(Ticket ticket) {
		loggy.info("New ticket passed from the Controller to the DAO.");
		return database.insertNewTicket(ticket);
	}

	@Override
	public boolean updateTicketStatus(int id, String status) {
		loggy.info("Ticket status update passed from the Controller to the DAO.");
		return database.updateTicketStatus(id, status);
	}

}
