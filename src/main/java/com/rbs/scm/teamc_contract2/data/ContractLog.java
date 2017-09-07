package com.rbs.scm.teamc_contract2.data;

public class ContractLog {
	
	int contract_id;
	String seller_id;
	String buyer_id;
	int status_id;
	int delivery_term_id;
	int payment_term_id;
	int proposal_id;
	float price;
	String period_of_delivery;
	String invoice_date;
	int version;
	public int getContract_id() {
		return contract_id;
	}
	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getDelivery_term_id() {
		return delivery_term_id;
	}
	public void setDelivery_term_id(int delivery_term_id) {
		this.delivery_term_id = delivery_term_id;
	}
	public int getPayment_term_id() {
		return payment_term_id;
	}
	public void setPayment_term_id(int payment_term_id) {
		this.payment_term_id = payment_term_id;
	}
	public int getProposal_id() {
		return proposal_id;
	}
	public void setProposal_id(int proposal_id) {
		this.proposal_id = proposal_id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPeriod_of_delivery() {
		return period_of_delivery;
	}
	public void setPeriod_of_delivery(String period_of_delivery) {
		this.period_of_delivery = period_of_delivery;
	}
	public String getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public ContractLog(int contract_id, String seller_id, String buyer_id, int status_id, int delivery_term_id,
			int payment_term_id, int proposal_id, float price, String period_of_delivery, String invoice_date,
			int version) {
		super();
		this.contract_id = contract_id;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
		this.status_id = status_id;
		this.delivery_term_id = delivery_term_id;
		this.payment_term_id = payment_term_id;
		this.proposal_id = proposal_id;
		this.price = price;
		this.period_of_delivery = period_of_delivery;
		this.invoice_date = invoice_date;
		this.version = version;
	}
	public ContractLog() {
	
	}
	
	
}
