package com.rbs.scm.teamd_payments.utils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/Stubs")

public class Stubs {

	@GET 
	@Path("/GetInvoice")
	@Produces(MediaType.APPLICATION_JSON)
	public String getInvoice(@QueryParam("InvoiceId")int invoiceId) throws JSONException{
		
		System.out.println(invoiceId);
		JSONObject newObj = new JSONObject();
		newObj.put("invoiceId",invoiceId);
		newObj.put("buyerId",123);
		newObj.put("sellerId",321);
		newObj.put("amount",10000);
		newObj.put("currency","USD");
		newObj.put("dateOfPayment","2017-08-22");
		return newObj.toString();
		
	}
	
	
	
	@GET 
	@Path("/GetUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@QueryParam("userid")int UserId) throws JSONException{
		
		System.out.println(UserId);
		JSONObject newObj = new JSONObject();
		newObj.put("userid",UserId);
		newObj.put("country","IND");
		newObj.put("name","Varun");
		newObj.put("accno","ABCDEFGH");
		return newObj.toString();
		
	}
}
