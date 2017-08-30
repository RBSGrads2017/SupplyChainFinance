package com.rbs.scm.teama_login.model.dao;
import java.sql.Connection;
import com.rbs.scm.teama_login.model.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class UserProductsDao {

	
	public static boolean insertIntoProducts(UserProducts up) throws SQLException {
			int i=0;
			Connection conn = SQLConnection.getConnection();
			Statement ss = conn.createStatement();
			String str = "DELETE from \"User_products\" where \"Username\" ='" + up.getUsername() + "'";
			ss.executeUpdate(str);
			for (i=0; i<up.getUserProducts().length; i++)
				{PreparedStatement st = conn.prepareStatement("INSERT INTO \"User_products\" values(?,?)");
				st.setString(1, up.getUsername());
				st.setString(2, (up.getUserProducts())[i]);
				st.executeUpdate();
			}
			
			return true;
	}
}