package com.rbs.scm.teamd_payments.model.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.rbs.scm.teamd_payments.model.beans.Bank;
import com.rbs.scm.teamd_payments.model.beans.Bank_to_Customer;
import com.rbs.scm.teamd_payments.model.beans.Customer_Transaction;
import com.rbs.scm.teamd_payments.model.beans.Customer_to_Bank;
import com.rbs.scm.teamd_payments.model.beans.FXData;
import com.rbs.scm.teamd_payments.model.beans.Swift;
import com.rbs.scm.teamd_payments.model.beans.Vostro_Accounts;

public class PaymentsImpl implements PaymentsDao {
	ConnectionClass c;
	public PaymentsImpl()
	{
		c=new ConnectionClass();
	}
	@Override
	public Bank[] getAllBankDetails() {
		
		try {
		Connection con=c.getConnection();
		Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		//System.out.println("Hello");
		ResultSet rs1=stmt1.executeQuery("select * from bank"); 
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		//System.out.println("Hello"+count);
		rs1.beforeFirst();
		//System.out.println("Hello"+count);
		Bank b[]=new Bank[count];
		//System.out.println("Hello"+count);
		while(rs1.next())  
		{	
			Map<String,String> m=new HashMap<String,String>();
			String s1=rs1.getString(1);
			PreparedStatement stmt=con.prepareStatement("select * from nostro_accounts where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setString(1, s1);
			System.out.println("Hello"+count);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
					m.put(rs.getString(2), rs.getString(3));
				
			}
			Bank b1=new Bank(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(14),rs1.getString(4),m,rs1.getString(5),rs1.getString(6),rs1.getDate(7),rs1.getString(10),rs1.getString(11),rs1.getString(12),rs1.getString(13),rs1.getInt(8),rs1.getString(9));
			b[i++]=b1;
		}
		con.close();
		return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
	}

	@Override
	public Customer_Transaction[] getAllCustomerTransactionDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs=stmt1.executeQuery("select * from customer_transaction");
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while (rs.next())
			{
			    // Process the row.
			    count++;
			}
			rs.beforeFirst();
			Customer_Transaction b[]=new Customer_Transaction[count];
			while(rs.next())  
			{	
				int s1=rs.getInt(1);
				PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE); 
				stmt.setInt(1, s1);
				System.out.println("Hello"+count);
				ResultSet rs1=stmt.executeQuery();
				if( rs1.first()){
					 
				Customer_Transaction t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
				b[i++]=t1;}
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

	@Override
	public Swift[] getAllSwiftDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs1=stmt1.executeQuery("select * from swift"); 
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while ( rs1.next() )
			{
			    // Process the row.
			    count++;
			}
			rs1.beforeFirst();
			Swift b[]=new Swift[count];
			while(rs1.next())  
			{	System.out.println("Hello"+count);
				Swift t1=new Swift(rs1.getString(1),rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11),rs1.getString(12),rs1.getString(13),rs1.getString(14),rs1.getString(15));
				b[i++]=t1;
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

	

	@Override
	public Bank_to_Customer[] getAllBankToCustomerDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs=stmt1.executeQuery("select * from bank_to_customer"); 
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while ( rs.next() )
			{
			    // Process the row.
			    count++;
			}
			rs.beforeFirst();
			Bank_to_Customer b[]=new Bank_to_Customer[count];
			while(rs.next())  
			{	
				int s1=rs.getInt(1);
				PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE); 
				stmt.setInt(1, s1);
				ResultSet rs1=stmt.executeQuery();
				System.out.println("Hello"+count);
				if( rs1.first()){
				Bank_to_Customer t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
				b[i++]=t1;}
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

	@Override
	public Customer_to_Bank[] getAllCustomerToBankDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs=stmt1.executeQuery("select * from customer_to_bank"); 
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while ( rs.next() )
			{
			    // Process the row.
			    count++;
			}
			rs.beforeFirst();
			Customer_to_Bank b[]=new Customer_to_Bank[count];
			while(rs.next())  
			{	
				int s1=rs.getInt(1);
				PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE); 
				stmt.setInt(1, s1);
				ResultSet rs1=stmt.executeQuery();
				System.out.println("Hello"+count);
				if( rs1.first()){
				Customer_to_Bank t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
				b[i++]=t1;}
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

@Override
public Bank getBankDetails(String swift_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from bank where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setString(1, swift_id);
		//System.out.println("Hello");
		Bank b1=null;
		ResultSet rs1=stmt1.executeQuery(); 
		while(rs1.next())  
		{	
			Map<String,String> m=new HashMap<String,String>();
			String s1=rs1.getString(1);
			PreparedStatement stmt=con.prepareStatement("select * from nostro_accounts where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setString(1, s1);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
					m.put(rs.getString(2), rs.getString(3));
				
			}
			b1=new Bank(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(14),rs1.getString(4),m,rs1.getString(5),rs1.getString(6),rs1.getDate(7),rs1.getString(10),rs1.getString(11),rs1.getString(12),rs1.getString(13),rs1.getInt(8),rs1.getString(9));
			
		}
		con.close();
		return b1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Swift getSwiftDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from swift where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery(); 
		Swift t1=null;
		
		while(rs1.next())  
		{	
			t1=new Swift(rs1.getString(1),rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11),rs1.getString(12),rs1.getString(13),rs1.getString(14),rs1.getString(15));
			
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction getCustomerTransactionDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery();
		
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			}
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer getBankToCustomerDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery();
		
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			}
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank getCustomerToBankDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery();
		
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			}
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public boolean addBank(Bank b) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into bank values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, b.getSwift_id());
		stmt1.setString(2, b.getBank_name());
		stmt1.setInt(3, b.getReg_no());
		stmt1.setString(5, b.getCategory());
		stmt1.setString(4, b.getAcc_no());
		stmt1.setString(6, b.getDir_name());
		stmt1.setDate(7, b.getD());
		stmt1.setString(10, b.getAddress());
		stmt1.setInt(8, b.getContact());
		stmt1.setString(9, b.getPan_no());
		stmt1.setString(11, b.getCity());
		stmt1.setString(12, b.getState());
		stmt1.setString(13, b.getCountry());
		stmt1.setString(14, b.getCurrency());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			
			for ( Map.Entry<String, String> entry : b.getNostro_accounts().entrySet()) {
			    String key = entry.getKey();
			    String tab = entry.getValue();
			    PreparedStatement stmt=con.prepareStatement("insert into nostro_accounts values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setString(1, b.getSwift_id());
				stmt.setString(2, key);
				stmt.setString(3, tab);
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		}
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addSwift(Swift s) {
	boolean s1=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into swift values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, s.getMessage_code());
		stmt1.setInt(2, s.getTransaction_id());
		stmt1.setString(3, s.getSender());
		stmt1.setString(4, s.getReceiver());
		stmt1.setString(5, s.getMessage_text());
		stmt1.setString(6, s.getBank_operation_code());
		stmt1.setString(7, s.getSender_ref());
		stmt1.setString(8, s.getInterbank_settled_amount());
		stmt1.setString(9, s.getInstructed_amount());
		stmt1.setString(10,s.getOrdering_customer());
		stmt1.setString(11, s.getBeneficiary_customer());
		stmt1.setString(12,s.getSender_correspondent());
		stmt1.setString(13, s.getReceiver_correspondent());
		stmt1.setString(14, s.getRemit_info());
		stmt1.setString(15, s.getDetails_of_charges());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
					s1=true;
			}
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s1;
}
@Override
public boolean addCustomer_Transaction(Customer_Transaction ct) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, ct.getTransaction_id());
		stmt1.setString(2, ct.getMessage_code());
		stmt1.setString(3, ct.getCurrency_code());
		stmt1.setDouble(4, ct.getAmount());
		stmt1.setDate(5, ct.getTransaction_date());
		stmt1.setString(6, ct.getAml_status());
		stmt1.setString(7, ct.getStatus());
		stmt1.setString(8, ct.getComments());
		stmt1.setString(9, ct.getPayer_account());
		stmt1.setString(10, ct.getPayee_account());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    PreparedStatement stmt=con.prepareStatement("insert into customer_transaction values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, ct.getTransaction_id());
				stmt.setString(2,ct.getPayer_id());
				stmt.setString(3, ct.getPayee_id());
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addBank_to_Customer(Bank_to_Customer btc) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, btc.getTransaction_id());
		stmt1.setString(2, btc.getMessage_code());
		stmt1.setString(3, btc.getCurrency_code());
		stmt1.setDouble(4, btc.getAmount());
		stmt1.setDate(5, btc.getTransaction_date());
		stmt1.setString(6, btc.getAml_status());
		stmt1.setString(7, btc.getStatus());
		stmt1.setString(8, btc.getComments());
		stmt1.setString(9, btc.getPayer_account());
		stmt1.setString(10, btc.getPayee_account());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    PreparedStatement stmt=con.prepareStatement("insert into bank_to_customer values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, btc.getTransaction_id());
				stmt.setString(2,btc.getPayer_id());
				stmt.setString(3, btc.getPayee_id());
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addCustomer_to_Bank(Customer_to_Bank ctb) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, ctb.getTransaction_id());
		stmt1.setString(2, ctb.getMessage_code());
		stmt1.setString(3, ctb.getCurrency_code());
		stmt1.setDouble(4, ctb.getAmount());
		stmt1.setDate(5, ctb.getTransaction_date());
		stmt1.setString(6, ctb.getAml_status());
		stmt1.setString(7, ctb.getStatus());
		stmt1.setString(8, ctb.getComments());
		stmt1.setString(9, ctb.getPayer_account());
		stmt1.setString(10, ctb.getPayee_account());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    PreparedStatement stmt=con.prepareStatement("insert into customer_to_bank values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, ctb.getTransaction_id());
				stmt.setString(2,ctb.getPayer_id());
				stmt.setString(3, ctb.getPayee_id());
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}


