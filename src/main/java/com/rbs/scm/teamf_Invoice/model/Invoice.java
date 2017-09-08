package com.rbs.scm.teamf_Invoice.model;

import java.util.Date;

public class Invoice {
	private double invoiceID;
	private double contractID; 
	private double sellerID; 
	private double buyerID; 
	private double billbookNo; 
	private double senderID; 
	private double receiverID; 
	private int fundingRequestStatus;
	private int approvalStatus; 
	private int draftStatus; 
	private Date invoiceCreatedDate;
	private Date paymentDate; 
	private float invoiceAmount; 
	private Date invoiceDueDate; 
	private int complianceStatus; 
	private int deleteStatus;
	private Date deleteTimestamp;
	private String paymentstatus;
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public double getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(double invoiceID) {
		this.invoiceID = invoiceID;
	}
	public double getContractID() {
		return contractID;
	}
	public void setContractID(double contractID) {
		this.contractID = contractID;
	}
	public double getSellerID() {
		return sellerID;
	}
	public void setSellerID(double sellerID) {
		this.sellerID = sellerID;
	}
	public double getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(double buyerID) {
		this.buyerID = buyerID;
	}
	public double getBillbookNo() {
		return billbookNo;
	}
	public void setBillbookNo(double billbookNo) {
		this.billbookNo = billbookNo;
	}
	public double getSenderID() {
		return senderID;
	}
	public void setSenderID(double senderID) {
		this.senderID = senderID;
	}
	public double getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(double receiverID) {
		this.receiverID = receiverID;
	}
	public int getFundingRequestStatus() {
		return fundingRequestStatus;
	}
	public void setFundingRequestStatus(int fundingRequestStatus) {
		this.fundingRequestStatus = fundingRequestStatus;
	}
	public int getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public int getDraftStatus() {
		return draftStatus;
	}
	public void setDraftStatus(int draftStatus) {
		this.draftStatus = draftStatus;
	}
	public Date getInvoiceCreatedDate() {
		return invoiceCreatedDate;
	}
	public void setInvoiceCreatedDate(Date invoiceCreatedDate) {
		this.invoiceCreatedDate = invoiceCreatedDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public float getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}
	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
	public int getComplianceStatus() {
		return complianceStatus;
	}
	public void setComplianceStatus(int complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	public int getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public Date getDeleteTimestamp() {
		return deleteTimestamp;
	}
	public void setDeleteTimestamp(Date deleteTimestamp) {
		this.deleteTimestamp = deleteTimestamp;
	}
	
}