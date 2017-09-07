package com.rbs.scm.teamb_contract1.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamb_contract1.BusinessLogic.StaticDetailsBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.staticDetails.FeaturesDetails;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.staticDetails.PaymentTerms;

@Path("static")
public class StaticDetailsService {
	
	@Path("/paymentterms")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentTerms PaymentTerms() {
		return StaticDetailsBL.viewPaymentTerms();
	}
	
	@Path("/featureslist/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public FeaturesDetails FeaturesList(@PathParam("pid") int product_id) {
		return StaticDetailsBL.viewFeaturesDetails(product_id);
	}
	
}
