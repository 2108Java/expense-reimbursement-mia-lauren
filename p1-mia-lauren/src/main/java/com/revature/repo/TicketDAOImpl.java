package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.util.DBConnection;

public class TicketDAOImpl implements TicketDAO {
	
	DBConnection dbConnection;
	
	public TicketDAOImpl(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	@Override
	public List<Ticket> viewTicketsByStatus(String status) {
		List<Ticket> ticketList = new ArrayList<>();
		
		try {
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM tickets WHERE status = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ticketList.add(new Ticket(
						rs.getInt("ticket_id"),
						rs.getInt("user_id"),
						rs.getString("expense_type"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getDate("submitted_on"),
						rs.getString("status")));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ticketList;
	}
	
	@Override
	public List<Ticket> viewTicketsByStatus(int userId, String status) {
		List<Ticket> ticketList = new ArrayList<>();
		
		try {
			Connection connection = dbConnection.getConnection();
			
			String sql = "SELECT * FROM tickets WHERE status = ? and user_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, userId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ticketList.add(new Ticket(
						rs.getInt("ticket_id"),
						rs.getInt("user_id"),
						rs.getString("expense_type"),
						rs.getDouble("amount"),
						rs.getString("description"),
						rs.getDate("submitted_on"),
						rs.getString("status")));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ticketList;
	}
	
	@Override
	public boolean createNewTicket(Ticket ticket) {
		boolean success = false;
		
		try {
			Connection connection = dbConnection.getConnection();
			
			String sql = "INSERT INTO tickets (user_id, expense_type, amount, description, submitted_on, status) "
					+ "VALUES (?,?,?,?, now(),?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ticket.getUserId());
			ps.setString(2, ticket.getExpenseType());
			ps.setDouble(3, ticket.getAmount());
			ps.setString(4, ticket.getDescription());
			ps.setString(5, ticket.getStatus());
			
			ps.execute();
			
			success = true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean updateTicketStatus(int id, String status) {
		boolean success = false;
		
		try {
			Connection connection = dbConnection.getConnection();
			
			String sql = "UPDATE TABLE tickets SET status = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}
}
