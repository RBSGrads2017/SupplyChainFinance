package com.rbs.scm.teamc_contract2.businessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rbs.scm.teamc_contract2.data.Contract;
import com.rbs.scm.teamc_contract2.data.Feature;
import com.rbs.scm.teamc_contract2.data.Product;
import com.rbs.scm.teamc_contract2.restClient.RestApiCalls;
import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposal;
import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposalProduct;
import com.rbs.scm.teamc_contract2.restClient.pojo.SelectedProposalProductFeature;

public class DraftContractBL {
	// Convert selectedProposal received from team B to Contract.
	private Contract mapContractProposal(SelectedProposal proposal) {
		Contract contract = new Contract();
		contract.setStatus(0);
		// buyid value hard-coded
		contract.setBuyerId("buyer@com");
		// proposals team returning integer as seller id
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		contract.setDateofInvoice(dateFormat.format(date));
		contract.setPeriodOfDelivery("10 days from invoice");
		contract.setSellerId(proposal.getBidsellerid().toString());
		contract.setDeliveryTermId(proposal.getDTermId());
		contract.setPaymentTermId(proposal.getPTermId());
		for(SelectedProposalProduct proposalProduct: proposal.getProducts()) {
			Product p = new Product();
			p.setId(proposalProduct.getId());
			p.setProductName(proposalProduct.getNameOfProduct());
			p.setQuantity(proposalProduct.getQuantity());
			// proposals team doesn't return price
			p.setPrice(29182.221);
			// proposals team doesn't return category
			p.setCategory(1);
			for(SelectedProposalProductFeature productFeature: proposalProduct.getFeatures()) {
				Feature f = new Feature();
				f.setFeatureId(productFeature.getFid());
				f.setName(productFeature.getFeatureName());
				p.addFeature(f);
			}
			
			contract.addProduct(p);
		}
		return contract;
	}
	
	public Contract getDraftContract(int proposalId) {
		SelectedProposal proposal = RestApiCalls.getProposal(proposalId);
		Contract contract = mapContractProposal(proposal);
		// setting contract id same as proposal id
		contract.setContract(proposalId);
		return contract;
	}
}
