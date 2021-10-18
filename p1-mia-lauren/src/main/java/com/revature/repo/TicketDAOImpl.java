package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.Ticket;
import com.revature.util.DBConnection;

public class TicketDAOImpl implements TicketDAO {
	
	DBConnection dbConnection;
	final static Logger loggy = Logger.getLogger(TicketDAOImpl.class);
	
	public TicketDAOImpl(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	@Override
	public List<Ticket> selectAllTickets(){
		List<Ticket> ticketList = new ArrayList<>();
		
		try {
			//connect to the database
			Connection connection = dbConnection.getConnection();
			
			//create sql query
			String sql = "SELECT * FROM tickets";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			//execute sql query
			ResultSet rs = ps.executeQuery();
			
			//add retrieved tickets to ticketList
			while(rs.next()) {
				ticketList.add(new Ticket(
						rs.getInt("ticket_id"),
						rs.getString("username"),
						rs.getString("expense_type"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getDate("submitted_on"),
						rs.getString("status")));
			}
			loggy.info("All tickets retrieved from the database.");
			
		} catch (SQLException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
		return ticketList;
	}
	
	@Override
	public List<Ticket> selectAllTickets(String username) {
		List<Ticket> ticketList = new ArrayList<>();
		
		try {
			//connect to the database
			Connection connection = dbConnection.getConnection();
			
			//create sql query
			String sql = "SELECT * FROM tickets WHERE username = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			
			//execute sql query
			ResultSet rs = ps.executeQuery();
			
			//add tickets to ticketList
			while(rs.next()) {
				ticketList.add(new Ticket(
						rs.getInt("ticket_id"),
						rs.getString("username"),
						rs.getString("expense_type"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getDate("submitted_on"),
						rs.getString("status")));
			}
			loggy.info("All tickets associated with " + username +" retrieved from database.");
			
		} catch (SQLException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
		
		
		
		return ticketList;
	}
	
	@Override
	public List<Ticket> selectTicketsByStatus(String username, String status) {
		List<Ticket> ticketList = new ArrayList<>();
		
		try {
			//connect to the database
			Connection connection = dbConnection.getConnection();
			
			//create sql query
			String sql = "SELECT * FROM tickets WHERE status = ? and username = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, username);
			
			//execute sql query
			ResultSet rs = ps.executeQuery();
			
			//add tickets to ticketList
			while(rs.next()) {
				ticketList.add(new Ticket(
						rs.getInt("ticket_id"),
						rs.getString("username"),
						rs.getString("expense_type"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getDate("submitted_on"),
						rs.getString("status")));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return ticketList;
	}
	
	@Override
	public boolean insertNewTicket(Ticket ticket) {
		boolean success = false;
		
		try {
			//connect to the database
			Connection connection = dbConnection.getConnection();
			
			//create sql query
			String sql = "INSERT INTO tickets (username, expense_type, amount, description, submitted_on, status) "
					+ "VALUES (?,?,?,?, now(),?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ticket.getUsername());
			ps.setString(2, ticket.getExpenseType());
			ps.setDouble(3, ticket.getAmount());
			ps.setString(4, ticket.getDescription());
			ps.setString(5, ticket.getStatus());
			
			//execute sql query
			ps.execute();
			
			success = true;
			
			loggy.info("Ticket added to database successfully.");
			
		} catch (SQLException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean updateTicketStatus(int id, String status) {
		boolean success = false;
		
		try {
			//connect to the database
			Connection connection = dbConnection.getConnection();
			
			//create sql query
			String sql = "UPDATE tickets SET status = ? WHERE ticket_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			
			//execute sql query
			ps.execute();
			
			success = true;
			loggy.info("Ticket " + id + " status was successfully updated.");
			
		} catch (SQLException e) {
			loggy.warn(e.toString());
			e.printStackTrace();
		}
		
		return success;
	}
}
