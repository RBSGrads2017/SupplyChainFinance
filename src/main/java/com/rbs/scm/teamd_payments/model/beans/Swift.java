package com.rbs.scm.teamd_payments.model.beans;


public class Swift {
	private String message_code;
private int transaction_id;
private String sender;
private String receiver;
private String message_text;
private String bank_operation_code;
private String sender_ref;
private String interbank_settled_amount;
private String instructed_amount;
private String ordering_customer;
private String beneficiary_customer;
private String sender_correspondent;
private String receiver_correspondent;
private String remit_info;
private String details_of_charges;
public Swift(String message_code,int transaction_id, String sender, String receiver, String message_text, String bank_operation_code,
		String sender_ref, String interbank_settled_amount, String instructed_amount, String ordering_customer,
		String beneficiary_customer, String sender_correspondent, String receiver_correspondent, String remit_info,
		String details_of_charges) {
	super();
	this.message_code=message_code;
	this.transaction_id = transaction_id;
	this.sender = sender;
	this.receiver = receiver;
	this.message_text = message_text;
	this.bank_operation_code = bank_operation_code;
	this.sender_ref = sender_ref;
	this.interbank_settled_amount = interbank_settled_amount;
	this.instructed_amount = instructed_amount;
	this.ordering_customer = ordering_customer;
	this.beneficiary_customer = beneficiary_customer;
	this.sender_correspondent = sender_correspondent;
	this.receiver_correspondent = receiver_correspondent;
	this.remit_info = remit_info;
	this.details_of_charges = details_of_charges;
}

public String getMessage_code() {
	return message_code;
}

public void setMessage_code(String message_code) {
	this.message_code = message_code;
}

public int getTransaction_id() {
	return transaction_id;
}
public void setTransaction_id(int transaction_id) {
	this.transaction_id = transaction_id;
}
public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getReceiver() {
	return receiver;
}
public void setReceiver(String receiver) {
	this.receiver = receiver;
}
public String getMessage_text() {
	return message_text;
}
public void setMessage_text(String message_text) {
	this.message_text = message_text;
}
public String getBank_operation_code() {
	return bank_operation_code;
}
public void setBank_operation_code(String bank_operation_code) {
	this.bank_operation_code = bank_operation_code;
}
public String getSender_ref() {
	return sender_ref;
}
public void setSender_ref(String sender_ref) {
	this.sender_ref = sender_ref;
}
public String getInterbank_settled_amount() {
	return interbank_settled_amount;
}
public void setInterbank_settled_amount(String interbank_settled_amount) {
	this.interbank_settled_amount = interbank_settled_amount;
}
public String getInstructed_amount() {
	return instructed_amount;
}
public void setInstructed_amount(String instructed_amount) {
	this.instructed_amount = instructed_amount;
}
public String getOrdering_customer() {
	return ordering_customer;
}
public void setOrdering_customer(String ordering_customer) {
	this.ordering_customer = ordering_customer;
}
public String getBeneficiary_customer() {
	return beneficiary_customer;
}
public void setBeneficiary_customer(String beneficiary_customer) {
	this.beneficiary_customer = beneficiary_customer;
}
public String getSender_correspondent() {
	return sender_correspondent;
}
public void setSender_correspondent(String sender_correspondent) {
	this.sender_correspondent = sender_correspondent;
}
public String getReceiver_correspondent() {
	return receiver_correspondent;
}
public void setReceiver_correspondent(String receiver_correspondent) {
	this.receiver_correspondent = receiver_correspondent;
}
public String getRemit_info() {
	return remit_info;
}
public void setRemit_info(String remit_info) {
	this.remit_info = remit_info;
}
public String getDetails_of_charges() {
	return details_of_charges;
}
public void setDetails_of_charges(String details_of_charges) {
	this.details_of_charges = details_of_charges;
}

}

/*create table swift(message_code varchar(10),
transaction_id numeric(10) unique,
sender varchar(30) not null,
receiver varchar(30) not null,
message_text varchar(40),
bank_operation_code varchar(20),
sender_ref varchar(30) not null,
interbank_settled_amount varchar(20),
instructed_amount varchar(20),
ordering_customer varchar(40),
beneficiary_customer varchar(40),
sender_correspndent varchar(40),
receiver_correspondent varchar(40),
remit_info varchar(20),
details_of_charges varchar(3));*/
