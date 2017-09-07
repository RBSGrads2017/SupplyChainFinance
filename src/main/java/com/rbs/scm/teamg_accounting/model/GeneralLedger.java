package com.rbs.scm.teamg_accounting.model;

import java.time.LocalDate;
import java.util.Date;

public class GeneralLedger {
	
private String accountEntryNo;
private Date currentDate;
private String transactionNo;
private String customerAccountNo;
private String swiftID;
private String invoiceNo;
private String drOrCr;
private double amount;
private Date paymentDate;
private Date dueDate;

		public String getAccountEntryNo() {
			return accountEntryNo;
		}
		public void setAccountEntryNo(String accountEntryNo) {
			this.accountEntryNo = accountEntryNo;
		}
		public Date getCurrentDate() {
			return currentDate;
		}
		public void setCurrentDate(Date currentDate) {
			LocalDate localDate = LocalDate.now();
			Date date = java.sql.Date.valueOf(localDate);
			System.out.println(date);
			this.currentDate = date;
		}
		public String getTransactionNo() {
			return transactionNo;
		}
		public void setTransactionNo(String transactionNo) {
			this.transactionNo = transactionNo;
		}
		public String getCustomerAccountNo() {
			return customerAccountNo;
		}
		public void setCustomerAccountNo(String customerAccountNo) {
			this.customerAccountNo = customerAccountNo;
		}
		public String getSwiftID() {
			return swiftID;
		}
		public void setSwiftID(String swiftID) {
			this.swiftID = swiftID;
		}
		public String getInvoiceNo() {
			return invoiceNo;
		}
		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public String getDrOrCr() {
			return drOrCr;
		}
		public void setDrOrCr(String drOrCr) {
			this.drOrCr = drOrCr;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public Date getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(Date paymentDate) {
			this.paymentDate = paymentDate;
		}
		public Date getDueDate() {
			return dueDate;
		}
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}
	}




