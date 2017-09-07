package com.rbs.scm.teame_funding.model.pojos;

import java.util.List;

public class PurchaseOrder {
	List<Invoice> li;
	List<Integer> invoiceIDs;
	String FinancialInstitution;
    String FinancingId;
    Double InvestmentAmount;
    Double DiscountRate;
    Double NetPayable;
    Integer Fees;
    String buyer_id;
    String seller_id;
    String buyer_name;
    String seller_name;
	boolean isSellerApproved;
	boolean isBankApproved;
	
	String POID;
	   public String getbuyer_name() {
	        return buyer_name;
	    }
	    public void setbuyer_name(String name) {
	        buyer_name = name;
	    }
	    public String getseller_name() {
	        return seller_name;
	    }
	    public void setseller_name(String name) {
	        seller_name= name;
	    }
    public String getbuyer_id() {
        return buyer_id;
    }
    public void setbuyer_id(String id) {
        buyer_id = id;
    }
    public String getseller_id() {
        return seller_id;
    }
    public void setseller_id(String id) {
        seller_id= id;
    }
	
	public String getPOID() {
		return POID;
	}

	public List<Integer> getInvoiceIDs() {
		return invoiceIDs;
	}

	public void setInvoiceIDs(List<Integer> invoiceIDs) {
		this.invoiceIDs = invoiceIDs;
	}

	public void setPOID(String pOID) {
		POID = pOID;
	}

	public boolean isSellerApproved() {
		return isSellerApproved;
	}

	public void setSellerApproved(boolean isSellerApproved) {
		this.isSellerApproved = isSellerApproved;
	}

	public boolean isBankApproved() {
		return isBankApproved;
	}

	public void setBankApproved(boolean isBankApproved) {
		this.isBankApproved = isBankApproved;
	}
	
	public PurchaseOrder()
	{
		
	}

	public PurchaseOrder(List<Integer> invoiceIDs, String financialInstitution, String financingId,
			Double investmentAmount, Double discountRate, Double netPayable, Integer fees, String buyer_id, String seller_id,
			boolean isSellerApproved, boolean isBankApproved, String pOID) {
		super();
		this.invoiceIDs = invoiceIDs;
		FinancialInstitution = financialInstitution;
		FinancingId = financingId;
		InvestmentAmount = investmentAmount;
		DiscountRate = discountRate;
		NetPayable = netPayable;
		Fees = fees;
		this.buyer_id = buyer_id;
		this.seller_id =seller_id;
		this.isSellerApproved = isSellerApproved;
		this.isBankApproved = isBankApproved;
		POID = pOID;
	}
	 public String getFinancialInstitution() {
	        return FinancialInstitution;
	    }
	    public void setFinancialInstitution(String financialInstitution) {
	        FinancialInstitution = financialInstitution;
	    }
	    public String getFinancingId() {
	        return FinancingId;
	    }
	    public void setFinancingId(String financingId) {
	        FinancingId = financingId;
	    }
	    public Double getInvestmentAmount() {
	        return InvestmentAmount;
	    }
	    public void setInvestmentAmount(Double investmentAmount) {
	        InvestmentAmount = investmentAmount;
	    }
	    public Double getDiscountRate() {
	        return DiscountRate;
	    }
	    public void setDiscountRate(Double discountRate) {
	        DiscountRate = discountRate;
	    }
	    public Double getNetPayable() {
	        return NetPayable;
	    }
	    public void setNetPayable(Double netPayable) {
	        NetPayable = netPayable;
	    }
	    public Integer getFees() {
	        return Fees;
	    }
	    public void setFees(Integer fees) {
	        Fees = fees;
	    }
	@Override
	public String toString() {
		return "PurchaseOrder [invoiceIDs=" + invoiceIDs + ", InvestmentAmount=" + InvestmentAmount + ", DiscountRate="
				+ DiscountRate + ", NetPayable=" + NetPayable + ", buyer=" + buyer_id + ", seller=" + seller_id
				+ ", isSellerApproved=" + isSellerApproved + ", isBankApproved=" + isBankApproved + ", POID=" + POID
				+ "]";
	}
	
	
}