@Override
public Customer_Transaction[] getAllPendingCustomerTransactionDetails(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where status=? and transaction_id in (select transaction_id from customer_transaction)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while (rs1.next())
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			System.out.println("Hello"+count);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			Customer_Transaction t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getAllPendingBankToCustomerDetails(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where status=? and transaction_id in (select transaction_id from bank_to_customer)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Hello"+count);
			if( rs.first()){
			Bank_to_Customer t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getAllPendingCustomerToBankDetails(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where status=? and transaction_id in (select transaction_id from customer_to_bank)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Hello"+count);
			if( rs.first()){
			Customer_to_Bank t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}

@Override
public Customer_Transaction[] getCustomerTransactionDetailsbyPayerId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_transaction where payer_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getBankToCustomerDetailsbyPayerId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from bank_to_customer where payer_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getCustomerToBankDetailsbyPayerId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_to_bank where payer_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction[] getCustomerTransactionDetailsbyPayeeId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_transaction where payee_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getBankToCustomerDetailsbyPayeeId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from bank_to_customer where payee_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getCustomerToBankDetailsbyPayeeId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_to_bank where payee_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction[] getCustomerTransactionDetailsbyDate(Date d) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_date=? and transaction_id in (select transaction_id from customer_transaction)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setDate(1, d);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getBankToCustomerDetailsbyDate(Date d) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_date=? and transaction_id in (select transaction_id from bank_to_customer)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setDate(1, d);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getCustomerToBankDetailsbyDate(Date d) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_date=? and transaction_id in (select transaction_id from customer_to_bank)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setDate(1, d);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public boolean addSanctionedCountry(String code,String country) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		Statement s1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rss=s1.executeQuery("select count(*) from sanc_countries");
		int count=0;
		while(rss.next()) {
			count=rss.getInt(1);
		}
		count+=1;
		PreparedStatement stmt1=con.prepareStatement("insert into sanc_countries values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setInt(1, count);
		stmt1.setString(2, country);
		stmt1.setString(3, code);
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addSanctionedName(String name) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		Statement s1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rss=s1.executeQuery("select count(*) from sanc_users");
		int count=0;
		while(rss.next()) {
			count=rss.getInt(1);
		}
		count+=1;
		PreparedStatement stmt1=con.prepareStatement("insert into sanc_users values(?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setInt(1, count);
		stmt1.setString(2, name);
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean isCountrySanctioned(String code) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement s1=con.prepareStatement("select count(*) from sanc_countries where code=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		s1.setString(1, code);
		ResultSet rss=s1.executeQuery();
		int rs1=0;
		while(rss.next()) {
		rs1=rss.getInt(1);
		}
		if(rs1!=0)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean isPersonSanctioned(String name) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement s1=con.prepareStatement("select count(*) from sanc_users where name=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		s1.setString(1, name);
		ResultSet rss=s1.executeQuery();
		int rs1=0;
		while(rss.next()) {
		rs1=rss.getInt(1);
		}
		
		if(rs1!=0)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}

@Override
public boolean addFXData(FXData[] fxd) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		int count=fxd.length;
		PreparedStatement p=null;
		for(int i=0;i<count;i++) {
			p=con.prepareStatement("insert into fx_data values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			p.setString(1, fxd[i].getCurrency());
			p.setDouble(2, fxd[i].getRate());
			p.setString(3, fxd[i].getCountry());
			int rs=p.executeUpdate();
			if(rs==1)
				s=true;
		}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public double getFXData(String currency) {
	double s=0;
	try {
		Connection con=c.getConnection();
		
		PreparedStatement p=null;
		
			p=con.prepareStatement("select rate from fx_data where currency=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			p.setString(1, currency);
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				s=rs.getDouble(1);
			}
		con.close();
		}
		catch(Exception e){ System.out.println(e);} 
	return s;
}
@Override
public FXData[] getAllFXData() {
	
	try {
		Connection con=c.getConnection();
		Statement s1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=s1.executeQuery("select * from fx_data");
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs.next() )
		{
		    // Process the row.
		    count++;
		}
		rs.beforeFirst();
		FXData fxd[]=new FXData[count];
			while(rs.next()) {
				FXData f1=new FXData(rs.getString(1),rs.getDouble(2),rs.getString(3));
				fxd[i++]=f1;
			}
			
		con.close();
		return fxd;
		}
		catch(Exception e){ System.out.println(e);} 
	return null;
}
@Override
public Map<String, String> getNostroAccounts(String swift_id) {
	Map<String,String> s=new HashMap<String,String>();
	try {
		Connection con=c.getConnection();
		
		PreparedStatement p=null;
		
			p=con.prepareStatement("select * from nostro_accounts where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			p.setString(1, swift_id);
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				s.put(rs.getString(2), rs.getString(3));
			}
		con.close();
		}
		catch(Exception e){ System.out.println(e);} 
	return s;
}
@Override
public Vostro_Accounts[] getVostroAccounts(String swift_id) {
	
	try {
		Connection con=c.getConnection();
		
		PreparedStatement p=null;
		
			p=con.prepareStatement("select * from vostro_accounts where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			p.setString(1, swift_id);
			ResultSet rs=p.executeQuery();
			int i=0,count=0;
			while (rs.next())
			{
			    // Process the row.
			    count++;
			}
			rs.beforeFirst();
			Vostro_Accounts v[]=new Vostro_Accounts[count];
			while(rs.next()) {
				Vostro_Accounts v1=new Vostro_Accounts(swift_id,rs.getString(2),rs.getString(3));
				v[i++]=v1;
			}
		con.close();
		return v;
		}
		catch(Exception e){ System.out.println(e);} 
	return null;
}
@Override
public boolean addVostroAccount(Vostro_Accounts v) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into vostro_accounts values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, v.getSwift_id());
		stmt1.setString(2, v.getOther_bank_swift_id());
		stmt1.setString(3, v.getAccount_no());
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			
			s=true;
		}
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public Vostro_Accounts[] getAllVostroAccounts() {
	try {
		Connection con=c.getConnection();
		
		Statement p=null;
		
			p=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=p.executeQuery("select * from vostro_accounts");
			int i=0,count=0;
			while (rs.next())
			{
			    // Process the row.
			    count++;
			}
			rs.beforeFirst();
			Vostro_Accounts v[]=new Vostro_Accounts[count];
			while(rs.next()) {
				Vostro_Accounts v1=new Vostro_Accounts(rs.getString(1),rs.getString(2),rs.getString(3));
				v[i++]=v1;
			}
		con.close();
		return v;
		}
		catch(Exception e){ System.out.println(e);} 
	return null;
}

@Override
public boolean updateAmlStatus(int transaction_id,String status) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("update transaction set aml_status=? where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(2, transaction_id);
		stmt1.setString(1, status);
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
					s=true;
				
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean updateTransactionStatus(int transaction_id,String status) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("update transaction set status=? where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(2, transaction_id);
		stmt1.setString(1, status);
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
					s=true;
				
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public String getBankCurrency(String swift_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select currency from bank where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setString(1, swift_id);
		String retVal=null;
		ResultSet rs1=stmt1.executeQuery(); 
		while(rs1.next())  
		{	
			retVal=rs1.getString(1);
		}
		con.close();
		return retVal;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction[] getAllCustomerTransactionDetailsByAmlStatus(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where aml_status=? and transaction_id in (select transaction_id from customer_transaction)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while (rs1.next())
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			System.out.println("Hello"+count);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			Customer_Transaction t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getAllBankToCustomerDetailsByAmlStatus(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where aml_status=? and transaction_id in (select transaction_id from bank_to_customer)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Hello"+count);
			if( rs.first()){
			Bank_to_Customer t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getAllCustomerToBankDetailsByAmlStatus(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where aml_status=? and transaction_id in (select transaction_id from customer_to_bank)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Hello"+count);
			if( rs.first()){
			Customer_to_Bank t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}

public static void main(String[] args) throws ParseException {
	PaymentsImpl p=new PaymentsImpl();
	Map<String,String> m=new HashMap<String,String>();
	m.put("val1", "123");
	m.put("val2", "87");
	java.sql.Date sqlDate = java.sql.Date.valueOf("2017-08-22");
	//Bank myBank=new Bank("hh","aakriti",1,"1234",m,"as","sa",sqlDate,"er",234,"yu");
	//System.out.println(p.addBank(myBank));
	//Customer_Transaction ct=new Customer_Transaction(8,"abcd","f",67.5,sqlDate,"a","pending","c","d","e");
	//System.out.println(p.addCustomer_Transaction(ct));
	//Customer_Transaction ct1[]=p.getAllPendingCustomerTransactionDetails("pending");
	//System.out.println(ct1[0].getTransaction_id());
	//Customer_Transaction ct2=p.getCustomerTransactionDetailsbySellerId("hu");
	//System.out.println(ct2.getPayee_id()+" "+ct2.getAmount());
	
	/*p.addSanctionedCountry("hello","h");
	p.addSanctionedName("abcd");
	System.out.println(p.isCountrySanctioned("hello"));
	FXData[] fxd=new FXData[1];
	FXData fxdd=new FXData();
	fxdd.setCurrency("INR");
	fxdd.setRate(1000.01);fxd[0]=fxdd;
	System.out.println(p.addFXData(fxd));*/
	//Bank myBank=new Bank("swift_id","bank name",1,"currency","acc_no",m,"category","dirname",sqlDate,"add","city","state","country",123,"pan");
		//	p.addBank(myBank);
	//Bank b=p.getBankDetails("swift_id");
	//System.out.println(b.getAcc_no()+" "+b.getContact()+" "+b.getAddress()+" "+b.getCountry()+" "+b.getPan_no()+" "+b.getCurrency());
//Map<String,String> mm=p.getNostroAccounts("swift_id");
//Iterator it = mm.entrySet().iterator();
//while (it.hasNext()) {
  //  Map.Entry pair = (Map.Entry)it.next();
    //System.out.println(pair.getKey() + " = " + pair.getValue());}
//System.out.println("Hello+"+p.updateAmlStatus(2, "Amlnotdone"));
//System.out.println("Hello+"+p.updateTransactionStatus(500, "Pay Succ"));
	//System.out.println("Hello "+p.getBankCurrency("swift_id"));
	
	System.out.println("Hello "+p.getAllPendingCustomerTransactionDetails("Pay Succ").length);
System.out.println("hi "+p.getCustomerTransactionDetailsbyDate(sqlDate).length);
System.out.println("hello "+p.getAllCustomerTransactionDetailsByAmlStatus("Amlnotdone").length);
}


}