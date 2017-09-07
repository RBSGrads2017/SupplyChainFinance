package com.rbs.scm.teamc_contract2.stubs;

public class Feature {
	private String primary;
	private String sub;
	private String featureName;
	private String description;
	private String specification;
	
	public Feature() {
		
	}
	
	

	public Feature(String primary, String sub, String featureName, String description, String specification) {
		super();
		this.primary = primary;
		this.sub = sub;
		this.featureName = featureName;
		this.description = description;
		this.specification = specification;
	}



	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	
}
