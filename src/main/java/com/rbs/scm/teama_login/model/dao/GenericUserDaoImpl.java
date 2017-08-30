package com.rbs.scm.teama_login.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.rbs.scm.teama_login.model.beans.*;
public class GenericUserDaoImpl{

	private static ResultSet res = null;
	
	private static GenericUser convertResToObject() throws SQLException{
		GenericUser gu = null;
		if(res != null) {
			while(res.next()) {
				String uID = res.getString(1);
				String password = res.getString(2);
				boolean isConfirmed = res.getBoolean(3);
				boolean userType = res.getBoolean(4);
				boolean is_Bank_User = res.getBoolean(5);
				
				
				gu = new GenericUser(uID, password, isConfirmed, userType,is_Bank_User);
			}
		}
		return gu;
	}
	
	public static GenericUser searchUser(String username) throws SQLException{
		res = null;
		
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			System.out.println("in GenericUser searchUser");
			String queryString = "select * from  \"User\" where \"Username\"='" + username + "'";
			res = st.executeQuery(queryString);
			GenericUser gu = convertResToObject();
			System.out.println("out GenericUser searchUser");
			return gu;
		}
		return null;
	}
	
	
	public static boolean insertIntoUser(GenericUser gu) throws SQLException {
		
		//if(checkUsernameExistence(c.email)) { return false; }
		
		Connection conn = SQLConnection.getConnection();
		PreparedStatement st = conn.prepareStatement("INSERT INTO \"User\" values" + "(?,?,?,?,?)");
		st.setString(1, gu.getUsername());
		st.setString(2, gu.getPassword());
		st.setBoolean(3, gu.isActive());
		st.setBoolean(4, gu.isConfirmed());
		st.setBoolean(5, gu.get_is_Bank_User());
		st.executeUpdate();
		
		return true;
	}
	
	public static boolean addToPwdTable (GenericUser gu, String token) throws SQLException {
		Connection conn = SQLConnection.getConnection();
		Statement ss = conn.createStatement();
		String str = "DELETE FROM \"Forgot_Password\" WHERE \"Username\" = '" +gu.getUsername() + "'";
		ss.executeUpdate(str);
		PreparedStatement st = conn.prepareStatement("INSERT INTO \"Forgot_Password\" values" + "(?,?)");
		st.setString(1, gu.getUsername());
		st.setString(2, token);
		st.executeUpdate();
		return true;
	}
	
	public static String GetUserFromToken (String token) throws SQLException {
		res = null;
		Connection conn = SQLConnection.getConnection();
		Statement ss = conn.createStatement();
		String str = "select \"Username\" from \"Forgot_Password\" where \"Token\" = '" + token + "'";
		res = ss.executeQuery(str);
		
		if (res.next()) {
			return res.getString(1);
		}
		return null;
	}
	
	public static boolean ChangePwdUser (String username, String password) throws SQLException {
		
		Connection conn = SQLConnection.getConnection();
		Statement ss = conn.createStatement();
		String str = "UPDATE \"User\" SET \"Password\" = '" + password + "' WHERE \"Username\" = '" + username + "'";
		ss.executeUpdate(str);
		
		ss = conn.createStatement();
		str = "DELETE FROM \"Forgot_Password\" WHERE \"Username\" = '" + username + "'";
		ss.executeUpdate(str);
		
		return true;
	}
}
