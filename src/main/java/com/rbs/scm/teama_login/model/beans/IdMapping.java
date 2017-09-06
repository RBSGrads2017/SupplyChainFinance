package com.rbs.scm.teama_login.model.beans;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class IdMapping {
	String UserStringId;
	int UserIntId;
	public String getUserStringId() {
		return UserStringId;
	}
	public void setUserStringId(String userStringId) {
		UserStringId = userStringId;
	}
	public int getUserIntId() {
		return UserIntId;
	}
	public void setUserIntId(int userIntId) {
		UserIntId = userIntId;
	}
	public IdMapping(String userStringId, int userIntId) {
		super();
		UserStringId = userStringId;
		UserIntId = userIntId;
	}
	public String toString() {
		return "Idmapping [StringId=" + UserStringId + ", IntId=" + UserIntId + "]";
	}
	
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
	}
}
