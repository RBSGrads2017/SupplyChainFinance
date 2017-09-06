package com.rbs.scm.teama_login.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rbs.scm.teama_login.model.beans.IdMapping;

public class IdMappingDaoImpl {
	private static ResultSet res = null;
		
	private static IdMapping convertResToObject() throws SQLException{
		IdMapping c = null;
		if(res != null) {
			while(res.next()) {
				String UserStringId = res.getString(1);
				int UserIntId = res.getInt(2);
				c = new IdMapping(UserStringId, UserIntId);
			}
		}
		return c;
	}
	
	public static IdMapping FindStringId(int UserIntId) throws SQLException {
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from user_id_name where user_id = " + UserIntId;
			res = st.executeQuery(queryString);
		}
		if(res.next()) {
			IdMapping m = convertResToObject();
			return m;
		}
		return null;
	}
	
	public static IdMapping FindIntId(String UserStringId) throws SQLException {
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from \"user_id_name\" where \"Username\" = '" + UserStringId + "'";
			res = st.executeQuery(queryString);
			IdMapping m = convertResToObject();
			return m;
		}
		return null;
	}
	
}
