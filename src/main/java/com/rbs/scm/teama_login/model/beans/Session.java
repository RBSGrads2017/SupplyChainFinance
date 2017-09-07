package com.rbs.scm.teama_login.model.beans;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Session {
	String userId;
	String userType;
	int userIdInt;
	String UserFullName;
	
	public Session(String userId, String userType, int userIdInt, String userFullName) {
		this.userId = userId;
		this.userType = userType;
		this.userIdInt = userIdInt;
		UserFullName = userFullName;
	}

	public int getUserIdInt() {
		return userIdInt;
	}

	public void setUserIdInt(int userIdInt) {
		this.userIdInt = userIdInt;
	}

	public String getUserFullName() {
		return UserFullName;
	}

	public void setUserFullName(String userFullName) {
		UserFullName = userFullName;
	}

	public Session(String userId, String userType) {
		this.userId = userId;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Session [userId=" + userId + ", userType=" + userType + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
	}
	
	
}
