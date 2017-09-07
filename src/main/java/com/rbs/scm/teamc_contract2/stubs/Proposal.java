package com.rbs.scm.teamc_contract2.stubs;

import java.util.List;

public class Proposal {
	private String proposal;
	private String description;
	private String sellerDetails;
	private String address;
	List<Feature> features;
	private int deliveryTerms;
	private int paymentTerms;
	public Proposal() {
		
	}
	public Proposal(String proposal, String description, String sellerDetails, String address, List<Feature> features,
			int deliveryTerms, int paymentTerms) {
		super();
		this.proposal = proposal;
		this.description = description;
		this.sellerDetails = sellerDetails;
		this.address = address;
		this.features = features;
		this.deliveryTerms = deliveryTerms;
		this.paymentTerms = paymentTerms;
	}
	public int getDeliveryTerms() {
		return deliveryTerms;
	}
	public void setDeliveryTerms(int deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}
	public int getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(int paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getProposal() {
		return proposal;
	}
	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSellerDetails() {
		return sellerDetails;
	}
	public void setSellerDetails(String sellerDetails) {
		this.sellerDetails = sellerDetails;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
}
