package com.rbs.scm.teama_login.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.rbs.scm.teama_login.model.beans.*;
public class CustomerDaoImpl {
	private static ResultSet res = null;
	
	private static Customer convertResToObject() throws SQLException{
		Customer c = null;
		if(res != null) {
			while(res.next()) {
				String name = res.getString(2);
				String email = res.getString(1);
				c = new Customer(name, email);
			}
		}
		return c;
	}
	
	public static boolean insertIntoCustomers(GenericUser gu, Customer c) throws SQLException {
		
		
		Connection conn = null;
		try {
			if (GenericUserDaoImpl.insertIntoUser(gu)) {
				conn = SQLConnection.getConnection();
				PreparedStatement st = conn.prepareStatement("INSERT INTO \"Customer_user\" values" + "(?,?)");
				st.setString(1, c.getEmail());
				st.setString(2, c.getName());
				st.executeUpdate();
				return true;
			
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return false;
	}
	
	public static boolean checkCustomerExistence(String email) throws SQLException{
		res = null;
		
		Connection conn = null;
		try {
			conn = SQLConnection.getConnection();
			if(conn != null) {
				Statement st = conn.createStatement();
				String queryString = "select * from \"Customer_user\" where \"Username\"='" + email + "'";
				res = st.executeQuery(queryString);
			}
			if(res.next()) {
				return true;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return false;
	}
	
	public static Customer searchCustomer(String username) throws SQLException{  //username is email here
		res = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getConnection();
			if(conn != null) {
				Statement st = conn.createStatement();
				String queryString = "select * from \"Customer_user\" where \"Username\" ='" + username + "'";
				res = st.executeQuery(queryString);
				Customer c = convertResToObject();
				return c;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public static Customer searchCustomerByName(String username) throws SQLException{  //username is email here
		res = null;
		Connection conn = null;
		try {
			conn = SQLConnection.getConnection();
			if(conn != null) {
				Statement st = conn.createStatement();
				String queryString = "select * from \"Customer_user\" where \"Name\" ='" + username + "'";
				res = st.executeQuery(queryString);
				Customer c = convertResToObject();
				return c;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	
}
