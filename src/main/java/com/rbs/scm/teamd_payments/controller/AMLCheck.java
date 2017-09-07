package com.rbs.scm.teamd_payments.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbs.scm.teamd_payments.model.core.ServicesExtra;

@Path("/AML")
public class AMLCheck {
	
	@GET
	@Path("/checkAML")
	
	@Produces(MediaType.TEXT_PLAIN)
	public String checkCountry(@QueryParam("country")String country,@QueryParam("userid")String userid) throws JSONException
	{
		ServicesExtra s = new ServicesExtra();
		return s.checkAML(country, userid).toString();			
	}
}
