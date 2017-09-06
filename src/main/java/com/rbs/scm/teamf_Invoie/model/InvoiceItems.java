package com.rbs.scm.teamf_Invoie.model;

public class InvoiceItems {
	private double itemNo;
	public double getItemNo() {
		return itemNo;
	}
	public void setItemNo(double itemNo) {
		this.itemNo = itemNo;
	}
	public double getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(double invoiceID) {
		this.invoiceID = invoiceID;
	}
	public double getProductID() {
		return ProductID;
	}
	public void setProductID(double productID) {
		ProductID = productID;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	private double  invoiceID;
	private double  ProductID;
	private double quantity ;
	private double grossAmount;
	private float tax;
	private double netAmount;
	private double unitprice;
	
}

