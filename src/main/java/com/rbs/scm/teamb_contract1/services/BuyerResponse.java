package com.rbs.scm.teamb_contract1.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamb_contract1.BusinessLogic.BuyerResponseBL;
import com.rbs.scm.teamb_contract1.BusinessLogic.StaticDetailsBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.staticDetails.PaymentTerms;

@Path("buyerresponse")
public class BuyerResponse {
	
	@Path("/award/{pid}/{sid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String award(@PathParam("pid") int proposal_id, @PathParam("sid") int seller_id) {
		if (BuyerResponseBL.award(proposal_id, seller_id)) {
			return "<p>Successful</p>";
		} else {
			return "<p>Unsuccessful</p>";
		}
	}
	
	@Path("/reject/{pid}/{sid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String reject(@PathParam("pid") int proposal_id, @PathParam("sid") int seller_id) {
		if (BuyerResponseBL.reject(proposal_id, seller_id)) {
			return "<p>Successful</p>";
		} else {
			return "<p>Unsuccessful</p>";
		}
	}
}
