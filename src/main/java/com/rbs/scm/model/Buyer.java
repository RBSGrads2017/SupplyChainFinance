package com.rbs.scm.model;

public class Buyer {
 private int buyer_id;
 private String name;
 private String email;
 private int credit_rating;
 public int getBuyer_id() {
	return buyer_id;
}
public void setBuyer_id(int buyer_id) {
	this.buyer_id = buyer_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getCredit_rating() {
	return credit_rating;
}
public void setCredit_rating(int credit_rating) {
	this.credit_rating = credit_rating;
}

}
