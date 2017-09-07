package com.rbs.scm.teamg_accounting.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.rbs.scm.teamg_accounting.controller.AccountingManagementController;
import com.rbs.scm.teamg_accounting.dao.DataBaseConnection;
import com.rbs.scm.teamg_accounting.dao.DatabaseConnectionPostgreSQL;
import com.rbs.scm.teamg_accounting.model.ChartOfAccount;
import com.rbs.scm.teamg_accounting.model.GeneralLedger;

@Service("accountingManagementServiceObj")
public class AccountingManagementService {
	static DatabaseConnectionPostgreSQL dbobj1;
	static Connection con;
	private static long crNo;
	private static long drNo;
	private static long acrNo;
	private static long iRNo;

	public List<ChartOfAccount> getCOAList() {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		// DataBaseConnection dbobj = new DataBaseConnection();
		List<ChartOfAccount> coaList = new LinkedList<ChartOfAccount>();
		try {
			Connection con = dbobj1.getConnection();
			Statement statement = con.createStatement();
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='yes') ");
			while (resultSet.next()) {
				ChartOfAccount coa = new ChartOfAccount();
				coa.setHead(resultSet.getString("bank_name"));
				// coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("city"));
				// coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				// coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("swift_id"));
				coaList.add(coa);
			}
			// con.close();
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return coaList;
	}

