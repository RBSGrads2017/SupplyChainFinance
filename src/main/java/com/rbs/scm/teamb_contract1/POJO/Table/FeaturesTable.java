package com.rbs.scm.teamb_contract1.POJO.Table;

public class FeaturesTable {

	Integer featuresId;
	Integer productsId;
	Integer proposalId;
	char priorityOrder;
	String attachment;
	public Integer getFeaturesId() {
		return featuresId;
	}
	public void setFeaturesId(Integer featuresId) {
		this.featuresId = featuresId;
	}
	public Integer getProductsId() {
		return productsId;
	}
	public void setProductsId(Integer productsId) {
		this.productsId = productsId;
	}

	public Integer getProposalId() {
		return proposalId;
	}
	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}
	public char getPriorityOrder() {
		return priorityOrder;
	}
	public void setPriorityOrder(char priorityOrder) {
		this.priorityOrder = priorityOrder;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	
}
