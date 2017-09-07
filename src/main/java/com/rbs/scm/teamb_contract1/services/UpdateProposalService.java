package com.rbs.scm.teamb_contract1.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamb_contract1.BusinessLogic.UpdateProposalBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.SelectedProposal;

@Path("update")
public class UpdateProposalService {

	@Path("/proposal/view/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SelectedProposal UpdateProposalView(@PathParam("id") int proposalId ) {
		
		return UpdateProposalBL.UpdateProposalView(proposalId);
		
	}
	
	@Path("/proposal")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String UpdateProposal(SelectedProposal selectedProposal) {
		if((selectedProposal.getBuyerStatus().equals("Y"))||(selectedProposal.getBuyerStatus().equals("y")))
				if( UpdateProposalBL.UpdateProposal(selectedProposal))
					return "<p>Update successful</p>";
				else return "Some database error occurred";
				
				
		else 
			return "<p>NOT ALLOWED</p>";
	}
	
	
}
