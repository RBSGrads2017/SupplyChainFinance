package com.rbs.scm.teamc_contract2.restClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposal;

public class RestApiCalls {
	static Client client = ClientBuilder.newClient();
	public static SelectedProposal getProposal(int proposalId) {
		//final String getProposalURL = "http://localhost:8080/ContractManagement2/webapi/proposals/selected/" + String.valueOf(proposalId);
		final String getProposalURL = "/service/proposals";
		WebTarget target = client.target(getProposalURL);
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		Response response = builder.get();
		SelectedProposal proposal  = response.readEntity(SelectedProposal.class);
		return proposal;
	}
}
