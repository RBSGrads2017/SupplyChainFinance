package com.rbs.scm.teamc_contract2.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.ContractProductFeatureLog;
import com.rbs.scm.teamc_contract2.data.DeliveryTerm;
import com.rbs.scm.teamc_contract2.database.SQLConnection;

public class DeliveryImpl implements DeliveryDao {

	public ArrayList<DeliveryTerm> fetchAllDeliveryTerm() {
		// TODO Auto-generated method stub
		ArrayList<DeliveryTerm> delivery = new ArrayList<>();
	
		Connection conn = SQLConnection.getConnection();
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt
					.executeQuery("select * from \"Delivery_terms\"");
			while (rs.next()) {
				DeliveryTerm del = new DeliveryTerm();
				del.setId(rs.getInt(1));
				del.setDesc(rs.getString(3));
                delivery.add(del);
				
			}
		}
			catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}

			return delivery;

	} 
		

}
