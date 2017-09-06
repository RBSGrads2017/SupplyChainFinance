package com.rbs.scm.teame_funding.model.pojos;

public class Libor {
	private String currency;
	private String duration;
	private String rate;
	
	public String getLibor() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getRate() {
		return rate;
	}
	
	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
