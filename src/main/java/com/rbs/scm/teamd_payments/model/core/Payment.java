package com.rbs.scm.teamd_payments.model.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.rbs.scm.teamd_payments.model.beans.*;
import com.rbs.scm.teamd_payments.model.core.*;
import com.rbs.scm.teamd_payments.utils.*;

import com.rbs.scm.teamd_payments.model.dao.PaymentsImpl;
public class Payment {
	
	public boolean createTransaction(Customer_Transaction txn)
	{
		txn.setStatus(Constants.AWAITING_APPROVAL);
		txn.setAml_status(Constants.AML_NOT_DONE);
		try{
			PaymentsImpl p = new PaymentsImpl();
			p.addCustomer_Transaction(txn);
			return true;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	
	public JSONArray getAllTransactions()
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction[] arrayOfTxn = p.getAllCustomerTransactionDetails();
			JSONArray toReturn = new JSONArray();
			for( Customer_Transaction txn:arrayOfTxn)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}return toReturn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONArray newArray = new JSONArray();
			return newArray;
		}
	}
	
	public JSONArray getAllPendingTransactions()
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction[] arrayOfTxn = p.getAllPendingCustomerTransactionDetails(Constants.AWAITING_APPROVAL);
			JSONArray toReturn = new JSONArray();
			for( Customer_Transaction txn:arrayOfTxn)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}
			
			Bank_to_Customer[] arrayOfTxn2 = p.getAllPendingBankToCustomerDetails(Constants.AWAITING_APPROVAL);
			
			for( Bank_to_Customer txn:arrayOfTxn2)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}
			

			Customer_to_Bank[] arrayOfTxn3 = p.getAllPendingCustomerToBankDetails(Constants.AWAITING_APPROVAL);
			
			for( Customer_to_Bank txn:arrayOfTxn3)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}

			
			
			
			return toReturn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONArray newArray = new JSONArray();
			return newArray;
		}
	}
	
	
	public JSONObject getTransaction(int transactionId)
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction txn1 = p.getCustomerTransactionDetails(transactionId);
			Bank_to_Customer txn2 = p.getBankToCustomerDetails(transactionId);
			Customer_to_Bank txn3 = p.getCustomerToBankDetails(transactionId) ;
			JSONObject newObj = new JSONObject();
			
			if(txn1!=null)
			{
				newObj.put("sender", txn1.getPayer_id());
				newObj.put("amount", txn1.getAmount());
				newObj.put("beneficiary", txn1.getPayee_id());
				newObj.put("date", txn1.getTransaction_date());
				newObj.put("details", txn1.getComments());
				newObj.put("comments", txn1.getComments());
				newObj.put("aml_status", txn1.getAml_status());
				newObj.put("status", txn1.getStatus());
				newObj.put("message_code", txn1.getMessage_code());
				newObj.put("transaction_id", txn1.getTransaction_id());
				newObj.put("debitAcc", txn1.getPayer_account());
				newObj.put("creditAcc", txn1.getPayee_account());
				
			}
			else if(txn2!=null)
			{
				newObj.put("sender", txn2.getPayer_id());
				newObj.put("amount", txn2.getAmount());
				newObj.put("beneficiary", txn2.getPayee_id());
				newObj.put("date", txn2.getTransaction_date());
				newObj.put("details", txn2.getComments());
				newObj.put("comments", txn2.getComments());
				newObj.put("aml_status", txn2.getAml_status());
				newObj.put("status", txn2.getStatus());
				newObj.put("message_code", txn2.getMessage_code());
				newObj.put("transaction_id", txn2.getTransaction_id());
				newObj.put("debitAcc", txn2.getPayer_account());
				newObj.put("creditAcc", txn2.getPayee_account());
				
			}
			else if(txn3!=null)
			{
				newObj.put("sender", txn3.getPayer_id());
				newObj.put("amount", txn3.getAmount());
				newObj.put("beneficiary", txn3.getPayee_id());
				newObj.put("date", txn3.getTransaction_date());
				newObj.put("details", txn3.getComments());
				newObj.put("comments", txn3.getComments());
				newObj.put("aml_status", txn3.getAml_status());
				newObj.put("status", txn3.getStatus());
				newObj.put("message_code", txn3.getMessage_code());
				newObj.put("transaction_id", txn3.getTransaction_id());		
				newObj.put("debitAcc", txn3.getPayer_account());
				newObj.put("creditAcc", txn3.getPayee_account());
				
			}
			return newObj;
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONObject newObj = new JSONObject();
			return newObj;
		}
	}
	
	
	public boolean createSwiftMessage(Swift smessage)
	{
		try{
			PaymentsImpl p = new PaymentsImpl();
			p.addSwift(smessage);
			return true;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	public void checkAML(int transactionId) throws JSONException
	{
		JSONObject transObj = getTransaction(transactionId);
		String sender = transObj.getString("sender");
		String beneficiary = transObj.getString("beneficiary");
		ConsumeRestService cons = new ConsumeRestService();
		PaymentsImpl p = new PaymentsImpl(); 
		//Get User country from user details
		String AMLStatusSender =  cons.getAMLStatus(sender, "IND");
		String AMLStatusBeneficiary = cons.getAMLStatus(beneficiary, "IND");
		JSONObject senderStatus = new JSONObject(AMLStatusSender);
		JSONObject beneficiaryStatus = new JSONObject(AMLStatusBeneficiary);
		if(senderStatus.getString("status").equals("success") && beneficiaryStatus.getString("status").equals("success"))
		{
			p.updateAmlStatus(transactionId, Constants.AML_DONE);
			p.updateTransactionStatus(transactionId, Constants.PAYMENT_SUCCESS);
		}
		else
		{
			System.out.println("eee");
			p.updateAmlStatus(transactionId, Constants.AML_FAILED);
			p.updateTransactionStatus(transactionId, Constants.AML_FAILED);
		}
		if(beneficiaryStatus.getString("status").equals("success") && beneficiaryStatus.getString("status").equals("success"))
		{
			p.updateAmlStatus(transactionId, Constants.AML_DONE);
			p.updateTransactionStatus(transactionId, Constants.PAYMENT_SUCCESS);
			paymentSuccess(transactionId);
		}
		else
		{
			System.out.println("eee1");
			p.updateAmlStatus(transactionId, Constants.AML_FAILED);
			p.updateTransactionStatus(transactionId, Constants.AML_FAILED);
		}
	}
	public JSONArray getAllMyDebits(String userid)
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			System.out.println("eee2");
			Customer_Transaction[] arrayOfTxn = p.getCustomerTransactionDetailsbyPayerId(userid);
			JSONArray toReturn = new JSONArray();
			System.out.println("eee1");
			for( Customer_Transaction txn:arrayOfTxn)
			{
				System.out.println("eee");
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				System.out.println(newObj);
				toReturn.put(newObj);
			}
			
			Bank_to_Customer[] arrayOfTxn2 = p.getBankToCustomerDetailsbyPayerId(userid);
			
			for( Bank_to_Customer txn:arrayOfTxn2)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				System.out.println(newObj);
				toReturn.put(newObj);
			}
			

			Customer_to_Bank[] arrayOfTxn3 = p.getCustomerToBankDetailsbyPayerId(userid);
			
			for( Customer_to_Bank txn:arrayOfTxn3)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				System.out.println(newObj);
				toReturn.put(newObj);
			}

			
			
			
			return toReturn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONArray newArray = new JSONArray();
			return newArray;
		}
	}
	
	
	public JSONArray getAllMyCredits(String userid)
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction[] arrayOfTxn = p.getCustomerTransactionDetailsbyPayeeId(userid);
			JSONArray toReturn = new JSONArray();
			for( Customer_Transaction txn:arrayOfTxn)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}
			
			Bank_to_Customer[] arrayOfTxn2 = p.getBankToCustomerDetailsbyPayeeId(userid);
			
			for( Bank_to_Customer txn:arrayOfTxn2)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}
			

			Customer_to_Bank[] arrayOfTxn3 = p.getCustomerToBankDetailsbyPayeeId(userid);
			
			for( Customer_to_Bank txn:arrayOfTxn3)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}

			
			
			
			return toReturn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONArray newArray = new JSONArray();
			return newArray;
		}
	}
	
	
	
	void paymentSuccess(int transactionId)
	{
		return;
	}
	
	
	//Getting all the aml check failure transactions
	public JSONArray getAMLFailures()
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction[] arrayOfTxn = p.getAllCustomerTransactionDetailsByAmlStatus(Constants.AML_FAILED);
			JSONArray toReturn = new JSONArray();
			for( Customer_Transaction txn:arrayOfTxn)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}
			
			Bank_to_Customer[] arrayOfTxn2 = p.getAllBankToCustomerDetailsByAmlStatus(Constants.AML_FAILED);
			
			for( Bank_to_Customer txn:arrayOfTxn2)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}
			

			Customer_to_Bank[] arrayOfTxn3 = p.getAllCustomerToBankDetailsByAmlStatus(Constants.AML_FAILED);
			
			for( Customer_to_Bank txn:arrayOfTxn3)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayee_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				toReturn.put(newObj);
			}

			
			
			
			return toReturn;
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONArray newArray = new JSONArray();
			return newArray;
		}
	}
	
	
	
}
