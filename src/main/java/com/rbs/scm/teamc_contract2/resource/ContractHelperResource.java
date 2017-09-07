package com.rbs.scm.teamc_contract2.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamc_contract2.businessLayer.ContractBusinessLayer;
import com.rbs.scm.teamc_contract2.data.DeliveryTerm;
import com.rbs.scm.teamc_contract2.data.PaymentTerm;

@Path("/contracts/helper")
public class ContractHelperResource {
	private ContractBusinessLayer contractBL;
	public ContractHelperResource(){
		contractBL = new ContractBusinessLayer();
	}
	@Path("/deliveryTerms")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DeliveryTerm> getDeliveryTerms() {
		return contractBL.getAllDeliveryTerms();
	}
	
	@Path("/paymentTerms")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PaymentTerm> getPaymentTerms() {
		return contractBL.getAllPaymentTerms();
	}
}
