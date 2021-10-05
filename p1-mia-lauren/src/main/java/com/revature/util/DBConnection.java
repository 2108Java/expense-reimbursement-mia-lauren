package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private String dbLocation = "localhost:5432";
	private String username = "postgres";
	private String password = "password";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";

	public Connection getConnection() throws SQLException {
		
	//	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
		
		
	}
}
