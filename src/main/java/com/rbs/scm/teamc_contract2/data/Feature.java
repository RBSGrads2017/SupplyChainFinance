package com.rbs.scm.teamc_contract2.data;

public class Feature {
int featureId;
String name;
public int getFeatureId() {
	return featureId;
}
public void setFeatureId(int featureId) {
	this.featureId = featureId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Feature(int featureId, String name) {
	super();
	this.featureId = featureId;
	this.name = name;
}
public Feature() {
	super();
}
@Override
public String toString() {
	return "Feature [featureId=" + featureId + ", name=" + name + "]";
}


}
