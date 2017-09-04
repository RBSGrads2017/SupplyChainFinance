package com.rbs.scm.teamb_contract1.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamb_contract1.BusinessLogic.PublishProposalBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.PublishProposal.IdentifiedSellers;

@Path("publish")
public class PublishProposal {

	@Path("/proposal/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public IdentifiedSellers GetAllSellers(@PathParam("id") int proposalId ) {
		PublishProposalBL bl = new PublishProposalBL();
		System.out.println(proposalId);
		return bl.GetListOfSellers(proposalId);
	}
	
	@Path("/toIdentifiedSellers/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String NotifySelectedsellers(IdentifiedSellers  identifiedSellers,@PathParam("id")Integer proposalId) {
		if(identifiedSellers.getSellers().isEmpty())
			return "<p>At least one seller must be selected</p>";
		else {
			PublishProposalBL bl = new PublishProposalBL();
			bl.PublishToSellers(identifiedSellers,proposalId);
		return "<p>Sellers notified</p>";	
		}
	}
	
}
