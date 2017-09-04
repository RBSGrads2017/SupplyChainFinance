package com.rbs.scm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.rbs.scm.DAO.DataBaseConnection;
import com.rbs.scm.controller.AccountingManagementController;
import com.rbs.scm.model.ChartOfAccount;
import com.rbs.scm.model.GeneralLedger;

@Service("accountingManagementServiceObj")
public class AccountingManagementService {
	
	public List<String> getCOAswiftList()
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		List<String> swiftList=new LinkedList<String>();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT productSwiftId FROM ChartOfAccounts");		
			while(resultSet.next())
			{
				swiftList.add(resultSet.getString("productSwiftId"));
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return swiftList;
	}
	
	public List<ChartOfAccount> getCOAList()
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		List<ChartOfAccount> coaList=new LinkedList<ChartOfAccount>();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChartOfAccounts");		
			while(resultSet.next())
			{
				ChartOfAccount coa=new ChartOfAccount();
				coa.setHead(resultSet.getString("head"));
				coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("branch"));
				coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("productSwiftID"));
				coaList.add(coa);
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return coaList;
	}
	
	public ChartOfAccount getCOA(String swiftID)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		ChartOfAccount coa=new ChartOfAccount();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChartOfAccounts where productSwiftID='"+swiftID+"'");		
			while(resultSet.next())
			{
				coa.setHead(resultSet.getString("head"));
				coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("branch"));
				coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("productSwiftID"));
				return coa;
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return coa;
	}
	
	public List<GeneralLedger> getEachGLEntry() {
		DataBaseConnection dbobj = new DataBaseConnection();
		
		List<GeneralLedger> entries = null;
			try {
				Connection con = dbobj.getConnection();
			Statement statement = 	con.createStatement();  
			System.out.println("before select in viewGList");
			
			ResultSet resultSet1 = statement.executeQuery("SELECT * FROM General_Ledger");
			while(resultSet1.next()){
			getdetails(resultSet1.getString("Account_Entry_No"));
			}
			ResultSet resultSet = statement.executeQuery("SELECT * FROM General_Ledger");
			System.out.println("after select in viewGList");
			entries=new ArrayList<GeneralLedger>();
			
			while (resultSet.next()) {
				System.out.println("after select in viewGList");
				GeneralLedger gl=new GeneralLedger();
				gl.setAccountEntryNo(resultSet.getString("Account_Entry_No"));
				gl.setCurrentDate(resultSet.getDate("Current_Date"));
				gl.setPaymentDate(resultSet.getDate("Payment_Date"));
				gl.setTransactionNo(resultSet.getString("Transaction_No"));
				gl.setCustomerAccountNo(resultSet.getString("Customer_Account_No"));
				gl.setSwiftID(resultSet.getString("SWIFTID"));
				gl.setInvoiceNo(resultSet.getString("Invoice_No"));
				gl.setDrOrCr(resultSet.getString("Dr_Cr"));
				gl.setAmount(resultSet.getDouble("Amount"));
				gl.setDueDate(resultSet.getDate("Due_date"));
				System.out.println("WHile loop"+resultSet.getString("Account_Entry_No"));
				System.out.println(gl.toString());
				entries.add(gl);	
			}
			con.close();
			}
			catch(Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
		
			return entries;
		}
	
	public List<GeneralLedger> getEachGLEntryBySearch(String acEntryNo,String transNo,String custAcNo,String swiftID,String invoiceNo,String drCr,String paymentDate,String dueDate) {
		DataBaseConnection dbobj = new DataBaseConnection();
		List<GeneralLedger> entries = null;
		
			try{
				Connection con = dbobj.getConnection();
				Statement statement = 	con.createStatement();
				ResultSet resultSet=null;
				if(paymentDate.equals("")&&transNo.equals("")&&dueDate.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%') and "
						+"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
						+"(customer_account_no like '%"+custAcNo+"%') and "
						+"(swiftID like '%"+swiftID+"%') and "
						+"(invoice_no like '%"+invoiceNo+"%') and "
						+"(dr_cr like '%"+drCr+"%') and "
						+"(payment_date like '%"+paymentDate +"%' or payment_date is null) and "
						+"(due_date like '%"+dueDate+"%' or due_date is null)");
				//******************************************
				else if(paymentDate.equals("")&&transNo.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%') and "
						//	+"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
							+"(customer_account_no like '%"+custAcNo+"%') and "
							+"(swiftID like '%"+swiftID+"%') and "
							+"(invoice_no like '%"+invoiceNo+"%') and "
							+"(dr_cr like '%"+drCr+"%') and "
						//	+"(payment_date like '%"+paymentDate +"%' or payment_date is null) and "
							+"(due_date = '"+dueDate+"')");
				else if(/*(paymentDate.equals("")&&dueDate.equals(""))||*/(transNo.equals("")&&dueDate.equals(""))||(!paymentDate.equals("")&&!transNo.equals("")&&dueDate.equals("")))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%') and "
					//		+"(transaction_no like '%"+transNo+"%') and "
							+"(customer_account_no like '%"+custAcNo+"%') and "
							+"(swiftID like '%"+swiftID+"%') and "
							+"(invoice_no like '%"+invoiceNo+"%') and "
							+"(dr_cr like '%"+drCr+"%') and "
							+"(payment_date = '"+paymentDate +"')");
					//		+"(payment_date = '"+paymentDate +"') and "
					//		+"(due_date like '%"+dueDate+"%' or due_date is null)");
				else if(paymentDate.equals("")&&dueDate.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%') and "
							+"(transaction_no like '%"+transNo+"%') and "
							+"(customer_account_no like '%"+custAcNo+"%') and "
							+"(swiftID like '%"+swiftID+"%') and "
							+"(invoice_no like '%"+invoiceNo+"%') and "
							+"(dr_cr like '%"+drCr+"%')");
						//	+"(dr_cr like '%"+drCr+"%') and "
						//	+"(payment_date = '"+paymentDate +"') and "
						//	+"(due_date like '%"+dueDate+"%' or due_date is null)");
				else if(!transNo.equals("")&&!dueDate.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%') and "
						+"(transaction_no like '%"+transNo+"%') and "
						+"(customer_account_no like '%"+custAcNo+"%') and "
						+"(swiftID like '%"+swiftID+"%') and "
						+"(invoice_no like '%"+invoiceNo+"%') and "
						+"(dr_cr like '%"+drCr+"%') and "
					//	+"(payment_date = '"+paymentDate +"') and "
						+"(due_date = '"+dueDate+"')");
				//else if(paymentDate.equals("")&&dueDate.equals(""))
				else
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%') and "
					//	+"(transaction_no like '%"+transNo+"%') and "
						+"(customer_account_no like '%"+custAcNo+"%') and "
						+"(swiftID like '%"+swiftID+"%') and "
						+"(invoice_no like '%"+invoiceNo+"%') and "
						+"(dr_cr like '%"+drCr+"%') and "
						+"(payment_date = '"+paymentDate +"') and "
						+"(due_date = '"+dueDate+"')");
			
			entries=new ArrayList<GeneralLedger>();
			while (resultSet.next()) {
				GeneralLedger gl=new GeneralLedger();
				gl.setAccountEntryNo(resultSet.getString("Account_Entry_No"));
				gl.setCurrentDate(resultSet.getDate("Current_Date"));
				gl.setPaymentDate(resultSet.getDate("Payment_Date"));
				gl.setTransactionNo(resultSet.getString("Transaction_No"));
				gl.setCustomerAccountNo(resultSet.getString("Customer_Account_No"));
				gl.setSwiftID(resultSet.getString("SWIFTID"));
				gl.setInvoiceNo(resultSet.getString("Invoice_No"));
				gl.setDrOrCr(resultSet.getString("Dr_Cr"));
				gl.setAmount(resultSet.getDouble("Amount"));
				gl.setDueDate(resultSet.getDate("Due_date"));
				
				entries.add(gl);			
			}
			}
			catch(Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
			return entries;
		}
	
	public void deleteCOASingle(String sID)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	//String[] chartNamesToDelete=request.getParameterValues("chartGroup");
        		stmt.executeUpdate("delete from ChartOfAccounts where productSwiftID='"+sID+"'");  
      		con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	
	public void deleteCOA(List<String> swiftIDList)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	//String[] chartNamesToDelete=request.getParameterValues("chartGroup");
        	for(String sID:swiftIDList)
        		stmt.executeUpdate("delete from ChartOfAccounts where productSwiftID='"+sID+"'");  
      		con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	public void addCOAService(ChartOfAccount coa)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	stmt.executeUpdate("insert into ChartOfAccounts values('"+coa.getHead()+"','"+coa.getLegalEntity()+"','"+coa.getCountry()+"','"+coa.getBranch()+"','"+coa.getProduct()+"','"+coa.getCurrency()+"',"+coa.getBook()+",'"+coa.getProductSwiftID()+"')");
      		con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	public   List<String> sanctionedCountries(){  
		 
		 
		 List<String> lstCountries =null;
		 
		 DataBaseConnection dbobj = new DataBaseConnection();
			try
		    {
				Connection con = dbobj.getConnection();
   	  
			   	//Step 3 Create the statement object
			       Statement stmt = con.createStatement();
			       
			       //Step 4 execute query
			       ResultSet rs = stmt.executeQuery("select * from sanctionedCountries");
			       lstCountries = new ArrayList<String>();
			      while(rs.next()){
			       	String Country = rs.getString("countryName");
			       	lstCountries.add(Country);     
			      } 
			       
			       //Step 5 close the connection
			       con.close();
			
			    }
			     catch(Exception e){
			   	  System.out.println("Exception - get rectangle"+e.getMessage());
			     }
			     
     return lstCountries;
}
	 
