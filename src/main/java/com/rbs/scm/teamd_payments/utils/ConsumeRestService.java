package com.rbs.scm.teamd_payments.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ConsumeRestService {

	public String getInvoice(int invoiceId)
	{
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8089/scm/service/Stubs/GetInvoice?InvoiceId="+String.valueOf(invoiceId));

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			return output;

		  } catch (Exception e) {

			e.printStackTrace();
		  }
		return "{}";

	}
	
	
	public String getUser(String userid)
	{
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8089/scm/service/Stubs/GetUser?userid="+userid);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			return output;

		  } catch (Exception e) {

			e.printStackTrace();
		  }
		return "{}";

	}
	public String getAMLStatus(String userid,String country) throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("country", country);
		obj.put("userid",userid);
		
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8089/scm/service/checkAML/?userid="+userid+"&country="+country);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			return output;

		  } catch (Exception e) {

			e.printStackTrace();
		  }
		return "{}";
	}
	
	
}
