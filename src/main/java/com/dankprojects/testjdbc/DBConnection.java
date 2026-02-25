package com.dankprojects.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getJDBCConnection(String url, String username, String passwd) throws ClassNotFoundException, SQLException {
		Connection c = null;
		Class.forName("org.postgresql.Driver");
		
		System.out.println("Driver loaded...");
		
		c = DriverManager.getConnection(url, username, passwd);
		
		return c;
	}

}
