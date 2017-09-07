package com.rbs.scm.teamc_contract2.data;

public class ContractProductFeatureLog {

	@Override
	public String toString() {
		return "ContractProductFeatureLog [contractId=" + contractId + ", productId=" + productId + ", featureId="
				+ featureId + ", version=" + version + "]";
	}
	int contractId;
	int productId;
	int featureId;
	int version;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contract_id) {
		this.contractId = contract_id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int product_id) {
		this.productId = product_id;
	}
	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int feature_id) {
		this.featureId = feature_id;
	}
	
	
	public ContractProductFeatureLog(int contract_id, int product_id, int feature_id, int version) {
		super();
		this.contractId = contract_id;
		this.productId = product_id;
		this.featureId = feature_id;
		this.version = version;
	}
	public ContractProductFeatureLog() {
//		super();
	}
	
	
}
