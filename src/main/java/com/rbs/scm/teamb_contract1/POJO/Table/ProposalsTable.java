package com.rbs.scm.teamb_contract1.POJO.Table;

public class ProposalsTable {

	Integer proposalId;
	Integer buyerId;
	Integer bidSellerId;
	String description;
	char buyerStatus;
	char contractStatus;
	Integer dTermsId;
	Integer pTermsId;
	public Integer getProposalId() {
		return proposalId;
	}
	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getBidSellerId() {
		return bidSellerId;
	}
	public void setBidSellerId(Integer bidSellerId) {
		this.bidSellerId = bidSellerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getBuyerStatus() {
		return buyerStatus;
	}
	public void setBuyerStatus(char buyerStatus) {
		this.buyerStatus = buyerStatus;
	}
	public char getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(char contractStatus) {
		this.contractStatus = contractStatus;
	}
	public Integer getdTermsId() {
		return dTermsId;
	}
	public void setdTermsId(Integer dTermsId) {
		this.dTermsId = dTermsId;
	}
	public Integer getpTermsId() {
		return pTermsId;
	}
	public void setpTermsId(Integer pTermsId) {
		this.pTermsId = pTermsId;
	}
	
	
}
