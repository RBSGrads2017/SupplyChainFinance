package com.rbs.scm.DAO;

//Sample code to test connectivity in local machine- will be updated later


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DatabaseConnectionPostgreSQL {
		Connection c = null;
	 public Connection getConnection() throws SQLException, ClassNotFoundException {
	      
	      
	         Class.forName("org.postgresql.Driver");
	     /*This commented fragment connects to the heroku cloud database directly
	      * Because heroku allows only 20 connections simultaneously, it s advised not to use this unless necessary
	      * Close connection appropriately
	      * It is advised to make a local copy of the postgres db and connect to it
	      * Please not that to connect to the cloud db, you need a working internet connection.
	      * These credentials are prone to change, if your connection fails, update the credentials.
	      */
	         
	         //heroku db connection
	         
	         
	         String url = "jdbc:postgresql://ec2-23-21-85-76.compute-1.amazonaws.com:5432/d11rrktmmgd00t?user=liybotsvyembvp&password=e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	         Properties props = new Properties();
	         props.setProperty("user","liybotsvyembvp");
	         props.setProperty("password","e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a");
	         props.setProperty("ssl","true");
	         c = DriverManager.getConnection(url, props);
	      
	         
	         
	         //local db connection
	          
	        /* c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/latestdb",
	            "postgres", "manager");*/
	            
	     
	           
	           
	         return c;
	     } 
	
	 public void closeConnection() throws SQLException{
		 c.close();
	 }
	 
	 

}



