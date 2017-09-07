package com.rbs.scm.teamh_mis.service;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbs.scm.teamh_mis.DAO.DatabaseConnectionPostgreSQL;
import com.rbs.scm.teamh_mis.model.Addn_response;
import com.rbs.scm.teamh_mis.model.Buyer;
import com.rbs.scm.teamh_mis.model.PaymentandDeliveryDetails;
import com.rbs.scm.teamh_mis.model.Proposal_Sellers_Bid;
import com.rbs.scm.teamh_mis.model.Proposal_Sellers_Bid_Proposals;
import com.rbs.scm.teamh_mis.model.Sfeatures;



 @Service("contractmanagementsellerservice")
 
public class ContractManagementSellerService {
	 static DatabaseConnectionPostgreSQL dbobj;
	 static Connection con;
	 
	/*service to fetch all the request for proposals(rfps)*/
	 
 /*service 1*/
	// public List<Proposal_Sellers_Bid_Proposals> listAllProposals(int seller_id) throws SQLException{
		 public List<Proposal_Sellers_Bid_Proposals> listAllProposals(String seller_id) throws SQLException{
	 	
	 		//int seller_id=1;
			dbobj = new DatabaseConnectionPostgreSQL();
			List<Proposal_Sellers_Bid_Proposals> lst = new ArrayList<Proposal_Sellers_Bid_Proposals>();
			Proposal_Sellers_Bid_Proposals Proposal_Sellers_Bid_Proposals_obj = null;
			try{
				con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				System.out.println("select \"Proposal_sellers_bid\".proposal_id,\"Proposal_sellers_bid\".seller_id,"
						+ "\"Proposals\".buyer_id,\"Proposals\".d_terms_id,\"Proposals\".p_terms_id from \"Proposal_sellers_bid\", \"Proposals\""
						+ "where \"Proposal_sellers_bid\".seller_id='"+seller_id+"'"
				+ "and \"Proposal_sellers_bid\".proposal_id=\"Proposals\".proposal_id");
				ResultSet rs = stmt.executeQuery("select \"Proposal_sellers_bid\".proposal_id,\"Proposal_sellers_bid\".seller_id,"
						+ "\"Proposals\".buyer_id,\"Proposals\".d_terms_id,\"Proposals\".p_terms_id from \"Proposal_sellers_bid\", \"Proposals\""
						+ "where \"Proposal_sellers_bid\".seller_id='"+seller_id+"'"
				+ "and \"Proposal_sellers_bid\".proposal_id=\"Proposals\".proposal_id");
				//ResultSet rs = stmt.executeQuery("select * from proposal_sellers_bid where seller_id='"+seller_id+"'");
				/*Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();*/
			
				while(rs.next()){
					Proposal_Sellers_Bid_Proposals_obj = new Proposal_Sellers_Bid_Proposals();
					Proposal_Sellers_Bid_Proposals_obj.setProposal_id(rs.getInt(1));
					Proposal_Sellers_Bid_Proposals_obj.setSeller_id(rs.getInt(2));
					Proposal_Sellers_Bid_Proposals_obj.setBuyer_id(rs.getInt(3));
					Proposal_Sellers_Bid_Proposals_obj.setD_terms_id(rs.getInt(4));
					Proposal_Sellers_Bid_Proposals_obj.setP_terms_id(rs.getInt(5));
					lst.add(Proposal_Sellers_Bid_Proposals_obj);
					}
			}catch(Exception e){							
				System.out.println(e.getMessage());
			}finally{
					try {
						dbobj.closeConnection();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
		return lst;			
	}
	
	
	
	
	
/* function to list a particular proposal if the proposal id is passed from proposal table
  */	
 
	/*service 2*/
	
	 public List<Sfeatures> getFeaturesforaproduct(int proposal_id) throws ClassNotFoundException, SQLException{
			
			List<Sfeatures> lst= new ArrayList<Sfeatures>();
			Sfeatures features = null;
			dbobj = new DatabaseConnectionPostgreSQL();
			
			con = dbobj.getConnection();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from \"Features\" where proposal_id ="+proposal_id);
			
			
			while(rs.next()){
				Statement stmt2=con.createStatement();
				int fid=rs.getInt("f_id");
				int pid=rs.getInt("p_id");
				
				features = new Sfeatures();
				features = new Sfeatures();
				features.setF_id(rs.getInt("f_id"));
				features.setP_id(rs.getInt("p_id"));
				features.setProposal_id(rs.getInt("proposal_id"));
				features.setPriority_order(rs.getString("priority_order"));
				features.setAttachment(rs.getString("attachment"));
				ResultSet rs2 = stmt2.executeQuery("select * from \"feature\" where feature_id ="+fid+"and product_id="+pid);
				while(rs2.next()){
					
					features.setFeature_name(rs2.getString("feature_name"));
					System.out.println(features.getFeature_name());
					
				}
				lst.add(features);
				
			}
			return lst;
			
		}

	//service 3 FUNCTION TO UPDATE SELLER'S RESPONSE TO THE FEATURES OF EVERY PRODUCT IN THE PRODUCT= WITH A/C/N RADIO BUTTONS
	 
	 public void updatesellerresponse(int productid, int proposalid, int featureid, int sellerid, String response ,int cost) throws ClassNotFoundException, SQLException {

			
		 dbobj = new DatabaseConnectionPostgreSQL();
		 con = dbobj.getConnection();
		 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from \"Response\" where p_id="+productid+" AND f_id=" + featureid + " AND" + " seller_id="+sellerid+" AND proposal_id="+proposalid);
		 if(!rs.next()){
			 stmt.executeUpdate("insert into \"Response\" (response_status,seller_id,f_id,p_id,proposal_id,cost) VALUES('"+response+"',"+sellerid+","+featureid+","+productid+","+proposalid+","+cost+")");
		 }
		 else
		 {
			 stmt.execute("delete from \"Response\" where p_id="+productid+" AND f_id=" + featureid + " AND" + " seller_id="+sellerid+" AND proposal_id="+proposalid);
			 stmt.executeUpdate("insert into \"Response\" VALUES('"+response+"',"+sellerid+","+featureid+","+productid+","+proposalid+","+cost+")");

		 }
		
	 }
	 
	 
	 /* SERVICE 4 service to update the seller's status(accept/reject/later) in the proposal_sellers_bid table 
	  	*/
	  	public void updateBidSellerStatus(int seller_id,int proposal_id,String seller_status){
	  	try{
	  	 dbobj = new DatabaseConnectionPostgreSQL();
	  	 con = dbobj.getConnection();
	  	Statement st = con.createStatement();
	  	//con.commit();
	  	System.out.println("update \"Proposal_sellers_bid\" set "
	  			+ "seller_status= '" +seller_status+ "' where seller_id=" +seller_id+ 
	  				 "and proposal_id="+proposal_id);
	  	st.executeUpdate("update \"Proposal_sellers_bid\" set seller_status='"+seller_status+"' where seller_id=" +seller_id+ "AND proposal_id="+proposal_id);
	  	
	  	}
	  	catch(Exception e){
	  		System.out.println(e.getMessage());
	  	}
	  	}

	 
	 