public   List<String> sanctionedIndividuals(){  
		 
	
		 List<String> lstNames =null;
		 DataBaseConnection dbobj = new DataBaseConnection();
			try
		    {
				Connection con = dbobj.getConnection();
   	  
				   	//Step 3 Create the statement object
				       Statement stmt = con.createStatement();
				       
				       //Step 4 execute query
				
				      
				      ResultSet rs1 = stmt.executeQuery("select * from sanctionedIndividuals");
				      lstNames = new ArrayList<String>();
				     while(rs1.next()){
				      	String name = rs1.getString("IndividualName");
				      	lstNames.add(name);     
				     } 
				       
				       //Step 5 close the connection
				       con.close();
				
				    }
				     catch(Exception e){
				   	  System.out.println("Exception - get rectangle"+e.getMessage());
     }
     
     return lstNames;
}

public void getdetails(String accountEntryNo){

	AccountingManagementController DateLogicObj = new AccountingManagementController();
	DataBaseConnection dbobj = new DataBaseConnection();
	System.out.println("before try");
	try{
		System.out.println("before con");
		Connection con = dbobj.getConnection();
	System.out.println("After con");
	Statement statement = con.createStatement();
	System.out.println("After statement");
	ResultSet resultSet = statement.executeQuery("SELECT payment_date, Amount,due_date FROM General_Ledger where account_entry_no= '"+accountEntryNo+"' and DR_CR ='Dr' ");
	System.out.println("After select");
	while(resultSet.next()){
	Date dueDate = resultSet.getDate("Due_date");
	 System.out.println("before if");
	if(((TimeUnit.MINUTES.convert(dueDate.getTime() - new java.util.Date().getTime(),TimeUnit.MILLISECONDS))/(60*24))>=0){
	    System.out.println("inside if");
		double accuredIncome = DateLogicObj.calculateAccruedIncome(resultSet.getDate("payment_date"), resultSet.getDate("Due_date"), resultSet.getDouble("Amount")); 
		System.out.println("accrued income "+accuredIncome);
		System.out.println("after calling function");
		String newaccountEntryNo = accountEntryNo.substring(0, 7)+'3'+accountEntryNo.substring(8);
		statement.executeQuery("update General_Ledger set amount ="+accuredIncome+" where account_entry_no= '"+newaccountEntryNo+"'");
		System.out.println("after updating");
	}
	break;
	}
	}
	
	catch(Exception e){
		System.out.println("Exception !!  "+e.getMessage());
	}

}	
}