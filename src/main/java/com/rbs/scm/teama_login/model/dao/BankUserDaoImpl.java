package com.rbs.scm.teama_login.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.rbs.scm.teama_login.model.beans.*;
public class BankUserDaoImpl {
	/*The object of ResultSet maintains a cursor pointing to a row of a table.
Initially, cursor points to before the first row.*/
	
	
	
	private static ResultSet res = null;
	
	
	
	private static BankUser convertResToObject() throws SQLException{
		BankUser gu = null;
		if(res != null) {
			while(res.next()) {
				String Username= res.getString(1);
				String Fullname= res.getString(2);
				String address= res.getString(3);
				String p_group = res.getString(4);
				gu = new BankUser(Username,Fullname,address,p_group);
			}
		}
		return gu;
	}
	
	public static boolean insertIntoBankUser(GenericUser u,BankUser c) throws SQLException {
		
		if(GenericUserDaoImpl.insertIntoUser(u)) {
			Connection conn = SQLConnection.getConnection();
			/*PreparedStatement for parameterized query like insert();*/
			PreparedStatement st = conn.prepareStatement("INSERT INTO \"Bank_user\" values(?,?,?,?)");
			st.setString(1, c.getUsername());
			st.setString(2, c.getFullname());
			st.setString(3, c.getaddress());
			st.setString(4, c.getp_group());
			st.executeUpdate();
			return true;
		}
		return false;
	}
	
	public static BankUser searchBankUser(String username) throws SQLException{  //username is email here
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from \"Bank_user\" where \"Username\" ='" + username + "'";
			res = st.executeQuery(queryString);
			System.out.println(res);
			BankUser b = convertResToObject();
			return b;
		}
		return null;
	}
	

	
}
