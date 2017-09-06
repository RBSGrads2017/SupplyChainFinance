package com.rbs.scm.teame_funding.model.pojos;

public class Buyer {
	
	private String name;
	private Integer buyer_id;
	private String email;
	private Integer credit_rating;
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getBuyer_id() {
		return buyer_id;
	}
	
	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getCredit_rating() {
		return credit_rating;
	}
	
	public void setCredit_rating(Integer credit_rating) {
		this.credit_rating = credit_rating;
	}
	
	
}