package com.rbs.scm.teame_funding.model.pojos;

public class Libor {
    private String currency;
    private String duration;
    private String rate;
   
    public String getCurrency() {
        return currency;
    }
   
    public void setCurrency(String currency) {
        this.currency = currency;
    }
   
    public String getRate() {
        return rate;
    }
   
    public void setRate(String string) {
        this.rate = string;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}