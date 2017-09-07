package com.rbs.scm.teamc_contract2.restClient.pojo;

import java.util.ArrayList;
import java.util.List;

public class SelectedProposal {
	private Integer proposalid;
    private Integer bidsellerid;
    private String description;
    private String buyerStatus;
    private String contractStatus;
    private Integer dTermId;
    private Integer pTermId;
    private List<SelectedProposalProduct> products = null;
    
    public SelectedProposal() {
		
	}

	public SelectedProposal(Integer proposalid, Integer bidsellerid, String description, String buyerStatus,
			String contractStatus, Integer dTermId, Integer pTermId, List<SelectedProposalProduct> products) {
		super();
		this.proposalid = proposalid;
		this.bidsellerid = bidsellerid;
		this.description = description;
		this.buyerStatus = buyerStatus;
		this.contractStatus = contractStatus;
		this.dTermId = dTermId;
		this.pTermId = pTermId;
		this.products = products;
	}

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

    public List<SelectedProposalProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SelectedProposalProduct> products) {
        this.products = products;
    }

}
