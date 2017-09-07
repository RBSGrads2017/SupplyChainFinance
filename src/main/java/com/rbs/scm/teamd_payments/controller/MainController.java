package com.rbs.scm.teamd_payments.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbs.scm.teamd_payments.model.beans.*;
import com.rbs.scm.teamd_payments.model.core.Payment;
import com.rbs.scm.teamd_payments.utils.Constants;
import com.rbs.scm.teamd_payments.utils.ConsumeRestService;

@Path("/Transactions")

public class MainController {

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
	 * @throws JSONException 
     */
	
	@Context HttpServletRequest request;
	

    
    @GET 
    @Path("/initTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInititateTransaction(@QueryParam("Id")int Id,@QueryParam("type")String type, @Context HttpServletRequest request,@Context HttpServletResponse response ) throws JSONException, IOException
    {
    	
    		HttpSession ses = request.getSession(false);
    		JSONObject sesObj = new JSONObject((String)ses.getAttribute("sessionObj"));
    		System.out.println(sesObj.get("userId")+"From Session");
    		
    		
    		
    		
    		if(type.equals("Invoice"))
    		{
    			ConsumeRestService consumer = new ConsumeRestService();
        		JSONObject returnObj = new JSONObject(consumer.getInvoice(Id));
        		//Get Account details
        		returnObj.put("creditAccount","123456789");
        		returnObj.put("debitAccount","987654321");
        		returnObj.put("comments","Invoice "+Id);
        		returnObj.put("details","SHR");    		
        		return returnObj.toString();
    		}
    		else
    		{
    			ConsumeRestService consumer = new ConsumeRestService();
        		JSONObject returnObj = new JSONObject(consumer.getInvoice(Id));
        		returnObj.put("creditAccount","123456789");
        		returnObj.put("debitAccount","987654321");
        		returnObj.put("comments","PO "+Id);
        		returnObj.put("details","SHR");    		
        		return returnObj.toString();
    		}
    		
    }
    
    @POST
    @Path("/initTransaction")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postInititateTransaction(String data) throws ParseException, JSONException{
    	
    	JSONObject newObj = new JSONObject(data);
    	String senderId = newObj.getString("sender");
    	double amount = newObj.getDouble("amount");
    	String currency = newObj.getString("currency");
    	String beneficiary = newObj.getString("beneficiary");
    	String debitAcc = newObj.getString("debitAcc");
    	String creditAcc = newObj.getString("creditAcc"); 
    	String date = newObj.getString("date");
    	String details  = newObj.getString("details");
    	String comments  = newObj.getString("comments");
    	
    	
    	Random generator= new Random();
    	int txnId = generator.nextInt(10000);
    	java.sql.Date sqlDate = java.sql.Date.valueOf(date);
    	
    	Customer_Transaction newTrans = new Customer_Transaction(txnId, "104", currency, amount, sqlDate, Constants.AML_NOT_DONE, Constants.AWAITING_APPROVAL, comments,debitAcc,creditAcc, senderId,beneficiary );
    	Payment pay = new Payment();
    	JSONObject returnStatus = new JSONObject();
    	
    	if(pay.createTransaction(newTrans)){
    		returnStatus.put("status", "success");
    		return returnStatus.toString();
    	}
    	else{
    		returnStatus.put("status", "failure");
    		return returnStatus.toString();
    	}
    }
    
    
    @GET 
    @Path("/getAllTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTransactions()
    {
    	Payment pay = new Payment();
    	JSONArray resultArray =  pay.getAllTransactions();
    	return resultArray.toString();
    }
    
    @GET 
    @Path("/getAllPendingTransactions")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPendingTransactions()
    {
    	Payment pay = new Payment();
    	JSONArray resultArray =  pay.getAllPendingTransactions();
    	return resultArray.toString();
    }
    
    
    
    @GET 
    @Path("/getAllMyDebits")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMyDebits()
    {
    	Payment pay = new Payment();
    	String userid = "123";
    	
    	JSONArray resultArray =  pay.getAllMyDebits(userid);
    	return resultArray.toString();
    }
    
    @GET 
    @Path("/getAllMyCredits")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMyCredits()
    {
    	Payment pay = new Payment();
    	String userid = "123";
    	
    	JSONArray resultArray =  pay.getAllMyCredits(userid);
    	return resultArray.toString();
    }
    
    
    @GET 
    @Path("/getTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTransactions(@QueryParam("TransactionId")int txnId)
    {
    	Payment pay = new Payment();
    	JSONObject jsonObj =  pay.getTransaction(txnId);
    	return jsonObj.toString();
    }
    
    
    @POST
    @Path("/setCurrentTransaction")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setCurrentTransaction(String data,@Context HttpServletRequest request) throws JSONException
    {
    	HttpSession ses = request.getSession();
    	
    	JSONObject newObj = new JSONObject(data);
    	String transactionId = newObj.getString("trns_id");
    	Payment pay = new Payment();
    	System.out.println(transactionId);
    	JSONObject returnObj = pay.getTransaction(Integer.parseInt(transactionId));
    	ses.setAttribute("transactionObj", returnObj);
    	System.out.println(ses.getAttribute("transactionObj"));
    	System.out.println("Showing attribute");
    	return "Done";
    }
    @GET
    @Path("/getCurrentTransaction")
    @Produces(MediaType.TEXT_HTML)
    public String getCurrentTransaction()
    {
    	
    	HttpSession ses = request.getSession();
    	System.out.println(ses.getAttribute("transactionObj"));
    	System.out.println("Showing attribute");
    	return ((JSONObject)ses.getAttribute("transactionObj")).toString();
    }
   
    
    @POST
    @Path("/submitSwiftMessage")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String submitSwiftMessage(String data) throws JSONException
    {
    	System.out.println("Swift message submitted");
    	JSONObject newObj = new JSONObject(data);
    	String messageCode = newObj.getString("messageCode");
    	int transactionId = newObj.getInt("transactionId");
    	String sender = newObj.getString("sender");
    	String reciever = newObj.getString("reciever");
    	String messageTxt = newObj.getString("messageText");
    	String bankOperationCode = newObj.getString("bankOperationCode");
    	String senderRef = newObj.getString("senderRef");
    	String interbankSettledAmount = newObj.getString("interbankSettledAmount");
    	String instructedAmount = newObj.getString("instructedAmount");
    	String orderingCustomer = newObj.getString("orderingCustomer");
    	String beneficiaryCustomer = newObj.getString("beneficiaryCustomer");
    	String senderCorrespondent = newObj.getString("senderCorrespondent");
    	String recieverCorrespondent = newObj.getString("recieverCorrespondent");
    	String remitInfo = newObj.getString("remitInfo");
    	String detailsOfCharge = newObj.getString("detailsOfCharge");
    	Payment pay = new Payment();
    	
    	Swift smessage = new Swift(messageCode,transactionId,sender,reciever,messageTxt,bankOperationCode,senderRef,interbankSettledAmount,instructedAmount,orderingCustomer,beneficiaryCustomer,senderCorrespondent,recieverCorrespondent,remitInfo,"SHR");
    	
    	pay.checkAML(transactionId);
    	
    	
    	if(pay.createSwiftMessage(smessage)) {
    		return "success";
    	}
    	else return "failure";
    }
    
    @GET
    @Path("/getAMLFailures")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAMLFailures() throws JSONException
    {
    	Payment pay = new Payment();
    	
    	
    	JSONArray resultArray =  pay.getAMLFailures();
    	return resultArray.toString();
    }
    
    
    @GET
    @Path("/approveAML")
    @Produces(MediaType.TEXT_PLAIN)
    public String approveAML(@QueryParam("TransactionId")int txnId)
    {

    	Payment pay = new Payment();
    	if(pay.approveAML(txnId))
    	{
    		return "success";
    	}
    	else
    	{
    		return "failure";
    	}
    	
    }
    
    
    @GET
    @Path("/rejectAML")
    @Produces(MediaType.TEXT_PLAIN)
    public String rejectAML(@QueryParam("TransactionId")int txnId)
    {

    	Payment pay = new Payment();
    	if(pay.rejectAML(txnId))
    	{
    		return "success";
    	}
    	else
    	{
    		return "failure";
    	}
    	
    }
    
}


