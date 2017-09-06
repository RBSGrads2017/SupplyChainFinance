package com.rbs.scm.teame_funding.model.pojos;

import java.util.List;

public class PurchaseOrder {
	List<Invoice> li;
	List<Integer> invoiceIDs;
	String FinancialInstitution;
	String FinancingId;
	String InvestmentAmount;
	String DiscountRate;
	String NetPayable;
	String Fees;
	
	Buyer buyer;
	Seller seller;
	
	boolean isSellerApproved;
	boolean isBankApproved;
	
	String POID;
	
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

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	

	public PurchaseOrder(List<Integer> invoiceIDs, String financialInstitution, String financingId,
			String investmentAmount, String discountRate, String netPayable, String fees, Buyer buyer, Seller seller,
			boolean isSellerApproved, boolean isBankApproved, String pOID) {
		super();
		this.invoiceIDs = invoiceIDs;
		FinancialInstitution = financialInstitution;
		FinancingId = financingId;
		InvestmentAmount = investmentAmount;
		DiscountRate = discountRate;
		NetPayable = netPayable;
		Fees = fees;
		this.buyer = buyer;
		this.seller = seller;
		this.isSellerApproved = isSellerApproved;
		this.isBankApproved = isBankApproved;
		POID = pOID;
	}

	/*public PurchaseOrder(List<Invoice> li, String InvestmentAmount, 
			String DiscountRate, String NetPayable, String Fees ){
		FinancialInstitution="RBS";
		FinancingId="0"; // ---------------------- generate new id.
		this.InvestmentAmount= InvestmentAmount;
		this.DiscountRate = DiscountRate;
		this.NetPayable= NetPayable;
		this.Fees= Fees;
	}*/
	
	public PurchaseOrder() {
		// TODO Auto-generated constructor stub
	}

	public List<Invoice> getLi() {
		return li;
	}
	public void setLi(List<Invoice> li) {
		this.li = li;
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
	public String getInvestmentAmount() {
		return InvestmentAmount;
	}
	public void setInvestmentAmount(String investmentAmount) {
		InvestmentAmount = investmentAmount;
	}
	public String getDiscountRate() {
		return DiscountRate;
	}
	public void setDiscountRate(String discountRate) {
		DiscountRate = discountRate;
	}
	public String getNetPayable() {
		return NetPayable;
	}
	public void setNetPayable(String netPayable) {
		NetPayable = netPayable;
	}
	public String getFees() {
		return Fees;
	}
	public void setFees(String fees) {
		Fees = fees;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [invoiceIDs=" + invoiceIDs + ", InvestmentAmount=" + InvestmentAmount + ", DiscountRate="
				+ DiscountRate + ", NetPayable=" + NetPayable + ", buyer=" + buyer.getBuyer_id() + ", seller=" + seller.getSeller_id()
				+ ", isSellerApproved=" + isSellerApproved + ", isBankApproved=" + isBankApproved + ", POID=" + POID
				+ "]";
	}
	
	
}