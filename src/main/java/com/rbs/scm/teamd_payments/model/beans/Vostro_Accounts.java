package com.rbs.scm.teamd_payments.model.beans;

public class Vostro_Accounts {
private String swift_id;
private String other_bank_swift_id;
private String account_no;
public Vostro_Accounts(String swift_id, String other_bank_swift_id, String account_no) {
	super();
	this.swift_id = swift_id;
	this.other_bank_swift_id = other_bank_swift_id;
	this.account_no = account_no;
}
public String getSwift_id() {
	return swift_id;
}
public void setSwift_id(String swift_id) {
	this.swift_id = swift_id;
}
public String getOther_bank_swift_id() {
	return other_bank_swift_id;
}
public void setOther_bank_swift_id(String other_bank_swift_id) {
	this.other_bank_swift_id = other_bank_swift_id;
}
public String getAccount_no() {
	return account_no;
}
public void setAccount_no(String account_no) {
	this.account_no = account_no;
}

}
/*
create table vostro_accounts(swift_id varchar(12),
bank_name varchar(40),
account_number varchar(40));
*/