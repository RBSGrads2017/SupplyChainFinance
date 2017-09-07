package com.rbs.scm.teamc_contract2.stubs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposal;

@Path("/proposals")
public class ProposalResource {
	private ProposalService proposalService = new ProposalService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SelectedProposal getProposal() {
		return proposalService.getProposal();
	}
}
