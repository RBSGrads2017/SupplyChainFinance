package com.rbs.scm.teamc_contract2.data;

public class MinContract {
	private int contractId;
	private String sellerId;
	private String buyerId;
	private int status;
	private String description;
	public MinContract(int contractId, String sellerId, String buyerId, int status, String description) {
		super();
		this.contractId = contractId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.status = status;
		this.description = description;
	}
	
	public MinContract() {
		
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
