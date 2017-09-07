package com.rbs.scm.teamb_contract1.POJO.Table;

public class ProposalSellersBid {

	private Integer proposalId;
	private Integer sellerId;
	private Integer costAvail;
	private Integer costAvailCust;
	private char sellerStatus;
	private Integer score;
	private char buyerBidStatus;
	public char getBuyerBidStatus() {
		return buyerBidStatus;
	}
	public void setBuyerBidStatus(char buyerBidStatus) {
		this.buyerBidStatus = buyerBidStatus;
	}
	public Integer getProposalId() {
		return proposalId;
	}
	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getCostAvail() {
		return costAvail;
	}
	public void setCostAvail(Integer costAvail) {
		this.costAvail = costAvail;
	}
	public Integer getCostAvailCust() {
		return costAvailCust;
	}
	public void setCostAvailCust(Integer costAvailCust) {
		this.costAvailCust = costAvailCust;
	}
	public char getSellerStatus() {
		return sellerStatus;
	}
	public void setSellerStatus(char sellerStatus) {
		this.sellerStatus = sellerStatus;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	
}
