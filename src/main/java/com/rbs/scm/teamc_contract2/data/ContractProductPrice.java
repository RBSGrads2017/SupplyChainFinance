package com.rbs.scm.teamc_contract2.data;

public class ContractProductPrice {
	@Override
	public String toString() {
		return "ContractProductPrice [contractId=" + contractId + ", contractVersion=" + contractVersion
				+ ", productId=" + productId + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity
				+ "]";
	}
	int contractId;
	int contractVersion;
	int productId;
	double productPrice;
	int productQuantity;
	public ContractProductPrice() {
		
	}
	public ContractProductPrice(int contractId, int contractVersion, int productId, double productPrice, int productQuantity) {
		this.contractId = contractId;
		this.contractVersion = contractVersion;
		this.productId = productId;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public int getContractVersion() {
		return contractVersion;
	}
	public void setContractVersion(int contractVersion) {
		this.contractVersion = contractVersion;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
}
