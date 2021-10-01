package com.revature.repo;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {

	List<Ticket> viewTicketsByStatus(String status);

	List<Ticket> viewTicketsByStatus(int userId, String status);
}