	  /*	 service 5*/
	 /*VIEW STATUS OF SENT PROPOSALS*/
	/*service to fetch the buyer's status for the proposals 
	 * which the seller has already accepted
	 
*/
	public List<Proposal_Sellers_Bid> fetchBuyerStatus(int seller_id) throws SQLException{
 		 //lst = null;
		DatabaseConnectionPostgreSQL dbobj = new DatabaseConnectionPostgreSQL();
		List<Proposal_Sellers_Bid> lst= new ArrayList<Proposal_Sellers_Bid>();
		Proposal_Sellers_Bid Proposal_Sellers_Bid_obj = null;
		 
		try{
		
 	
 		
 			Connection con = dbobj.getConnection();
 			
 			Statement st = con.createStatement();

 			ResultSet rs1=st.executeQuery("select \"Proposals\".proposal_id,\"Proposals\".bid_seller_id , buyer_status "
 					+ "from \"Proposals\" where \"Proposals\".proposal_id in "
 					+ "(select \"Proposal_sellers_bid\".proposal_id from \"Proposal_sellers_bid\" where "
 					+ "\"Proposal_sellers_bid\".seller_id=" +seller_id+"AND \"Proposal_sellers_bid\".seller_status='A')");
 			
 			//System.out.println("1");
 			while(rs1.next()){
 					int selected_seller=rs1.getInt("bid_seller_id");
 					
		//System.out.println(x);
					char status=rs1.getString("buyer_status").charAt(0);
		//System.out.println(y);
					int p_id=rs1.getInt("proposal_id");
		//System.out.println(z);
		if(selected_seller==seller_id && status=='y'){
			System.out.println("11");
			Statement st1 = con.createStatement();
			st1.executeUpdate("update \"Proposal_sellers_bid\" set buyer_bid_status='a' where seller_id=" +seller_id+ "AND proposal_id="+p_id );
		}
		else if(selected_seller!=seller_id && selected_seller!=0 && status=='y' ){
			Statement st2 = con.createStatement();
			System.out.println("111");
			st2.executeUpdate("update \"Proposal_sellers_bid\" set buyer_bid_status='r' where seller_id=" +seller_id+ "AND proposal_id="+p_id);
		}
		else if(selected_seller==0){
			Statement st3 = con.createStatement();
			System.out.println("1111");
			st3.executeUpdate("update \"Proposal_sellers_bid\" set buyer_bid_status='p' where seller_id=" +seller_id+ "AND proposal_id="+p_id);
		}
		
 		
 	}
 	Statement st4 = con.createStatement();
	ResultSet rs2=st4.executeQuery("select * from \"Proposal_sellers_bid\" where seller_status='A' AND seller_id='" + seller_id+"'");
	
	//System.out.println("1");
	while(rs2.next()){
		Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();
		Proposal_Sellers_Bid_obj.setProposal_id(rs2.getInt("proposal_id"));
		Proposal_Sellers_Bid_obj.setSeller_id(rs2.getInt("seller_id"));
		Proposal_Sellers_Bid_obj.setSeller_status(rs2.getString("seller_status").charAt(0));
		//System.out.println(rs2.getString("seller_status").charAt(0));
		Proposal_Sellers_Bid_obj.setCost_avail(rs2.getInt("cost_avail"));
		Proposal_Sellers_Bid_obj.setCost_avail_cust(rs2.getInt("cost_avail_cust"));
		Proposal_Sellers_Bid_obj.setScore(rs2.getInt("score"));
		Proposal_Sellers_Bid_obj.setBuyer_bid_status(rs2.getString("buyer_bid_status").charAt(0));
		System.out.println(Proposal_Sellers_Bid_obj.toString());
		lst.add(Proposal_Sellers_Bid_obj);
		}
		}catch(Exception e){							
			System.out.println(e.getMessage());
		}finally{
				try {
					dbobj.closeConnection();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		}
		return lst;
 	
 	
 	}
	
 	
/*service to send additional response-
 * service 6*/
	
	
	public void addnresp(int seller_id,int product_id,String specification){
	  	try{
	  	 dbobj = new DatabaseConnectionPostgreSQL();
	  	 con = dbobj.getConnection();
	  	 System.out.println("came here");
	  	 System.out.println(seller_id);
	  	 System.out.println(product_id);
	  	 System.out.println(specification);
	  	Statement st = con.createStatement();
	  	int max=0;
	  	int max1;
	  	ResultSet rs=st.executeQuery("select * from \"Addn_response\"");
	  	while(rs.next()){
	  		max1=rs.getInt("id");
	  		if(max1>max){
	  			max=max1;
	  		}
	  	}
	  	 
	  	Statement st1 = con.createStatement();
	  	System.out.println("insert into \"Addn_response\"(seller_id,specification,product_id,id) values("+seller_id+",'"+specification+"',"+product_id+","+(max+1)+")");
	  	st1.executeUpdate("insert into \"Addn_response\"(seller_id,specification,product_id,id) values("+seller_id+",'"+specification+"',"+product_id+","+(max+1)+")");
	  	//st1.executeUpdate("update \"Addn_response\" set seller_id=" +seller_id+ ",specification='" +specification+ "',product_id=" +product_id+ "where id=3");
	  	
	  	//con.commit();
	  	
	  	}
	  	catch(Exception e){
	  		System.out.println(e.getMessage());
	  	}
	  	}
	
	/* view additional responses sevice 7*/
	 
	 public List<Addn_response> listAllAddResponses(int seller_id) throws SQLException{
		 	
	 		//int seller_id=1;
			dbobj = new DatabaseConnectionPostgreSQL();
			List<Addn_response> lst = new ArrayList<Addn_response>();
			Addn_response Addn_response_obj = null;
			try{
				con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from \"Addn_response\" where seller_id='"+seller_id+"'");
				//ResultSet rs = stmt.executeQuery("select * from proposal_sellers_bid where seller_id='"+seller_id+"'");
				/*Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();*/
			
				while(rs.next()){
					Addn_response_obj = new Addn_response();
					Addn_response_obj.setProduct_id(rs.getInt("product_id"));
					Addn_response_obj.setSpecification(rs.getString("specification"));
	
					lst.add(Addn_response_obj);
					}
			}catch(Exception e){							
				System.out.println(e.getMessage());
			}finally{
					try {
						dbobj.closeConnection();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
		return lst;			
	}
	   /* view buyer details service 8*/
	 
	 public List<Buyer> buyerDet(int proposal_id) throws SQLException{
		 	
	 		//int seller_id=1;
			dbobj = new DatabaseConnectionPostgreSQL();
			List<Buyer> lst = new ArrayList<Buyer>();
			Buyer Buyer_obj = null;
			try{
				con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				System.out.println("select * from \"Buyer\" where buyer_id=(select buyer_id from \"Proposals\" where proposal_id='"+proposal_id+"')");
				ResultSet rs = stmt.executeQuery("select * from \"Buyer\" where buyer_id=(select buyer_id from \"Proposals\" where proposal_id='"+proposal_id+"')");
				System.out.println("select * from \"Buyer\" where buyer_id=(select buyer_id in \"Proposals\" where proposal_id='"+proposal_id+")");
				//ResultSet rs = stmt.executeQuery("select * from proposal_sellers_bid where seller_id='"+seller_id+"'");
				/*Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();*/
			
				while(rs.next()){
					Buyer_obj = new Buyer();
					Buyer_obj.setName(rs.getString("name"));
					Buyer_obj.setEmail(rs.getString("email"));
					Buyer_obj.setBuyer_id(rs.getInt("buyer_id"));
					Buyer_obj.setCredit_rating(rs.getInt("credit_rating"));
	
					lst.add(Buyer_obj);
					}
			}catch(Exception e){							
				System.out.println(e.getMessage());
			}finally{
					try {
						dbobj.closeConnection();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
		return lst;			
	}
	
	 /*payment and delivery details service 9*/
	 
	 public List<PaymentandDeliveryDetails> pAndDdetails(int proposal_id) throws SQLException{
		 	
	 		//int seller_id=1;
			dbobj = new DatabaseConnectionPostgreSQL();
			List<PaymentandDeliveryDetails> lst = new ArrayList<PaymentandDeliveryDetails>();
			PaymentandDeliveryDetails PaymentandDeliveryDetails_obj = null;
			System.out.println("hey i came to service");
			try{
				con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				System.out.println("select * from \"Payment_terms\",\"Delivery_terms\" where \"Payment_terms\".id=(select p_terms_id from \"Proposals\" where proposal_id='"+proposal_id+"')"
						+ " and \"Delivery_terms\".id=(select d_terms_id from \"Proposals\" where proposal_id='"+proposal_id+"')");
				ResultSet rs = stmt.executeQuery("select * from \"Payment_terms\",\"Delivery_terms\" where \"Payment_terms\".id=(select p_terms_id from \"Proposals\" where proposal_id='"+proposal_id+"')"
						+ " and \"Delivery_terms\".id=(select d_terms_id from \"Proposals\" where proposal_id='"+proposal_id+"')");
				
				//ResultSet rs = stmt.executeQuery("select * from proposal_sellers_bid where seller_id='"+seller_id+"'");
				/*Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();*/
			
				while(rs.next()){
					PaymentandDeliveryDetails_obj = new PaymentandDeliveryDetails();
					PaymentandDeliveryDetails_obj.setDename(rs.getString(5));
					PaymentandDeliveryDetails_obj.setDedesc(rs.getString(6));
					PaymentandDeliveryDetails_obj.setDeid(rs.getInt(4));
					PaymentandDeliveryDetails_obj.setPaname(rs.getString(2));
					PaymentandDeliveryDetails_obj.setPadesc(rs.getString(3));
					PaymentandDeliveryDetails_obj.setPaid(rs.getInt(1));
					
	
					lst.add(PaymentandDeliveryDetails_obj);
					}
			}catch(Exception e){							
				System.out.println(e.getMessage());
			}finally{
					try {
						dbobj.closeConnection();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
		return lst;			
	}
	

  	}
 
 
 
