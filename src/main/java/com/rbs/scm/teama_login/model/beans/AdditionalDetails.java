package com.rbs.scm.teama_login.model.beans;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class AdditionalDetails {
	String username;
	int contNumber;
	String swift;
	int accNumber;
	String postalLocation; 
	String factoryLocation; 
	String postalCity; 
	String factoryCity; 
	String postalState; 
	String factoryState; 
	String department;
	
	

	public AdditionalDetails(String username, String swift, int accNumber, int contNumber, String postalLocation, String factoryLocation,
			String postalCity, String factoryCity, String postalState, String factoryState, String department) {
		super();
		this.swift = swift;
		this.accNumber = accNumber;
		this.contNumber = contNumber;
		this.postalLocation = postalLocation;
		this.factoryLocation = factoryLocation;
		this.postalCity = postalCity;
		this.factoryCity = factoryCity;
		this.postalState = postalState;
		this.factoryState = factoryState;
		this.department = department;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public int getContNumber() {
		return contNumber;
	}
	public void setContNumber(int contNumber) {
		this.contNumber = contNumber;
	}
	public String getPostalLocation() {
		return postalLocation;
	}
	public void setPostalLocation(String postalLocation) {
		this.postalLocation = postalLocation;
	}
	public String getFactoryLocation() {
		return factoryLocation;
	}
	public void setFactoryLocation(String factoryLocation) {
		this.factoryLocation = factoryLocation;
	}
	public String getPostalCity() {
		return postalCity;
	}
	public void setPostalCity(String postalCity) {
		this.postalCity = postalCity;
	}
	public String getFactoryCity() {
		return factoryCity;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public int getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public void setFactoryCity(String factoryCity) {
		this.factoryCity = factoryCity;
	}
	public String getPostalState() {
		return postalState;
	}
	public void setPostalState(String postalState) {
		this.postalState = postalState;
	}
	public String getFactoryState() {
		return factoryState;
	}
	public void setFactoryState(String factoryState) {
		this.factoryState = factoryState;
	}
	

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Additional detalis for Customer: [ " + " Contact Number=" + contNumber +
				", Postal Address: {Location=" + postalLocation + ", City= " + postalCity + 
				", State="+ postalState + " }"+ ", Factory Address: {Location=" + factoryLocation + ", City= " + factoryCity + 
				", State="+ factoryState + " }" +
				", Department= "+ department + "]";
	}
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
	}
}
