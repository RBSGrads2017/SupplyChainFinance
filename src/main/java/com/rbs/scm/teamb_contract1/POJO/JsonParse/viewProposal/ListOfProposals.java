package com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal;

import java.util.ArrayList;
import java.util.List;

import com.rbs.scm.teamb_contract1.POJO.Table.ProposalsTable;

public class ListOfProposals {
	private List<ProposalsTable> proposals = new ArrayList<ProposalsTable>();

	public List<ProposalsTable> getProposals() {
		return proposals;
	}

	public void setProposals(List<ProposalsTable> proposals) {
		this.proposals = proposals;
	}
}
