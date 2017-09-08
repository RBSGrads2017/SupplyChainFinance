/*package com.rbs.scm.teame_funding.dao;

import com.rbs.scm.teame_funding.controller.db.FundingDAOimpl;

public class Business {
	private int buyer_id;
	private int seller_id;
	private boolean approved;
	private boolean reviewed;
	private double discount_rate;
	private double libor_rate;
	
	private String currency;
	private int duration; //in months
	private String time_of_issue;
	FundingDAOimpl dao = new FundingDAOimpl();


	public boolean getApproved() {
		return approved;
	}
	public void setApproved() {
		boolean flag1=false;
		flag1= dao.readPurchaseOrder(buyer_id, seller_id);
		double credit = dao.readBuyer(buyer_id);
		boolean flag2=true;
		if (credit<600) flag2=false;
		this.approved= flag1&flag2;
	}
	public double getDiscount_rate() {
		return discount_rate;
	}
	public double getLibor_rate() {
		return libor_rate;	}
	public void setLibor_rate() {
		libor_rate = dao.readLIBOR(currency, duration, time_of_issue);
	}
	public void setDiscount_rate(String currency, String dates) {
		double credit = dao.readBuyer(buyer_id);
		if (credit>750) {discount_rate= this.getLibor_rate() + 0.5; }
		if ((credit>700)&&(credit<=750)){ discount_rate=this.getLibor_rate() +1; }
		if ((credit>650)&&(credit<=700)){ discount_rate=this.getLibor_rate() +2; }
		if (credit<=650){ discount_rate=this.getLibor_rate() +2; }
	}
	public boolean getReviewed() {
		return reviewed;
	}
	public void setReviewed(boolean reviewed) {
		this.reviewed = dao.readPurchaseOrder(buyer_id, seller_id);
	}

}
*/