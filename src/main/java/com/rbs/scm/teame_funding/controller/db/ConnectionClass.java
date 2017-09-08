package com.rbs.scm.teame_funding.controller.db;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	public Connection getConnection()
	{
		try{  
	
		
		Class.forName("org.postgresql.Driver");  
		
		Connection con=DriverManager.getConnection(  
		"jdbc:postgresql://ec2-46-137-97-169.eu-west-1.compute.amazonaws.com/dcp74dndul9afj?sslmode=require","yzqfbztsbnnnoi","a02372528e43fe5384947fdc13d247f85cc9da2e6925e64f717355434d941c4d");  
		 
		return con;
	}catch(Exception e){ System.out.println(e);}  
	return null;
	}
}
