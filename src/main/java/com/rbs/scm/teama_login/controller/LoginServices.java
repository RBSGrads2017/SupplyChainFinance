package com.rbs.scm.teama_login.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbs.scm.teama_login.model.beans.AdditionalDetails;
import com.rbs.scm.teama_login.model.beans.BankUser;
import com.rbs.scm.teama_login.model.beans.Customer;
import com.rbs.scm.teama_login.model.beans.GenericUser;
import com.rbs.scm.teama_login.model.beans.Session;
import com.rbs.scm.teama_login.model.beans.UserProducts;
import com.rbs.scm.teama_login.model.dao.AdditionalDetailsDao;
import com.rbs.scm.teama_login.model.dao.BankUserDaoImpl;
import com.rbs.scm.teama_login.model.dao.CustomerDaoImpl;
import com.rbs.scm.teama_login.model.dao.GenericUserDaoImpl;
import com.rbs.scm.teama_login.model.dao.SessionUtility;
import com.rbs.scm.teama_login.model.dao.UserProductsDao;
import com.rbs.scm.teama_login.utils.MyMailClass;

@Path("login")
public class LoginServices {
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("checkCredentials")
	public Response authenticateUser(String data, @Context HttpServletRequest request) throws JSONException {
		JSONObject inputJsonObj = new JSONObject(data);
		
		String username = inputJsonObj.getString("username");
		String password = inputJsonObj.getString("password");
		System.out.println(username);
		GenericUser gu = null;
		try {
			gu = GenericUserDaoImpl.searchUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
		System.out.println(gu);
		if(gu == null)	{ 
			System.out.print("doesnotexist");
			return Response.ok("UserDoesNotExist").header("Access-Control-Allow-Origin", "*").status(Status.OK).build(); 
		}
		
		System.out.println("hi");
		
		if(password.equals(gu.getPassword())) {
			String TypeOfUser = null;
			
			if (gu.get_is_Bank_User()) {
				TypeOfUser = "Bank";
			} else {
				TypeOfUser = "Customer";
			}
			
			Session s = new Session(gu.getUsername(), TypeOfUser);
			HttpSession hs = request.getSession();//CREATE A SESSION FOR THE USER.
			hs.setAttribute("session", s);
			
			return Response.ok("LoggedInSuccessfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();  // Here we can redirect to the landing page
		} else {
			return Response.ok("WrongCredentials").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
		
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("getUserInfo")
	public String checkToken(@Context HttpServletRequest request) throws JSONException{
		Session s = SessionUtility.sessionValidation(request);
		if(s != null) {
			String obj = "{\"username\": \"" + s.getUserId() + "\", \"userType\": \"" + s.getUserType() + "\"}";
			return obj;
		}
		return "no session";
	}
	
	@POST
	@Path("logout")
	public boolean logoutUser(@Context HttpServletRequest request) {
		HttpSession hs = request.getSession(false);
		hs.invalidate();
		return true;
	}
	

	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("signupCustomer")
	public Response signUpCustomer(String data, @Context HttpServletRequest request) throws JSONException{
		JSONObject inputJsonObj = new JSONObject(data);
		String name = inputJsonObj.getString("name");
		String email = inputJsonObj.getString("email");
		String password = inputJsonObj.getString("password");
		
		Customer c = new Customer(name,email);
		GenericUser gu = new GenericUser(email,password,false, false, false);
		System.out.println(c);
		
		boolean flag_notfound = false;
		try {
			flag_notfound = CustomerDaoImpl.insertIntoCustomers(gu,c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
		if (flag_notfound) {
			return Response.ok("SignupSuccess").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		} else {
			return Response.ok("UserExists").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
	}
	
	
	@GET
	@Path("fetchCustomerDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchCustomerDetails(@QueryParam("uname") String Uname ,@Context HttpServletRequest request) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		Customer c = CustomerDaoImpl.searchCustomer(Uname);
		if(c == null)	{ return null; }
		return c.convertObjectToJSON();
	}
	
	@GET
	@Path("fetchAdditionalDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchAdditionalDetails(@QueryParam("uname") String Uname ,@Context HttpServletRequest request) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		AdditionalDetails c = AdditionalDetailsDao.search(Uname);
		if(c == null)	{ return null; }
		System.out.println(c.getContNumber());
		return c.convertObjectToJSON();
	}
	
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("signupUser")
	public Response signupUser(String data, @Context HttpServletRequest request) throws JSONException{
		JSONObject inputJsonObj = new JSONObject(data);
		String username= inputJsonObj.getString("CorpID");
		String password = inputJsonObj.getString("Password");
		String Fullname = inputJsonObj.getString("Fullname");
		String address = inputJsonObj.getString("address");
		String p_group = inputJsonObj.getString("privgrp");
		BankUser b = new BankUser(username,Fullname,address,p_group);
		GenericUser gu = new GenericUser(username,password,false, false, true);
		System.out.println(b);
		
		
		boolean flag_notfound = false;
		try {
			flag_notfound = BankUserDaoImpl.insertIntoBankUser(gu,b);
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
		if (flag_notfound) {
			return Response.ok("SignupSuccess").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		} else {
			return Response.ok("UserExists").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
	}
	
	
	

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("updateDetails")

	public Response addDetails(String data, @Context HttpServletRequest request) throws JSONException {
		JSONObject JsonObj = new JSONObject(data);
		System.out.println("hi");
		Session s = SessionUtility.sessionValidation(request);
		String username = s.getUserId();
		String swift = JsonObj.getString("Swift");
		int accnumber = JsonObj.getInt("AccNumber");
		int contnumber = JsonObj.getInt("ContNumber");
		String postallocation = JsonObj.getString("PostalLocation");
		String postalcity = JsonObj.getString("PostalCity");
		String postalstate = JsonObj.getString("PostalState");
		String factorylocation = JsonObj.getString("FactoryLocation");
		String factorycity = JsonObj.getString("FactoryCity");
		String factorystate = JsonObj.getString("FactoryState");
		String department = JsonObj.getString("Department");
		//String username = "neha";
		//return Response.ok("Details updated Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		
		AdditionalDetails ad = new AdditionalDetails(username,swift, accnumber, contnumber, postallocation, factorylocation, postalcity, factorycity, postalstate, factorystate, department);
		try {
			AdditionalDetailsDao.insertIntoAdditionalDetails(ad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
		return Response.ok("DetailsUpdatedSuccessfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
	}



	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("updateProducts")
	public Response addproducts(String data, @Context HttpServletRequest request) throws JSONException {
		JSONObject JsonObj = new JSONObject(data);
		JSONArray productCategories = JsonObj.getJSONArray("UserProductsCategories");
		JSONArray products = JsonObj.getJSONArray("UserProducts");
		Session s = SessionUtility.sessionValidation(request);
		String username = s.getUserId();
		
		String[] prod = new String[products.length()];
		System.out.println(products);
		System.out.println(productCategories);
		for (int j=0; j<products.length(); j++) {
			prod[j]=(products.get(j).toString());	
		}
		UserProducts up = new UserProducts(username, prod);
		
		try {
			 UserProductsDao.insertIntoProducts(up);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
		return Response.ok("ProductsUpdatedSuccessfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();	
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("forgotPassword")
	public Response forgotPassword(String data, @Context HttpServletRequest request) throws JSONException {
		
		System.out.println("entered into forgotPassword service");
				
		JSONObject inputJsonObj = new JSONObject(data);
		String customerEmail = inputJsonObj.getString("email");	
		// check db 
		
		GenericUser gu = null;
		try {
			gu = GenericUserDaoImpl.searchUser(customerEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
		
		if (gu == null) {
			return Response.ok("UserDoesNotExist").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
		
		String subjectToSend="Password change request";
		Random rand = new Random();			 
		String randomString   = Integer.toString(rand.nextInt(5000));
		// db store token
		
		try {
			GenericUserDaoImpl.addToPwdTable(gu, randomString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();
		}
			
		//String basePath = request.getScheme()+request.getRemoteHost()+request.getpo;
		System.out.println("the random string generated is"+randomString);
		
		String messageToSend = "Hi!! To reset the password kindly click the link. \n" + "\n<a href='http://localhost:8089/scm/pages/teama_login/ChangePassword.htm?"+randomString+"'>Reset password</a>";
		
		System.out.print(customerEmail + " this is the email where i have to send the message");

		MyMailClass.sendMail(customerEmail ,messageToSend,subjectToSend);		
			
	    return Response.ok("Mailed Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();  // Here we can redirect to the landing page
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("saveNewPassword")
	public Response changePassword(String data, @Context HttpServletRequest request) throws JSONException {
		
		System.out.println("entered into change Password service");
		
		JSONObject inputJsonObj = new JSONObject(data);
		String newPassword = inputJsonObj.getString("newPassword");
		String token = inputJsonObj.getString("token");
		//token 
		System.out.print(newPassword + " this is the password that i have to now chnage in db");
		System.out.print(token + " this is the token for the user id");
		
		String Username = null;
		try {
			Username = GenericUserDaoImpl.GetUserFromToken(token);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Status.EXPECTATION_FAILED).build();		
		}
		
		if (Username != null) {
			 try {
				GenericUserDaoImpl.ChangePwdUser(Username, newPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.serverError().status(Status.EXPECTATION_FAILED).build();		
			}
		} else {
			 return Response.ok("LinkGotExpired").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
		return Response.ok("LoggedInSuccessfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
	}
}
