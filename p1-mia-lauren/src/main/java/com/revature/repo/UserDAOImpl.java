package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	public UserDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	DBConnection connectionUtil = new DBConnection();

	public User selectUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		try
		{
			Connection connection = connectionUtil.getConnection();
			
			String sql = "SELECT * FROM user_table WHERE username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, username);
			
		//	ps.executeQuery();	
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
			
			// user.setUser_id(rs.getInt("user_id"));
			 user.setPassword(rs.getString("passwrd"));
			 user.setUsername(rs.getString("username"));
			
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
}