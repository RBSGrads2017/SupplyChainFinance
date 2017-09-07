package com.rbs.scm.teamc_contract2.data;

public class PaymentTerm {
	
	int id;
	String desc;
	public PaymentTerm(int id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}
	public PaymentTerm() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
