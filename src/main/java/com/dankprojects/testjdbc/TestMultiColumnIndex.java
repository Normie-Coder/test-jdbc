package com.dankprojects.testjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.IntStream;

public class TestMultiColumnIndex {
	
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/TestDB";
		String username = "postgres";
		String password = "Qw3rty!";
		
		try {
			Connection con = DBConnection.getJDBCConnection(url, username, password);
			
			System.out.println("Connected to DB...");
			
			String insertSQL = "insert into test2 (major, minor, name) values(?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(insertSQL);
			
			IntStream.range(1, 200000000).forEach(
					num -> {
						try {
							pstmt.setInt(1, new Random().nextInt(1, 200000000));
							pstmt.setInt(2, new Random().nextInt(1, 200000000));
							pstmt.setString(3, "value for num is "+num);
							pstmt.addBatch();
							
							if (num % 100000 == 0) {
								int[] rowsAffected = pstmt.executeBatch();
								System.out.println(rowsAffected==null?0: rowsAffected.length+ " row(s) inserted successfully!");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
			);
			
			pstmt.executeBatch();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
