package com.rbs.scm.model;

public class ProductInvoice {
	private double productID;
	private String productName; 
	private float unitPrice; 
	private float  tax;
	private String  description;

	
	public double getProductID() {
		return productID;
	}
	public void setProductID(double productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
