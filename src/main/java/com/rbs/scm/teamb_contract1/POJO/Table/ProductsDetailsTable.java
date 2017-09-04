package com.rbs.scm.teamb_contract1.POJO.Table;

public class ProductsDetailsTable {
	
	String productName;
	String categoryName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	Integer quantity;
	FeaturesTable ft;
	Integer cost_a;
	Integer cost_a_c;
	
	public Integer getCost_a() {
		return cost_a;
	}
	public void setCost_a(Integer cost_a) {
		this.cost_a = cost_a;
	}
	public Integer getCost_a_c() {
		return cost_a_c;
	}
	public void setCost_a_c(Integer cost_a_c) {
		this.cost_a_c = cost_a_c;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public FeaturesTable getFt() {
		return ft;
	}
	public void setFt(FeaturesTable ft) {
		this.ft = ft;
	}

}
