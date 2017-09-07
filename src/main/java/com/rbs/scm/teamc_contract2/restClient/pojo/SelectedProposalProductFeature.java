package com.rbs.scm.teamc_contract2.restClient.pojo;

public class SelectedProposalProductFeature {
	private String featureName;
    private String priorityOrder;
    private Integer fid;
    public SelectedProposalProductFeature() {
		
	}
    public SelectedProposalProductFeature(String featureName, String priorityOrder, Integer fid) {
		this.featureName = featureName;
		this.priorityOrder = priorityOrder;
		this.fid = fid;
	}

	public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(String priorityOrder) {
        this.priorityOrder = priorityOrder;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
