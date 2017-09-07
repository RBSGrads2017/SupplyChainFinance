
package com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal;

import java.util.List;

public class SelectedProposal {

    private Integer proposalid;
    private Integer bidsellerid;
    private String description;
    private String buyerStatus;
    private String contractStatus;
    private Integer dTermId;
    private Integer pTermId;
    private List<Product> products = null;

    public Integer getProposalid() {
        return proposalid;
    }

    public void setProposalid(Integer proposalid) {
        this.proposalid = proposalid;
    }

    public Integer getBidsellerid() {
        return bidsellerid;
    }

    public void setBidsellerid(Integer bidsellerid) {
        this.bidsellerid = bidsellerid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuyerStatus() {
        return buyerStatus;
    }

    public void setBuyerStatus(String buyerStatus) {
        this.buyerStatus = buyerStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Integer getDTermId() {
        return dTermId;
    }

    public void setDTermId(Integer dTermId) {
        this.dTermId = dTermId;
    }

    public Integer getPTermId() {
        return pTermId;
    }

    public void setPTermId(Integer pTermId) {
        this.pTermId = pTermId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
