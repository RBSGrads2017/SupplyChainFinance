package com.rbs.scm.teame_funding.controller.db;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.json.JSONObject;

import com.rbs.scm.teame_funding.POJOTables.Libortable;
import com.rbs.scm.teame_funding.POJOTables.Manageroverridetable;
import com.rbs.scm.teame_funding.model.pojos.Buyer;
import com.rbs.scm.teame_funding.model.pojos.Invoice;
import com.rbs.scm.teame_funding.model.pojos.Libor;
import com.rbs.scm.teame_funding.model.pojos.PurchaseOrder;
import com.rbs.scm.teame_funding.model.pojos.Seller;

public class FundingDAOimpl {

	ConnectionClass c;

	public FundingDAOimpl() {
		c = new ConnectionClass();
	}

	public List<Invoice> view_invoices(String buyer, String seller) {

		Statement statement = null;
		Connection con = null;

		List<Invoice> pt_list = new ArrayList<Invoice>();

		try { 	PreparedStatement stmt1 = con.prepareStatement(
				"select user_name from \"customer_user\" where \"\"=? and \"seller_id\"=?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		

			PreparedStatement stmt3 = con.prepareStatement(
					"select * from \"Invoice\" where \"buyer_id\"=? and \"seller_id\"=?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt3.setInt(1,);
			stmt3.setInt(2,);
			ResultSet rs3 = stmt3.executeQuery();
			System.out.println("3done");

			while (rs3.next()) {
				Invoice pt = new Invoice();
				

			

				pt.setInvoiceID(rs3.getInt(1)); // invoice id
				// System.out.println(rs3.getInt(1));

				pt.setInvoiceDueDate(rs3.getString(2)); // date
				// System.out.println(rs3.getString(2));

				
				// System.out.println(rs3.getInt(3));

			
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

				po.setPOID(rs3.getString("po_id")); // invoice id
				System.out.println(Integer.toString(rs3.getInt("po_id")));

				po.setFees(rs3.getInt("fees"));
				// System.out.println(rs3.getString(2));

				po.setNetPayable(rs3.getDouble("net_payable_amount"));
				// System.out.println(rs3.getInt(3));

				po.setInvestmentAmount(rs3.getDouble("investment_amount"));
				// System.out.println(rs3.getInt(4));

				po.setDiscountRate(rs3.getDouble("discount_rate"));
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



	


	public void insertLIBOR(Libor lt) throws ParseException {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Insert into \"LIBOR\" (currency , \"duration\", \"rate\") values (?,?,?)";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setString(1, lt.getCurrency());
			//DateFormat formatter = new SimpleDateFormat("HH:mm");
			//java.sql.Time timeValue = new java.sql.Time(formatter.parse((new Date()).getTime()).toString());
			preparedStmt.setInt(2, calcDuration(lt.getDuration()));
			preparedStmt.setDouble(3, lt.getRate());
			//preparedStmt.setTime(2, timeValue);
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
	
	public int calcDuration(String duration) {
		int t=0;
		if(duration.equals("1 week"))
			t = 0;
		else if(duration.equals("1 month"))
			t=1;
		else if(duration.equals("2 months"))
			t=2;
		else if(duration.equals("3 months"))
			t=3;
		else if(duration.equals("6 months"))
			t=6;
		else if(duration.equals("12 months"))
			t=12;
		
		return t;
	}

	public double readLIBOR(String currency, Integer duration) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "lqmwbjsssdqgqd");
			props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);
			String sql_1 = "Select rate from LIBOR l where l.currency=? AND l.duration=?";
			PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
			preparedStmt.setString(1, currency);
			preparedStmt.setInt(2, duration);
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

	public void insertPO(PurchaseOrder PO) {
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
            preparedStmt.setString(1, PO.getPOID());
            preparedStmt.setString(2,PO.getbuyer_id());
            preparedStmt.setString(3,PO.getseller_id());
            preparedStmt.setInt(4, PO.getFees());
            preparedStmt.setDouble(5, PO.getNetPayable());
            preparedStmt.setDouble(6, PO.getInvestmentAmount());
            preparedStmt.setDouble(7, PO.getDiscountRate());
            preparedStmt.setBoolean(8, PO.isBankApproved());
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

	public Boolean readPOsreview(String PO_id) {
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
            sql_3 += "is_seller_review";
            sql_3 += " from \"Purchase_Order\"";
            sql_3 += " WHERE po_id= ";
            sql_3 += PO_id;

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
//	public JSONObject readPO(String PO_id) {
//        Statement statement = null;
//        Connection connection = null;
//        JSONObject Json;
//        try {
//
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
//            Properties props = new Properties();
//            props.setProperty("user", "lqmwbjsssdqgqd");
//            props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
//            props.setProperty("ssl", "true");
//            connection = DriverManager.getConnection(url, props);
//            String sql_3 = "SELECT ";
//            sql_3 += "*";
//            sql_3 += " from \"Purchase_Order\"";
//            sql_3 += " WHERE po_id= ";
//            sql_3 += PO_id;
//
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(sql_3);
//            //Json.put(, arg1) = rs.get
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            
//        } finally {
//            try {
//            	connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public Boolean readPOapproved(String PO_id) {
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
            sql_3 += "is_approved";
            sql_3 += " from \"Purchase_Order\"";
            sql_3 += " WHERE po_id= ";
            sql_3 += PO_id;

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
   
    public void deletePO(boolean flag, String PO_id)
    { Connection connection = null;
    try {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        Properties props = new Properties();
        props.setProperty("user", "lqmwbjsssdqgqd");
        props.setProperty("password", "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
        props.setProperty("ssl", "true");
        connection = DriverManager.getConnection(url, props);
        String sql_1 = "Delete from Purchase_Order where po_id=? and ?=true";
        PreparedStatement preparedStmt = connection.prepareStatement(sql_1);
        preparedStmt.setString(1,PO_id);
        preparedStmt.setBoolean(2,flag);
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

	
}

