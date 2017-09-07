package com.rbs.scm.teamb_contract1.POJO.Table;

public class ResponseTable {
	
	Integer proposal_id;
	String product_name;
	String feature_name;
	Integer seller_id;
	char response_status;
	
	public Integer getProposal_id() {
		return proposal_id;
	}
	public void setProposal_id(Integer proposal_id) {
		this.proposal_id = proposal_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String string) {
		this.product_name = string;
	}
	public String getFeature_name() {
		return feature_name;
	}
	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}
	public Integer getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	public char getResponse_status() {
		return response_status;
	}
	public void setResponse_status(char response_status) {
		this.response_status = response_status;
	}
	
}
