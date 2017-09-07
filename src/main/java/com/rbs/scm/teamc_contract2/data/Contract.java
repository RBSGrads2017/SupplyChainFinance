package com.rbs.scm.teamc_contract2.data;

import java.util.ArrayList;
import java.util.Date;

public class Contract {

	int contract;
	String sellerId;
	String buyerId;
	int deliveryTermId;
	@Override
	public String toString() {
		return "Contract [contract=" + contract + ", sellerId=" + sellerId + ", buyerId=" + buyerId
				+ ", deliveryTermId=" + deliveryTermId + ", paymentTermId=" + paymentTermId + ", dateofInvoice="
				+ dateofInvoice + ", periodOfDelivery=" + periodOfDelivery + ", product=" + product + ", status="
				+ status + "]";
	}
	int paymentTermId;
	String dateofInvoice;
	String periodOfDelivery;
	ArrayList<Product> product = new ArrayList<>();
	int status; // 0,1,2 IN PROCESS, REJECTED, ACCEPTED
	
	public void addProduct(Product p) {
		product.add(p);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Contract(int contract, String sellerId, String buyerId, int deliveryTermId, int paymentTermId,
			String dateofInvoice, String periodOfDelivery, ArrayList<Product> product, int status) {
		super();
		this.contract = contract;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.deliveryTermId = deliveryTermId;
		this.paymentTermId = paymentTermId;
		this.dateofInvoice = dateofInvoice;
		this.periodOfDelivery = periodOfDelivery;
		this.product = product;
		this.status = status;
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getContract() {
		return contract;
	}
	public void setContract(int contract) {
		this.contract = contract;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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
	public String getDateofInvoice() {
		return dateofInvoice;
	}
	public void setDateofInvoice(String dateofInvoice) {
		this.dateofInvoice = dateofInvoice;
	}
	public String getPeriodOfDelivery() {
		return periodOfDelivery;
	}
	public void setPeriodOfDelivery(String periodOfDelivery) {
		this.periodOfDelivery = periodOfDelivery;
	}
	public ArrayList<Product> getProduct() {
		return product;
	}
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	
	
}
