package com.rbs.scm.teame_funding.controller.db;

import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.rbs.scm.teame_funding.POJOTables.Libortable;
import com.rbs.scm.teame_funding.POJOTables.Manageroverridetable;
import com.rbs.scm.teame_funding.model.pojos.Buyer;
import com.rbs.scm.teame_funding.model.pojos.Invoice;
import com.rbs.scm.teame_funding.model.pojos.PurchaseOrder;
import com.rbs.scm.teame_funding.model.pojos.Seller;

public class FundingDAOimpl {

	ConnectionClass c;

	public FundingDAOimpl() {
		c = new ConnectionClass();
	}

	public List<Invoice> view_invoices(String buyer, String seller) {

		Statement statement = null;
		Connection connection = null;

		List<Invoice> pt_list = new ArrayList<Invoice>();

		try {

			Connection con = c.getConnection();
			PreparedStatement stmt1 = con.prepareStatement("select * from \"Buyer\" where \"name\"=?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt1.setString(1, buyer);
			ResultSet rs1 = stmt1.executeQuery();
			System.out.println("1done");

			PreparedStatement stmt2 = con.prepareStatement("select * from \"Buyer\" where \"name\"=?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt2.setString(1, seller);
			ResultSet rs2 = stmt2.executeQuery();
			System.out.println("2done");

			PreparedStatement stmt3 = con.prepareStatement(
					"select * from \"Invoice\" where \"buyer_id\"=? and \"seller_id\"=?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt3.setInt(1, 11);
			stmt3.setInt(2, 501);
			ResultSet rs3 = stmt3.executeQuery();
			System.out.println("3done");

			while (rs3.next()) {
				Invoice pt = new Invoice();
				Buyer b = new Buyer();
				Seller s = new Seller();

				b.setBuyer_id(rs3.getInt(3));
				b.setName(buyer);
				s.setSeller_id(rs3.getInt(4));
				s.setName(seller);

				pt.setInvoiceID(rs3.getInt(1)); // invoice id
				// System.out.println(rs3.getInt(1));

				pt.setInvoiceDueDate(rs3.getString(2)); // date
				// System.out.println(rs3.getString(2));

				pt.setBuyer(b);
				// System.out.println(rs3.getInt(3));

				pt.setSeller(s);
				// System.out.println(rs3.getInt(4));

				pt.setInvoiceAmount(rs3.getDouble(5));
				// System.out.println(rs3.getDouble(5));

				pt.setPaymentDueDate(rs3.getString(6));

				pt.setInvoiceDate(rs3.getString(7));

				pt_list.add(pt);
			}

			for (Invoice i : pt_list) {
				System.out.println("test");
				System.out.println(i.getInvoiceAmount());
				System.out.println(i.getBuyer().getBuyer_id());
				System.out.println(i.getSeller().getSeller_id());
				System.out.println("data ends");
			}

			System.out.println("done successfully");
			rs3.close();
			con.close();
			return pt_list;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}


//	public List<PurchaseOrder> getPOdata(String ContractId){
//		Connection con = c.getConnection();
//		List<PurchaseOrder> po_list = new ArrayList<PurchaseOrder>();
//		PreparedStatement stmt1;
//		String sqlQuery="select * from \"Purchase_Order\" where \"po_id\"=";
//		sqlQuery += ContractId;
//		sqlQuery += ")";
//
//		try {
//			stmt1 = con.prepareStatement(sqlQuery,
//					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			ResultSet rs3 = stmt1.executeQuery();
//			System.out.println("getPOdata done");
//
//			while (rs3.next()) {
//				PurchaseOrder po = new PurchaseOrder();
//				Buyer b = new Buyer();
//				Seller s = new Seller();
//
//				b.setBuyer_id(rs3.getInt("buyer_id"));
//				s.setSeller_id(rs3.getInt("seller_id"));
//
//				po.setPOID(Integer.toString(rs3.getInt("po_id"))); // invoice id
//				// System.out.println(rs3.getInt(1));
//
//				po.setFees(Double.toString(rs3.getFloat("fees")));
//				// System.out.println(rs3.getString(2));
//
//				po.setNetPayable(Double.toString(rs3.getFloat("net_payable_amount")));
//				// System.out.println(rs3.getInt(3));
//
//				po.setInvestmentAmount(Double.toString(rs3.getFloat("investment_amount")));
//				// System.out.println(rs3.getInt(4));
//
//				po.setDiscountRate(Double.toString(rs3.getFloat("discount_rate")));
//				// System.out.println(rs3.getDouble(5));
//
//				po.setBankApproved(rs3.getBoolean("is_approved"));
//
//				po.setSellerApproved(rs3.getBoolean("is_seller_review"));
//
//				po.setBuyer(b);
//				po.setSeller(s);
//
//				po_list.add(po);
//
//				System.out.println(po);
//			}
//
//			rs3.close();
//			con.close();
//		}
//
//		catch (Exception e) {
//			System.out.println(e);
//		}
//
//		return po_list;
//
//	}

	public List<PurchaseOrder> getPOList(String username){

		/*
		 * TODO: check for bank user
		 */
		boolean isBankUser = false;
		Connection con = c.getConnection();
		List<PurchaseOrder> po_list = new ArrayList<PurchaseOrder>();
		PreparedStatement stmt1;
		String sqlQuery;
		String  a=null;
		if(isBankUser) {
			sqlQuery = "select * from \"Purchase_Order\"";
		}
		else {
			try {
				PreparedStatement stmt3 = con.prepareStatement("select \"user_id\" from \"user_id_name\" where \"Username\"=?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt3.setString(1, username);
				ResultSet rs1 = stmt3.executeQuery();
				while(rs1.next()) {
					a= rs1.getString(1);
				}
			}
			
			catch (Exception e) {
				System.out.println(e);
			}
			
			
			sqlQuery = "select * from \"Purchase_Order\" where \"seller_id\" =501";
			//sqlQuery += a;
			}

		try {
			stmt1 = con.prepareStatement(sqlQuery,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs3 = stmt1.executeQuery();
			System.out.println("getPOlist done");

			while (rs3.next()) {
				PurchaseOrder po = new PurchaseOrder();
				Buyer b = new Buyer();
				Seller s = new Seller();
				System.out.println("step1");
				b.setBuyer_id(rs3.getInt("buyer_id"));
				s.setSeller_id(rs3.getInt("seller_id"));

				po.setPOID(Integer.toString(rs3.getInt("po_id"))); // invoice id
				System.out.println(Integer.toString(rs3.getInt("po_id")));

				po.setFees(Double.toString(rs3.getFloat("fees")));
				// System.out.println(rs3.getString(2));

				po.setNetPayable(Double.toString(rs3.getFloat("net_payable_amount")));
				// System.out.println(rs3.getInt(3));

				po.setInvestmentAmount(Double.toString(rs3.getFloat("investment_amount")));
				// System.out.println(rs3.getInt(4));

				po.setDiscountRate(Double.toString(rs3.getFloat("discount_rate")));
				// System.out.println(rs3.getDouble(5));

				po.setBankApproved(rs3.getBoolean("is_approved"));

				po.setSellerApproved(rs3.getBoolean("is_seller_review"));

				po.setBuyer(b);
				po.setSeller(s);

				po_list.add(po);

				System.out.println(po);
				System.out.println("end");
			}

			rs3.close();
			con.close();



		}
		catch (Exception e) {
			System.out.println(e);
		}

		return po_list;
	}






	public void insertLIBOR(Libortable lt) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Insert into LIBOR values ('?','?','?','?')";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setString(1, lt.getCurrency());
			preparedStmt.setTime(2, lt.getTime_of_issue());
			preparedStmt.setInt(3, lt.getDuration());
			preparedStmt.setDouble(4, lt.getRate());
			preparedStmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public double readLIBOR(String currency, Integer duration, String time_of_issue) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Select rate from LIBOR l where l.currency=? AND l.duration=> AND l.time_of_issue=?";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setString(1, currency);
			preparedStmt.setInt(2, duration);
			preparedStmt.setString(3, time_of_issue);
			ResultSet rs = preparedStmt.executeQuery();
			return rs.getDouble(1);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertManager(Manageroverridetable man) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Insert into Manager_Override values ('?','?','?','?')";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setInt(1, man.getManager_id());
			preparedStmt.setString(2, man.getManager_name());
			preparedStmt.setInt(3, man.getPo_id());
			preparedStmt.setString(4, man.getReason());
			preparedStmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertPurchaseOrder(Integer buyer_id, Integer seller_id, double investment_amount, double net_payeable,
			double discount_rate, int PO_id) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Insert into Purchase_Order values ('?','?','?','?','?','?','?','?','?')";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setInt(1, PO_id);
			preparedStmt.setInt(2, buyer_id);
			preparedStmt.setInt(3, seller_id);
			preparedStmt.setDouble(4, 1000);
			preparedStmt.setDouble(5, net_payeable);
			preparedStmt.setDouble(6, investment_amount);
			preparedStmt.setDouble(7, discount_rate);
			preparedStmt.setBoolean(8, false);
			preparedStmt.setBoolean(9, false);
			preparedStmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Boolean readPurchaseOrder(Integer buyer_id, Integer seller_id) {
		Statement statement = null;
		Connection connection = null;
		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_3 = "SELECT ";
			sql_3 += "isReviewedbySeller";
			sql_3 += " from \"PurchaseOrder\"";
			sql_3 += " WHERE buyer_id=";
			sql_3 += buyer_id;
			sql_3 += " AND seller_id=";
			sql_3 += seller_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql_3);
			Boolean flag = rs.getBoolean(1);
			rs.close();
			return flag;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public double readBuyer(int buyer_id) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Select credit_rating from Buyer b where b.buyer_id=?";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setInt(1, buyer_id);
			ResultSet rs = preparedStmt.executeQuery();
			return rs.getDouble(1);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
