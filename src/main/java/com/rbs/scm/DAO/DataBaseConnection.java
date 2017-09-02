package com.rbs.scm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

public Connection getConnection() throws ClassNotFoundException, SQLException{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		
		System.out.println("connected to Oracle Db");
		
		return con;
		
	}
	
}
