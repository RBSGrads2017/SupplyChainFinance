package com.rbs.scm.model;

public class Proposal {
	private int proposal_id;
	private int buyer_id;
	private int bid_seller_id;
	private String description;
	private String buyer_status;
	private String contract_status;
	private int d_terms_id;
	private int p_terms_id;
	
	
	Product product;
	
	public int getProposal_id(){
		return proposal_id;
	}
	public void setProposal_id(int proposal_id){
		this.proposal_id = proposal_id;
	}
	public int getBuyer_id(){
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id){
		this.buyer_id = buyer_id;
	}
	public int getBid_seller_id(){
		return bid_seller_id;
	}
	public void setBid_seller_id(int bid_seller_id){
		this.bid_seller_id = bid_seller_id;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getBuyer_status() {
		return buyer_status;
	}
	public void setBuyer_status(String buyer_status) {
		this.buyer_status = buyer_status;
	}
	public String getContract_status() {
		return contract_status;
	}
	public void setContract_status(String contract_status) {
		this.contract_status = contract_status;
	}
	public int getD_terms_id(){
		return d_terms_id;
	}
	public void setD_terms_id(int d_terms_id){
		this.d_terms_id = d_terms_id;
	}
	public int getP_terms_id(){
		return p_terms_id;
	}
	public void setP_terms_id(int p_terms_id){
		this.p_terms_id = p_terms_id;
	}
	
	
}
