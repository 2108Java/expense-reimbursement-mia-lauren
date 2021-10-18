package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.service.TicketServiceImpl;

public class DBConnection {


	//database endpoint and login credentials
	private static final String URL = "jdbc:postgresql://mia-lauren.cagsxdx1qu3s.us-east-2.rds.amazonaws.com/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "2108Java";
	
	final static Logger loggy = Logger.getLogger(DBConnection.class);
	
	public Connection getConnection() throws SQLException {
		
		//attempt to create a connection to the database
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		loggy.info("Connection to database generated.");
		
		return conn;
	}


}
