package com.rbs.scm.teamc_contract2.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.DeliveryTerm;
import com.rbs.scm.teamc_contract2.data.PaymentTerm;
import com.rbs.scm.teamc_contract2.database.SQLConnection;

public class PaymentsImpl implements PaymentsDao {


	public ArrayList<PaymentTerm> fetchAllPaymentTerms() {

		// TODO Auto-generated method stub
		ArrayList<PaymentTerm> payment = new ArrayList<>();
	
		Connection conn = SQLConnection.getConnection();
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt
					.executeQuery("select * from \"Payment_terms\"");
			while (rs.next()) {
				PaymentTerm pay = new PaymentTerm();
				pay.setId(rs.getInt(1));
				pay.setDesc(rs.getString(3));
                payment.add(pay);
				
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

			return payment;

	

	}	

}
