
package com.rbs.scm.teamb_contract1.POJO.JsonParse.createProposal;

import java.util.List;

public class Product {

    private Integer id;
    private Integer quantity;
    private List<Feature> features = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}
