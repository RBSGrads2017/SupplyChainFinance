package com.rbs.scm.teamc_contract2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
//	private static Connection conn = null;
//	final private static String USERNAME = "liybotsvyembvp";
//	final private static String PASSWORD = "e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a";
//	final private static String URI = "jdbc:postgresql://ec2-23-21-85-76.compute-1.amazonaws.com:5432/d11rrktmmgd00t?sslmode=require";
//	static {
//		try {
//			Class.forName("org.postgresql.Driver");
//			conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private SQLConnection() {}
//	public static Connection getConnection() {
//		if (conn != null) {
//			System.out.println("You made it, take control your database now!");
//		} else {
//			System.out.println("Failed to make connection!");
//		}
//		return conn;
//	}
	
		public static Connection getConnection()
		{
			try{  
		
			
			Class.forName("org.postgresql.Driver");  
			
			Connection con=DriverManager.getConnection(  
			"jdbc:postgresql://ec2-46-137-97-169.eu-west-1.compute.amazonaws.com:5432/dcp74dndul9afj?sslmode=require","yzqfbztsbnnnoi","a02372528e43fe5384947fdc13d247f85cc9da2e6925e64f717355434d941c4d");  
			 
			return con;
		}catch(Exception e){ System.out.println(e);}  
		return null;
		}
	
	
}
