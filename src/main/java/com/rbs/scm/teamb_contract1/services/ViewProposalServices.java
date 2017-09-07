package com.rbs.scm.teamb_contract1.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamb_contract1.BusinessLogic.ViewProposalBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.ListOfProposals;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.SelectedProposal;

@Path("view")
public class ViewProposalServices {

	@Path("/proposals")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListOfProposals proposalList() {
		
		
		/*HttpSession ses = httpRequest.getSession(false);
    	User usr =  (User)ses.getAttribute("user");*/
		//TODO string 
		
		int buyerId = 1;
		
		return ViewProposalBL.view(buyerId);
		
	}
	@Path("/proposal/inactivate/{id}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String inactivateProposal(@PathParam("id") int proposalId) {
		if(ViewProposalBL.InactivateProposal(proposalId))
			return "<p>Proposal Inactivated successfully!!!<p>";
		
		return "<p>Unable to Inactivate proposal!!<p>";
	}
	
	@Path("/proposal/selected/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SelectedProposal ViewSelectedProposal(@PathParam("id") int proposalId) {
			return ViewProposalBL.GetProposalWithId(proposalId);
	}
}
