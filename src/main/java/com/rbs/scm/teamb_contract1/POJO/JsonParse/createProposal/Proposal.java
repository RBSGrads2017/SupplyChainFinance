
package com.rbs.scm.teamb_contract1.POJO.JsonParse.createProposal;

import java.util.List;

public class Proposal {

    private List<Product> products = null;
    private String description;
    private Integer paymentTermsId;
    private Integer deliveryTermsId;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPaymentTermsId() {
        return paymentTermsId;
    }

    public void setPaymentTermsId(Integer paymentTermsId) {
        this.paymentTermsId = paymentTermsId;
    }

    public Integer getDeliveryTermsId() {
        return deliveryTermsId;
    }

    public void setDeliveryTermsId(Integer deliveryTermsId) {
        this.deliveryTermsId = deliveryTermsId;
    }

}
