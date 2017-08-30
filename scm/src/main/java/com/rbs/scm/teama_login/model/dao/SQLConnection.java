package com.rbs.scm.teama_login.model.dao;




import java.sql.Connection;
import java.sql.DriverManager;
import com.rbs.scm.teama_login.model.beans.*;
public class SQLConnection {
	public static Connection getConnection()
	{
		try{  
	
		
		Class.forName("org.postgresql.Driver");  
		
		Connection con=DriverManager.getConnection(  
		"jdbc:postgresql://ec2-23-21-85-76.compute-1.amazonaws.com:5432/d11rrktmmgd00t?sslmode=require","liybotsvyembvp","e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a");  
		 
		return con;
	}catch(Exception e){ System.out.println(e);}  
	return null;
	}
}


