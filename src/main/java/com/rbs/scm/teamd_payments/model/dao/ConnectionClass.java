package com.rbs.scm.teamd_payments.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	public Connection getConnection()
	{
		try{  
	
		
		Class.forName("org.postgresql.Driver");  
		
		Connection con=DriverManager.getConnection(  
		"jdbc:postgresql://ec2-107-22-167-179.compute-1.amazonaws.com:5432/d8mmtu5oa82n4b?sslmode=require","oiiylntyasmyjc","75444b86c6e5bf539613af381fa452442035ed5aa43502b7ad329ca1a6314af3");  
		 
		return con;
	}catch(Exception e){ System.out.println(e);}  
	return null;
	}
}
