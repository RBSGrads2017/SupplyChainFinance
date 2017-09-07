package com.rbs.scm.teamd_payments.model.beans;

import java.sql.Date;

public class Bank_to_Customer extends Transaction{
private String payer_id;
private String payee_id;

public Bank_to_Customer(int transaction_id, String message_code, String currency_code, double amount,
		Date transaction_date, String aml_status, String status, String comments,
		String payer_account, String payee_Account, String payer_id, String payee_id) {
	super(transaction_id, message_code, currency_code, amount, transaction_date, aml_status, status, comments,
			payer_account, payee_Account);
	this.payer_id = payer_id;
	this.payee_id = payee_id;
}
public String getPayer_id() {
	return payer_id;
}
public void setPayer_id(String payer_id) {
	this.payer_id = payer_id;
}
public String getPayee_id() {
	return payee_id;
}
public void setPayee_id(String payee_id) {
	this.payee_id = payee_id;
}

}