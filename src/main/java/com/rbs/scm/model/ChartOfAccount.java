package com.rbs.scm.model;

public class ChartOfAccount {
	
	private String head;
	private String legalEntity;
	private String country;
	private String branch;
	private String product;
	private String currency;
	private int book;
	private String productSwiftID;
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getLegalEntity() {
		return legalEntity;
	}
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public String getProductSwiftID() {
		return productSwiftID;
	}
	public void setProductSwiftID(String productSwiftID) {
		this.productSwiftID = productSwiftID;
	}
}
