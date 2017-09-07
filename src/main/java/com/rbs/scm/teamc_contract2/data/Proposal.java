package com.rbs.scm.teamc_contract2.data;

import java.util.ArrayList;

public class Proposal {
	private int id;
	private int bidSellerId;
	private String description;
	private String buyerStatus;
	private String contractStatus;
	private int deliveryTermId;
	private int paymentTermId;
	ArrayList<Product> products;
	
	public Proposal() {
		
	}
	
	public Proposal(int id, int bidSellerId, String description, String buyerStatus, String contractStatus,
			int deliveryTermId, int paymentTermId, ArrayList<Product> products) {
		super();
		this.id = id;
		this.bidSellerId = bidSellerId;
		this.description = description;
		this.buyerStatus = buyerStatus;
		this.contractStatus = contractStatus;
		this.deliveryTermId = deliveryTermId;
		this.paymentTermId = paymentTermId;
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBidSellerId() {
		return bidSellerId;
	}
	public void setBidSellerId(int bidSellerId) {
		this.bidSellerId = bidSellerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBuyerStatus() {
		return buyerStatus;
	}
	public void setBuyerStatus(String buyerStatus) {
		this.buyerStatus = buyerStatus;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public int getDeliveryTermId() {
		return deliveryTermId;
	}
	public void setDeliveryTermId(int deliveryTermId) {
		this.deliveryTermId = deliveryTermId;
	}
	public int getPaymentTermId() {
		return paymentTermId;
	}
	public void setPaymentTermId(int paymentTermId) {
		this.paymentTermId = paymentTermId;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}
