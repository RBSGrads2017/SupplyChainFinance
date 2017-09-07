package com.rbs.scm.teamc_contract2.stubs;

import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposal;
import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposalProduct;
import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposalProductFeature;

public class ProposalService {
	private SelectedProposal proposal;
	ProposalService(){
		SelectedProposalProductFeature f1 = new SelectedProposalProductFeature("Feature 1", "Highest", 1);
		SelectedProposalProductFeature f2 = new SelectedProposalProductFeature("Feature 2", "Moderate", 2);
		SelectedProposalProductFeature f3 = new SelectedProposalProductFeature("Feature 3", "Low", 3);
		ArrayList<SelectedProposalProductFeature> list1 = new ArrayList<>();
		list1.add(f1);
		list1.add(f2);
		ArrayList<SelectedProposalProductFeature> list2 = new ArrayList<>();
		list2.add(f3);
		list2.add(f2);
		SelectedProposalProduct p1 = new SelectedProposalProduct(500, "Laptop", 1, list1);
		SelectedProposalProduct p2 = new SelectedProposalProduct(1000, "Mobile", 2, list2);
		ArrayList<SelectedProposalProduct> prodList = new ArrayList<>();
		prodList.add(p1);
		prodList.add(p2);
		proposal = new SelectedProposal(1,41, "Proposals Desc", "SomeStatusBuyer", "someContractStatus", 10, 20, prodList);
	}
	
	public SelectedProposal getProposal() {
		return proposal;
	}
}
