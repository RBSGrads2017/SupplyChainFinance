package com.rbs.scm.teame_funding.model.pojos;

import java.util.Date;
import java.util.Random;


public class Invoice {
	String InvoiceDate;
	String InvoiceDueDate;
	String PaymentDueDate;
	Double InvoiceAmount;
	double DiscountRate;
	String Status;
	String PaymentStatus;
	String FinancialInstitution;
	int FinancingId;
	int InvoiceID;
	public int getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		InvoiceID = invoiceID;
	}
	Buyer buyer;
	Seller seller;
	
	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Invoice(){
		Random rand = new Random();
		FinancingId=rand.nextInt();
		FinancialInstitution="RBS";
		Status= "Pending";
		PaymentStatus="Pending";
		DiscountRate=8;
	}
	
	public String getInvoiceDate() {
		return InvoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}
	public String getInvoiceDueDate() {
		return InvoiceDueDate;
	}
	public void setInvoiceDueDate(String invoiceDueDate) {
		InvoiceDueDate = invoiceDueDate;
	}
	public String getPaymentDueDate() {
		return PaymentDueDate;
	}
	public void setPaymentDueDate(String paymentDueDate) {
		PaymentDueDate = paymentDueDate;
	}
	public Double getInvoiceAmount() {
		return InvoiceAmount;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		InvoiceAmount = invoiceAmount;
	}
	public double getDiscountRate() {
		return DiscountRate;
	}
	public void setDiscountRate(double discountRate) {
		DiscountRate = discountRate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPaymentStatus() {
		return PaymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
	}
	public String getFinancialInstitution() {
		return FinancialInstitution;
	}
	public void setFinancialInstitution(String financialInstitution) {
		FinancialInstitution = financialInstitution;
	}
	public int getFinancingId() {
		return FinancingId;
	}
	public void setFinancingId(int financingId) {
		FinancingId = financingId;
	}
}