	public ChartOfAccount getCOA(String swiftID) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		// DataBaseConnection dbobj = new DataBaseConnection();
		ChartOfAccount coa = new ChartOfAccount();
		try {
			Connection con = dbobj1.getConnection();
			Statement statement = con.createStatement();
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM bank where swift_id='" + swiftID + "'");
			while (resultSet.next()) {
				coa.setHead(resultSet.getString("bank_name"));
				// coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("city"));
				// coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				// coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("swift_id"));
				return coa;
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return coa;
	}
	

	public List<GeneralLedger> getEachGLEntry() throws SQLException {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		//DataBaseConnection dbobj = new DataBaseConnection();
		Connection con = null;
		List<GeneralLedger> entries = null;
		try {
			 con = dbobj1.getConnection();
			Statement statement = con.createStatement();
			System.out.println("before select in viewGList");

			/*ResultSet resultSet1 = statement.executeQuery("SELECT * FROM general_ledger");
			while (resultSet1.next()) {
				getdetails(resultSet1.getString("Account_Entry_No"));
			}*/
			ResultSet resultSet = statement.executeQuery("SELECT * FROM general_ledger");
			System.out.println("after select in viewGList");
			entries = new ArrayList<GeneralLedger>();

			while (resultSet.next()) {
				System.out.println("after select in viewGList");
				GeneralLedger gl = new GeneralLedger();
				
				gl.setAccountEntryNo(resultSet.getString("account_entry_no"));
				//gl.setCurrentDate(resultSet.getDate("Current_Date"));
				gl.setPaymentDate(resultSet.getDate("payment_date"));
				gl.setTransactionNo(resultSet.getString("transaction_no"));
				gl.setCustomerAccountNo(resultSet.getString("customer_account_no"));
				gl.setSwiftID(resultSet.getString("swift_id"));
				gl.setInvoiceNo(resultSet.getString("invoice_no"));
				gl.setDrOrCr(resultSet.getString("dr_cr"));
				gl.setAmount(resultSet.getDouble("amount"));
				gl.setDueDate(resultSet.getDate("due_date"));
				System.out.println("WHile loop" + resultSet.getString("account_entry_no"));
				System.out.println(gl.toString());
				entries.add(gl);
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return entries;
	}

	public List<GeneralLedger> getEachGLEntryBySearch(String acEntryNo, String transNo, String custAcNo, String swiftID,
			String invoiceNo, String drCr, String paymentDate, String dueDate) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		//DataBaseConnection dbobj = new DataBaseConnection();
		List<GeneralLedger> entries = null;

		try {
			Connection con = dbobj1.getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = null;
			System.out.println("Filtering before any select");
			if (paymentDate.equals("") && transNo.equals("") && dueDate.equals("")){
				System.out.println("Within if");
				resultSet = statement.executeQuery("SELECT * FROM general_ledger where (account_entry_no like'%"
						+ acEntryNo + "%') and " + "(transaction_no like '%" + transNo
						+ "%' or transaction_no is null) and " + "(customer_account_no like '%" + custAcNo + "%') and "
						+ "(swift_id like '%" + swiftID + "%') and " + "(invoice_no like '%" + invoiceNo + "%') and "
						+ "(dr_cr like '%" + drCr + "%')");
			}
			// ******************************************
			else if (paymentDate.equals("") && transNo.equals(""))
				resultSet = statement.executeQuery(
						"SELECT * FROM general_ledger where (account_entry_no like'%" + acEntryNo + "%') and "
						// +"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
								+ "(customer_account_no like '%" + custAcNo + "%') and " + "(swift_id like '%" + swiftID
								+ "%') and " + "(invoice_no like '%" + invoiceNo + "%') and " + "(dr_cr like '%" + drCr
								+ "%') and "
								// +"(payment_date like '%"+paymentDate +"%' or payment_date is null) and "
								+ "(due_date = '" + dueDate + "')");
			else if (/* (paymentDate.equals("")&&dueDate.equals(""))|| */(transNo.equals("") && dueDate.equals(""))
					|| (!paymentDate.equals("") && !transNo.equals("") && dueDate.equals("")))
				resultSet = statement.executeQuery(
						"SELECT * FROM general_ledger where (account_entry_no like'%" + acEntryNo + "%') and "
						// +"(transaction_no like '%"+transNo+"%') and "
								+ "(customer_account_no like '%" + custAcNo + "%') and " + "(swift_id like '%" + swiftID
								+ "%') and " + "(invoice_no like '%" + invoiceNo + "%') and " + "(dr_cr like '%" + drCr
								+ "%') and " + "(payment_date = '" + paymentDate + "')");
			// +"(payment_date = '"+paymentDate +"') and "
			// +"(due_date like '%"+dueDate+"%' or due_date is null)");
			else if (paymentDate.equals("") && dueDate.equals(""))
				resultSet = statement.executeQuery(
						"SELECT * FROM general_ledger where (account_entry_no like'%" + acEntryNo + "%') and "
								+ "(transaction_no like '%" + transNo + "%') and " + "(customer_account_no like '%"
								+ custAcNo + "%') and " + "(swift_id like '%" + swiftID + "%') and "
								+ "(invoice_no like '%" + invoiceNo + "%') and " + "(dr_cr like '%" + drCr + "%')");
			// +"(dr_cr like '%"+drCr+"%') and "
			// +"(payment_date = '"+paymentDate +"') and "
			// +"(due_date like '%"+dueDate+"%' or due_date is null)");
			else if (!transNo.equals("") && !dueDate.equals(""))
				resultSet = statement.executeQuery(
						"SELECT * FROM general_ledger where (account_entry_no like'%" + acEntryNo + "%') and "
								+ "(transaction_no like '%" + transNo + "%') and " + "(customer_account_no like '%"
								+ custAcNo + "%') and " + "(swift_id like '%" + swiftID + "%') and "
								+ "(invoice_no like '%" + invoiceNo + "%') and " + "(dr_cr like '%" + drCr + "%') and "
								// +"(payment_date = '"+paymentDate +"') and "
								+ "(due_date = '" + dueDate + "')");
			// else if(paymentDate.equals("")&&dueDate.equals(""))
			else
				resultSet = statement.executeQuery(
						"SELECT * FROM general_ledger where (account_entry_no like'%" + acEntryNo + "%') and "
						// +"(transaction_no like '%"+transNo+"%') and "
								+ "(customer_account_no like '%" + custAcNo + "%') and " + "(swift_id like '%" + swiftID
								+ "%') and " + "(invoice_no like '%" + invoiceNo + "%') and " + "(dr_cr like '%" + drCr
								+ "%') and " + "(payment_date = '" + paymentDate + "') and " + "(due_date = '" + dueDate
								+ "')");

			System.out.println("Filtering after all selects");
			entries = new ArrayList<GeneralLedger>();
			while (resultSet.next()) {
				GeneralLedger gl = new GeneralLedger();
				gl.setAccountEntryNo(resultSet.getString("account_entry_no"));
				//gl.setCurrentDate(resultSet.getDate("current_date"));
				gl.setPaymentDate(resultSet.getDate("payment_date"));
				gl.setTransactionNo(resultSet.getString("transaction_no"));
				gl.setCustomerAccountNo(resultSet.getString("customer_account_no"));
				gl.setSwiftID(resultSet.getString("swift_id"));
				gl.setInvoiceNo(resultSet.getString("invoice_no"));
				gl.setDrOrCr(resultSet.getString("dr_cr"));
				gl.setAmount(resultSet.getDouble("amount"));
				gl.setDueDate(resultSet.getDate("due_date"));

				System.out.println("Filtered ac:"+gl.getAccountEntryNo());
				entries.add(gl);
			}
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return entries;
	}

	public void deleteCOASingle(String sID) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			// String[] chartNamesToDelete=request.getParameterValues("chartGroup");
			stmt.executeUpdate("update chart_of_accounts set addedornot='no' where swift_id='" + sID + "'");
			con.commit();
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteCOA(List<String> swiftIDList) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			// String[] chartNamesToDelete=request.getParameterValues("chartGroup");
			for (String sID : swiftIDList)
				stmt.executeUpdate("delete from ChartOfAccounts where productSwiftID='" + sID + "'");
			con.commit();
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addCOAService(String swift) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("update chart_of_accounts set addedornot='yes' where swift_id='" + swift + "'");
			con.commit();
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * public void addCOAService(ChartOfAccount coa) { DataBaseConnection dbobj =
	 * new DataBaseConnection(); try { Connection con = dbobj.getConnection();
	 * Statement stmt=con.createStatement();
	 * stmt.executeUpdate("insert into ChartOfAccounts values('"+coa.getHead()+"','"
	 * +coa.getLegalEntity()+"','"+coa.getCountry()+"','"+coa.getBranch()+"','"+coa.
	 * getProduct()+"','"+coa.getCurrency()+"',"+coa.getBook()+",'"+coa.
	 * getProductSwiftID()+"')"); con.commit(); con.close(); }
	 * 
	 * catch(Exception e) { System.out.println("Exception " + e.getMessage()); } }
	 */

	public void updateCOAtableService() {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT swift_id FROM chart_of_accounts");
			Set<String> swiftFromCOA = new HashSet<String>();
			while (resultSet.next()) {
				System.out.println(resultSet.getString("swift_id"));
				swiftFromCOA.add(resultSet.getString("swift_id"));
				/*
				 * Statement stmt1=con.createStatement();
				 * stmt1.executeUpdate("insert into chart_of_accounts values('"+resultSet.
				 * getString("swift_id")+"','no')");
				 */
			}
			resultSet = stmt.executeQuery("SELECT swift_id FROM bank");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("swift_id"));
				if (swiftFromCOA.contains(resultSet.getString("swift_id")))
					continue;
				Statement stmt1 = con.createStatement();
				stmt1.executeUpdate(
						"insert into chart_of_accounts values('" + resultSet.getString("swift_id") + "','no')");
			}
			con.commit();
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> getNoSwift_headService() {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		List<String> headList = new LinkedList<String>();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(
					"select bank_name from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')");
			System.out.println("get head list");
			while (resultSet.next()) {
				System.out.println("Head: " + resultSet.getString("bank_name"));
				headList.add(resultSet.getString("bank_name"));
			}
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return headList;
	}

	public List<String> getNoSwift_countryService(String head) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		List<String> countryList = new LinkedList<String>();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(
					"select country from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no') and bank_name = '"
							+ head + "'");

			while (resultSet.next()) {
				countryList.add(resultSet.getString("country"));
			}
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Service Countries: " + countryList);
		return countryList;
	}

	public List<String> getNoSwift_branchService(String head, String country) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		List<String> branchList = new LinkedList<String>();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(
					"select city from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')"
							+ " and bank_name = '" + head + "' and country = '" + country + "'");

			while (resultSet.next()) {
				branchList.add(resultSet.getString("city"));
			}
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Service Countries: " + branchList);
		return branchList;
	}

