package com.rbs.scm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.rbs.scm.DAO.DataBaseConnection;
import com.rbs.scm.controller.AccountingManagementController;
import com.rbs.scm.model.ChartOfAccount;
import com.rbs.scm.model.GeneralLedger;

@Service("accountingManagementServiceObj")
public class AccountingManagementService {
	
	/*public List<String> getCOAswiftList()
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
	}*/
	public List<ChartOfAccount> getCOAList()
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		List<ChartOfAccount> coaList=new LinkedList<ChartOfAccount>();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='yes') ");		
			while(resultSet.next())
			{
				ChartOfAccount coa=new ChartOfAccount();
				coa.setHead(resultSet.getString("bank_name"));
				//coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("city"));
				//coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				//coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("swift_id"));
				coaList.add(coa);
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return coaList;
	}
	/*public List<ChartOfAccount> getCOAList()
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
	}*/
	
	public ChartOfAccount getCOA(String swiftID)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		ChartOfAccount coa=new ChartOfAccount();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM bank where swift_id='"+swiftID+"'");		
			while(resultSet.next())
			{
				coa.setHead(resultSet.getString("bank_name"));
				//coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("city"));
				//coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				//coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("swift_id"));
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
        		stmt.executeUpdate("update chart_of_accounts set addedornot='no' where swift_id='"+sID+"'");  
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
	public void addCOAService(String swift)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	stmt.executeUpdate("update chart_of_accounts set addedornot='yes' where swift_id='"+swift+"'");
      		con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	/*public void addCOAService(ChartOfAccount coa)
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
	}*/
	
	public void updateCOAtableService()
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	ResultSet resultSet = stmt.executeQuery("SELECT swift_id FROM chart_of_accounts");		
        	Set<String> swiftFromCOA=new HashSet<String>();
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("swift_id"));
				swiftFromCOA.add(resultSet.getString("swift_id"));
				/*Statement stmt1=con.createStatement(); 
				stmt1.executeUpdate("insert into chart_of_accounts values('"+resultSet.getString("swift_id")+"','no')");*/
			}
			resultSet = stmt.executeQuery("SELECT swift_id FROM bank");
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("swift_id"));
				if(swiftFromCOA.contains(resultSet.getString("swift_id")))
					continue;
				Statement stmt1=con.createStatement();
				stmt1.executeUpdate("insert into chart_of_accounts values('"+resultSet.getString("swift_id")+"','no')");
			}
        	con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	
	public List<String> getNoSwift_headService()
	{
		List<String> headList=new LinkedList<String>();
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	ResultSet resultSet = stmt.executeQuery("select bank_name from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')");	
        	System.out.println("get head list");
			while(resultSet.next())
			{
				System.out.println("Head: "+resultSet.getString("bank_name"));
				headList.add(resultSet.getString("bank_name"));
			}
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
		return headList;
	}
	
	public List<String> getNoSwift_countryService(String head)
	{
		List<String> countryList=new LinkedList<String>();
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	ResultSet resultSet = stmt.executeQuery("select country from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no') and bank_name = '"+head+"'");	
        	
			while(resultSet.next())
			{
				countryList.add(resultSet.getString("country"));
			}
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
		System.out.println("Service Countries: "+countryList);
		return countryList;
	}
	
	public List<String> getNoSwift_branchService(String head, String country)
	{
		List<String> branchList=new LinkedList<String>();
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	ResultSet resultSet = stmt.executeQuery("select city from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')"
        											+" and bank_name = '"+head
        											+"' and country = '"+country+"'");	
        	
			while(resultSet.next())
			{
				branchList.add(resultSet.getString("city"));
			}
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
		System.out.println("Service Countries: "+branchList);
		return branchList;
	}
	
	public List<String> getNoSwift_currencyService(String head, String country, String branch)
	{
		List<String> currencyList=new LinkedList<String>();
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	ResultSet resultSet = stmt.executeQuery("select currency from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')"
													+" and bank_name = '"+head
													+"' and country = '"+country
													+"' and city= '"+branch+"'");	
        	
			while(resultSet.next())
			{
				currencyList.add(resultSet.getString("currency"));
			}
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
		System.out.println("Service Countries: "+currencyList);
		return currencyList;
	}
	
	public List<String> getNoSwift_swiftService(String head, String country, String branch, String currency)
	{
		List<String> swiftList=new LinkedList<String>();
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	ResultSet resultSet = stmt.executeQuery("select swift_id from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')"
													+" and bank_name = '"+head
													+"' and country = '"+country
													+"' and city= '"+branch
													+"' and currency = '"+currency+"'");	
        	
			while(resultSet.next())
			{
				swiftList.add(resultSet.getString("swift_id"));
			}
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
		return swiftList;
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