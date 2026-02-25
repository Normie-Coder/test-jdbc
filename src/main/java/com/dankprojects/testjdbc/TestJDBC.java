package com.dankprojects.testjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.IntStream;

public class TestJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "jdbc:postgresql://localhost:5432/TestDB";
		String username = "postgres";
		String password = "Qw3rty!";
		
		try {
			Connection conn= DBConnection.getJDBCConnection(url, username, password);
			
			System.out.println("Connected to DB");
			//conn.setAutoCommit(false);
			String insertSQL = "insert into \"Test1\" (content1) values (?)";
			//Statement statement = conn.
			PreparedStatement statement = conn.prepareStatement(insertSQL);
			
		
			IntStream.range(60000001, 90000000).forEach(num ->{
				try {
					//conn.setAutoCommit(false); 
					statement.setString(1, "inserting values for content i" + num);
					statement.addBatch();
					if (num % 30000== 0 ) {
						
						int[] rowsAffected = statement.executeBatch();
						System.out.println(rowsAffected==null?0: rowsAffected.length+ " row(s) inserted successfully!");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			});
			
			 statement.executeBatch();
			
			
			
			//conn.commit();
			System.out.println("successful!!!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