	public List<String> getNoSwift_currencyService(String head, String country, String branch) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		List<String> currencyList = new LinkedList<String>();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(
					"select currency from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')"
							+ " and bank_name = '" + head + "' and country = '" + country + "' and city= '" + branch
							+ "'");

			while (resultSet.next()) {
				currencyList.add(resultSet.getString("currency"));
			}
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Service Countries: " + currencyList);
		return currencyList;
	}

	public List<String> getNoSwift_swiftService(String head, String country, String branch, String currency) {
		dbobj1 = new DatabaseConnectionPostgreSQL();
		List<String> swiftList = new LinkedList<String>();
		// DataBaseConnection dbobj = new DataBaseConnection();
		try {
			Connection con = dbobj1.getConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(
					"select swift_id from bank where swift_id in (SELECT swift_id FROM chart_of_accounts where addedornot='no')"
							+ " and bank_name = '" + head + "' and country = '" + country + "' and city= '" + branch
							+ "' and currency = '" + currency + "'");

			while (resultSet.next()) {
				swiftList.add(resultSet.getString("swift_id"));
			}
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		} finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return swiftList;
	}

	public List<String> sanctionedCountries() {

		List<String> lstCountries = null;
		dbobj1 = new DatabaseConnectionPostgreSQL();
		try {
			Connection con = dbobj1.getConnection();

			// Step 3 Create the statement object
			Statement stmt = con.createStatement();

			// Step 4 execute query
			ResultSet rs = stmt.executeQuery("select code from \"sanc_countries\"");
			lstCountries = new ArrayList<String>();
			while (rs.next()) {
				String Country = rs.getString("countryName");
				lstCountries.add(Country);
			}

			// Step 5 close the connection
			

		} catch (Exception e) {
			System.out.println("Exception - get rectangle" + e.getMessage());
		}
		finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstCountries;
	}

	public List<String> sanctionedIndividuals() {

		List<String> lstNames = null;
		dbobj1 = new DatabaseConnectionPostgreSQL();
		try {
			Connection con = dbobj1.getConnection();

			// Step 3 Create the statement object
			Statement stmt = con.createStatement();

			// Step 4 execute query

			ResultSet rs1 = stmt.executeQuery("select name from sanc_users");
			lstNames = new ArrayList<String>();
			while (rs1.next()) {
				String name = rs1.getString("IndividualName");
				lstNames.add(name);
			}

			// Step 5 close the connection
			

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		finally {

			try {
				dbobj1.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstNames;
	}

	public void getdetails(String accountEntryNo) {

		AccountingManagementController DateLogicObj = new AccountingManagementController();
		DataBaseConnection dbobj = new DataBaseConnection();
		System.out.println("before try");
		try {
			System.out.println("before con");
			Connection con = dbobj.getConnection();
			System.out.println("After con");
			Statement statement = con.createStatement();
			System.out.println("After statement");
			ResultSet resultSet = statement
					.executeQuery("SELECT payment_date, amount,due_date FROM general_ledger where account_entry_no= '"
							+ accountEntryNo + "' and dr_cr ='Dr' ");
			System.out.println("After select");
			while (resultSet.next()) {
				Date dueDate = resultSet.getDate("due_date");
				System.out.println("before if");
				if (((TimeUnit.MINUTES.convert(dueDate.getTime() - new java.util.Date().getTime(),
						TimeUnit.MILLISECONDS)) / (60 * 24)) >= 0) {
					System.out.println("inside if");
					double accuredIncome = DateLogicObj.calculateAccruedIncome(resultSet.getDate("payment_date"),
							resultSet.getDate("due_date"), resultSet.getDouble("amount"));
					System.out.println("accrued income " + accuredIncome);
					System.out.println("after calling function");
					String newaccountEntryNo = accountEntryNo.substring(0, 7) + '3' + accountEntryNo.substring(8);
					statement.executeQuery("update general_ledger set amount =" + accuredIncome
							+ " where account_entry_no= '" + newaccountEntryNo + "'");
					System.out.println("after updating");
				}
				break;
			}
		}

		catch (Exception e) {
			System.out.println("Exception !!  " + e.getMessage());
		}

	}
	public void createGlEntry(long transactionID, int purchaseID,String payeeAcnt,String payerAcnt){
		// generateAccNoDAO dbobj = new generateAccNoDAO();
		//everything is debit here 
		dbobj1 = new DatabaseConnectionPostgreSQL();
			List<String> swiftList = new LinkedList<String>();
			String transactionID1 = Long.toString(transactionID);
			try{
				Connection con = dbobj1.getConnection();
			    Statement statement = con.createStatement();
			    ResultSet resultSet = statement.executeQuery("SELECT * from bank");// TranctionTable--> dumm<!-- -->
			    
			    System.out.println("AFTER SELECT FROM BANK");
			    ResultSet resultSet1=statement.executeQuery("SELECT seller_id,buyer_id,net_payable_amount,discount_rate from \"Purchase_Order\" where po_id="+purchaseID);
			    long seller_id = resultSet1.getLong("seller_id");
			    System.out.println("AFTER SELECT getting seller_id");
			    ResultSet resultSet2=statement.executeQuery("SELECT invoice_due_date,\"Payment_Date\" from invoice1 where \"sellerID\"="+seller_id); 
				while(resultSet.next())
				{
					System.out.println("IN loop");
						AccountingManagementService ACCNo=new AccountingManagementService();
						
						
						//bank lending
						String accENo =ACCNo.Generate(resultSet.getString("bank_name"),resultSet.getString("city"),
								"Dr");
						statement.executeUpdate("insert into general_ledger values('"+accENo+"','"+resultSet2.getString(2)+"','"+transactionID1+"','"+payeeAcnt+"',"+purchaseID+",'Dr',"+resultSet1.getString("net_payable_amount")+",'"+resultSet2.getString("invoice_due_date")+"','"+resultSet.getString("swift_id")+"','"+resultSet.getString("discount_rate")+"'");
						System.out.println("AFTER insert into gr DR");
						//accrued income
						String accENo1 =ACCNo.Generate(resultSet.getString("head"),resultSet.getString("branch"),"Ar");
					
						statement.executeQuery("insert into general_ledger values('"+accENo1+"','"+resultSet2.getString(2)+"',null,'"+payeeAcnt+"',"+purchaseID+",'Cr',null,"
								+ "'"+resultSet2.getString("invoice_due_date")+"','"+resultSet.getString("productSwiftId")+"'");
						System.out.println("AFTER insert into gr CR Acr");
						//interest receivable
						accENo1 =ACCNo.Generate(resultSet.getString("head"),resultSet.getString("branch"),"Ir");
						
						System.out.println("AFTER insert into gr DR");
						 statement.executeQuery("insert into general_ledger values('"+accENo1+"','"+resultSet2.getString(2)+"',null,'"+payeeAcnt+"',"+purchaseID+",'Dr',null,"
								+ "'"+resultSet2.getString("invoice_due_date")+"','"+resultSet.getString("productSwiftId")+"'");
						
				}
			    
			}
			
			/*try {
			Connection con = dbobj1.getConnection();
				Statement statement = con.createStatement();  
				System.out.println("In getCOAswiftList - After create statement");
				ResultSet resultSet = statement.executeQuery("SELECT * FROM TranctionTable");// TranctionTable--> dumm<!-- -->		
				 
				while(resultSet.next())
				{
					swiftList.add(resultSet.getString("productSwiftId"));
				}
				GeneralLedger ACCNo = new GeneralLedger();
				String accENo =ACCNo.Generate(resultSet.getString("bank_name"),resultSet.getString("branch"),resultSet.getString("Dr_Cr"), resultSet.getString("productSwiftId"));
				ResultSet resultSet1 = statement.executeQuery("insert into general_ledger values('"+accENo+"',null,'"+resultSet.getString("tranction_date")+"','"+resultSet.getString("tranction_id")+"',"
						+ "'"+resultSet.getString("Customer_acc_no")+"','"+resultSet.getString("invoice_No")+"','"+resultSet.getString("DR_CR")+"','"+resultSet.getDouble("Amount")+"','"+resultSet.getString("due_Date")+"','"+resultSet.getString("productSwiftId")+"',");
				if(resultSet.getString("Dr_Cr").equals("Dr")){
				String accENo1 =ACCNo.Generate(resultSet.getString("head"),resultSet.getString("branch"),"Ar", resultSet.getString("productSwiftId"));
				resultSet1 = statement.executeQuery("insert into general_ledger values('"+accENo1+"',null,null,null,'"+resultSet.getString("Customer_acc_no")+"','"+resultSet.getString("invoice_No")+"','CR',null,'"+resultSet.getString("due_Date")+"','"+resultSet.getString("productSwiftId")+"'");
			    }
				con.close();
			}*/
			catch(Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
			
		}

	public String Generate(String head,String branch,String d_c_a){
		String cr ="Cr";
		String dr ="Dr";
		String ar ="Ar";
		String ir ="Ir";

		
		AccountingManagementService No = new AccountingManagementService();
		String AccountEntryNo ="";
		
		if(d_c_a.equals(dr)){
			String myStringRepOfInt = String.format("%03d", No.initdrNo());
			AccountEntryNo=head+branch+"01"+myStringRepOfInt;
		}
		else if(d_c_a.equals(cr)){
			String myStringRepOfInt = String.format("%03d", No.initcrNo());
			AccountEntryNo=head+branch+"02"+myStringRepOfInt;
		}
		else if(d_c_a.equals(ar)){
			String myStringRepOfInt = String.format("%03d", No.initacrNo());
			AccountEntryNo=head+branch+"03"+myStringRepOfInt;
		}
		else if(d_c_a.equals(ir)){
			String myStringRepOfInt = String.format("%03d", No.initacrNo());
			AccountEntryNo=head+branch+"04"+myStringRepOfInt;
		}
		return AccountEntryNo;
	  }
	
	  public static long initcrNo(){
		    return crNo++;
		}
		public static long initdrNo(){
		    return drNo++;
		}
		public static long initacrNo(){
		    return acrNo++;
	    }
		 public static long initirNo(){
	     	 return iRNo++;
	       }
		 
		 
		 public List<String> BuyerDetails(int buyerId) throws SQLException {

				List<String> buyerDetail = null;
				
				String name = null;
				String country =null;
				String username = null;
				dbobj1 = new DatabaseConnectionPostgreSQL();
				
				buyerDetail = new ArrayList<String>();
				try {
					
					Connection con = dbobj1.getConnection();

					// Step 3 Create the statement object
					Statement stmt = con.createStatement();

					// Step 4 execute query
					ResultSet rs = stmt.executeQuery("select name from \"Buyer\" where buyer_id ="+buyerId); 
					while (rs.next()) {
					name = rs.getString("name");
						buyerDetail.add(name);
					}
					
					rs = stmt.executeQuery("select \"Username\" from \"Customer_user\" where \"Name\" ='"+ name+"'"); 
					while (rs.next()) {
						username = rs.getString("Username");
					//	buyerDetail.add(country);
					}
					
					rs = stmt.executeQuery("select \"Postal_Location\" from \"Customer_Additional_Details\" where \"Username\" = '"+ username+"'"); 
					while (rs.next()) {
						country = rs.getString("Postal_Location");
						buyerDetail.add(country);
					}
					
					
					// Step 5 close the connection
					

				} catch (Exception e) {
					System.out.println("Exception - get rectangle" + e.getMessage());
				}
				
				finally {

					try {
						dbobj1.closeConnection();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return buyerDetail;
			}
}