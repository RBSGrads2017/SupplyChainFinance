package com.rbs.scm.teamh_mis.model;

public class Proposal_Sellers_Bid {
	private int proposal_id; 
	private int seller_id;
	private int cost_avail;
	private int cost_avail_cust;
	private char seller_status;
	private int score;
	private char buyer_bid_status;
	
	public char getBuyer_bid_status() {
		return buyer_bid_status;
	}
	public void setBuyer_bid_status(char buyer_bid_status) {
		this.buyer_bid_status = buyer_bid_status;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getProposal_id() {
		return proposal_id;
	}
	public void setProposal_id(int proposal_id) {
		this.proposal_id = proposal_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getCost_avail() {
		return cost_avail;
	}
	public void setCost_avail(int cost_avail) {
		this.cost_avail = cost_avail;
	}
	public int getCost_avail_cust() {
		return cost_avail_cust;
	}
	public void setCost_avail_cust(int cost_avail_cust) {
		this.cost_avail_cust = cost_avail_cust;
	}
	public char getSeller_status() {
		return seller_status;
	}
	public void setSeller_status(char seller_status) {
		this.seller_status = seller_status;
	}
	
	
}