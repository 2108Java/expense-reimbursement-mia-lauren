package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.MainDriver;
import com.revature.models.User;
import com.revature.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	DBConnection connectionUtil;
	final static Logger loggy = Logger.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl(DBConnection connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
		// TODO Auto-generated constructor stub
	}

	public User selectUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		User user = new User();
		try
		{
			Connection connection = connectionUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, username);
			
		//	ps.executeQuery();	
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
			 user.setUsername(rs.getString("username"));
			 user.setPassword(rs.getString("password"));
			 user.setUserType(rs.getString("user_type"));
			}
			loggy.info(username + " retrieved from the database.");
		} 
		catch (SQLException e) 
		{
			loggy.warn(e.toString());
			e.printStackTrace();
		}
		
		return user;
	}
}
