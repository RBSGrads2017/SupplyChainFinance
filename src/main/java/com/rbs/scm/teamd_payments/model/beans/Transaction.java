package com.rbs.scm.teamd_payments.model.beans;

import java.sql.Date;

public abstract class Transaction {
private int transaction_id;
private String message_code;
private String currency_code;
private double amount;
private Date transaction_date;
private String aml_status;
private String status;
private String comments;
private String payer_account;
private String payee_account;
public Transaction(int transaction_id, String message_code, String currency_code, double amount, Date transaction_date,
		String aml_status, String status, String comments, String payer_account,String payee_Account) {
	super();
	this.transaction_id = transaction_id;
	this.message_code = message_code;
	this.currency_code = currency_code;
	this.amount = amount;
	this.transaction_date = transaction_date;
	this.aml_status = aml_status;
	this.status = status;
	this.comments = comments;
	this.payer_account=payer_account;
	this.payee_account=payee_account;
}
public String getPayer_account() {
	return payer_account;
}
public void setPayer_account(String payer_account) {
	this.payer_account = payer_account;
}
public String getPayee_account() {
	return payee_account;
}
public void setPayee_account(String payee_account) {
	this.payee_account = payee_account;
}
public int getTransaction_id() {
	return transaction_id;
}
public void setTransaction_id(int transaction_id) {
	this.transaction_id = transaction_id;
}
public String getMessage_code() {
	return message_code;
}
public void setMessage_code(String message_code) {
	this.message_code = message_code;
}
public String getCurrency_code() {
	return currency_code;
}
public void setCurrency_code(String currency_code) {
	this.currency_code = currency_code;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getTransaction_date() {
	return transaction_date;
}
public void setTransaction_date(Date transaction_date) {
	this.transaction_date = transaction_date;
}
public String getAml_status() {
	return aml_status;
}
public void setAml_status(String aml_status) {
	this.aml_status = aml_status;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}

}
/*create table transaction(transaction_id numeric(10) primary key,
message_code varchar(10),
currency_code varchar(3) not null,
amount money not null,
transaction_date date,
aml_status varchar(12),
status varchar(12),
comments varchar(50),
payer_account varchar(40),
payee_Account varchar(40));*/