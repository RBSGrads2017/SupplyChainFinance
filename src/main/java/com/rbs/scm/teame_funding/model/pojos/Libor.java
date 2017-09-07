package com.rbs.scm.teame_funding.model.pojos;

public class Libor {
    private String currency;
    private String duration;
    private Double rate;
   
    public String getCurrency() {
        return currency;
    }
   
    public void setCurrency(String currency) {
        this.currency = currency;
    }
   
    public Double getRate() {
        return rate;
    }
   
    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}