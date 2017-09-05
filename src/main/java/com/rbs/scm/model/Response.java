package com.rbs.scm.model;

public class Response {
	private int f_id;
	private int p_id; /*product id only but it is given as p_id in 
						database response table so gave the same*/
	private int proposal_id; 
	private char response_status;
	private int seller_id;
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getProposal_id() {
		return proposal_id;
	}
	public void setProposal_id(int proposal_id) {
		this.proposal_id = proposal_id;
	}
	public char getResponse_status() {
		return response_status;
	}
	public void setResponse_status(char response_status) {
		this.response_status = response_status;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	
	
	
	}