package com.rbs.scm.teame_funding.POJOTables;

import java.sql.Time;

public class Libortable {
	
	private String currency;
	private Time time_of_issue;
	private Integer duration;
	private double rate;
	
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Time getTime_of_issue() {
		return time_of_issue;
	}
	
	public void setTime_of_issue(Time time_of_issue) {
		this.time_of_issue = time_of_issue;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	

}
