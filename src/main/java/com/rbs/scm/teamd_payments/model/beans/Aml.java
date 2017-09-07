package com.rbs.scm.teamd_payments.model.beans;

public class Aml {
private String name;
private String country;
public Aml(String name, String country) {
	super();
	this.name = name;
	this.country = country;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}

}
/*
create table sanc_countries(id numeric(10),
name varchar(40));

create table sanc_users(id numeric(10),
name varchar(40));
 */