package com.rbs.scm.teamd_payments.model.beans;
public class FXData {
private String currency;
private double rate;
private String country;
public FXData(String currency, double rate,String country) {
	super();
	this.currency = currency;
	this.rate = rate;
	this.country=country;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public FXData() {
	super();
	this.currency = "";
	this.rate = 0;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public double getRate() {
	return rate;
}
public void setRate(double rate) {
	this.rate = rate;
}

}
/*
 create table fx_data(currency varchar(5),
 rate numberic(10,2),
 country varchar(40)); 
 */
