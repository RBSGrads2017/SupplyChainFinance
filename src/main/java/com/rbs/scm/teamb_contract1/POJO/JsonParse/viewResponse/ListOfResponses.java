package com.rbs.scm.teamb_contract1.POJO.JsonParse.viewResponse;

import java.util.ArrayList;
import java.util.List;

import com.rbs.scm.teamb_contract1.POJO.Table.ProposalSellersBid;

public class ListOfResponses {

	private List<ProposalSellersBid> responses = new ArrayList<ProposalSellersBid>();

	public List<ProposalSellersBid> getResponses() {
		return responses;
	}

	public void setResponses(List<ProposalSellersBid> responses) {
		this.responses = responses;
	}
	
}
