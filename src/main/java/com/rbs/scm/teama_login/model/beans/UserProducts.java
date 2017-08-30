package com.rbs.scm.teama_login.model.beans;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class UserProducts {
	private String username;
	private String[] userProducts = new String[6];
	
	public UserProducts(String username, String[] userProducts) {
		super();
		this.username = username;
		this.userProducts = userProducts;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String[] getUserProducts() {
		return userProducts;
	}
	public void setUserProducts(String[] userProducts) {
		this.userProducts = userProducts;
	}
	
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
}
}