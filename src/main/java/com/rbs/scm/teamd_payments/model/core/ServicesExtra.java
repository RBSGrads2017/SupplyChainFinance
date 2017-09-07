package com.rbs.scm.teamd_payments.model.core;

import org.json.JSONException;
import org.json.JSONObject;

import com.rbs.scm.teamd_payments.model.dao.*;

public class ServicesExtra {
	
	public JSONObject checkAML(String country, String userid) throws JSONException
	{
		PaymentsImpl p = new PaymentsImpl();
		boolean countryStatus = p.isCountrySanctioned(country);
		boolean personStatus = p.isPersonSanctioned(userid);
		String responseMessage = "";
		JSONObject newObj = new JSONObject();
		if(!countryStatus && !personStatus)
		{
			newObj.put("status","success");
			newObj.put("message", "AML Check success");
			return newObj;
		}
		if(countryStatus)
			responseMessage +="Country Sanctioned";
		if(personStatus)
			responseMessage+="Person Sanctioned";
		newObj.put("status", "failure");
		newObj.put("message", responseMessage);
		return newObj;
	}
	
}
