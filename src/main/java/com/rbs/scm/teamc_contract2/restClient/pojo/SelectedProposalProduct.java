package com.rbs.scm.teamc_contract2.restClient.pojo;

import java.util.List;

public class SelectedProposalProduct {
	private Integer quantity;
    private String nameOfProduct;
    private Integer id;
    private List<SelectedProposalProductFeature> features = null;
    
    public SelectedProposalProduct() {
	}

	public SelectedProposalProduct(Integer quantity, String nameOfProduct, Integer id,
			List<SelectedProposalProductFeature> features) {
		this.quantity = quantity;
		this.nameOfProduct = nameOfProduct;
		this.id = id;
		this.features = features;
	}

	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SelectedProposalProductFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<SelectedProposalProductFeature> features) {
        this.features = features;
    }
}
