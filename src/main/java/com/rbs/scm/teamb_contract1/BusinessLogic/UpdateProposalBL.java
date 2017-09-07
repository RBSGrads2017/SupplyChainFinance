package com.rbs.scm.teamb_contract1.BusinessLogic;

import com.rbs.scm.teamb_contract1.DAO.DAOImpl;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.Feature;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.Product;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.SelectedProposal;

public class UpdateProposalBL {

	
	public static SelectedProposal UpdateProposalView(Integer proposalId) {
		return ViewProposalBL.GetProposalWithId(proposalId);
	}
	
	public static boolean UpdateProposal(SelectedProposal selectedProposal) {
		
		DAOImpl daoImpl = new DAOImpl();
		boolean b = daoImpl.UpdateProposalsTable(selectedProposal.getProposalid(),selectedProposal.getDescription());
		for(Product p : selectedProposal.getProducts())	
		{	if(b)
				{boolean pb = daoImpl.UpdateProductTable(p.getId(),p.getQuantity());
					for(Feature f : p.getFeatures()) {
						if(pb)
							daoImpl.UpdateFeatureTable(f.getFid(),p.getId(),selectedProposal.getProposalid(),f.getPriorityOrder());
					}
				}
		}
		
		
		
		
		return true;
	}
	
}
