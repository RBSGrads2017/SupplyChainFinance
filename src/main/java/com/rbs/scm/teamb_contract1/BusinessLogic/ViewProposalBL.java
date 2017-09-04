package com.rbs.scm.teamb_contract1.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import com.rbs.scm.teamb_contract1.DAO.DAOImpl;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.Feature;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.ListOfProposals;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.Product;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewProposal.SelectedProposal;
import com.rbs.scm.teamb_contract1.POJO.Table.FeaturesTable;
import com.rbs.scm.teamb_contract1.POJO.Table.ProductsTable;
import com.rbs.scm.teamb_contract1.POJO.Table.ProposalsTable;

public class ViewProposalBL {
	
	public static ListOfProposals view(Integer id) {
		
		
		
		ListOfProposals lp = new ListOfProposals();
		DAOImpl daoImpl = new DAOImpl();
		
		lp.setProposals(daoImpl.view_proposals(id));
		
		return lp;
	}
	
	public static boolean InactivateProposal(Integer proposalId) {
		DAOImpl daoImpl = new DAOImpl();
		
		return daoImpl.InactivateProposal(proposalId);
	}
	
	public static SelectedProposal GetProposalWithId(Integer proposalId) {
		SelectedProposal sp = new SelectedProposal();
		DAOImpl daoImpl = new DAOImpl();
		ProposalsTable pt = daoImpl.get_proposal(proposalId);
		sp.setBidsellerid(pt.getBidSellerId());
		sp.setBuyerStatus(String.valueOf(pt.getBuyerStatus()));
		sp.setContractStatus(String.valueOf(pt.getContractStatus()));
		sp.setDescription(pt.getDescription());
		sp.setDTermId(pt.getdTermsId());
		sp.setPTermId(pt.getpTermsId());
		sp.setProposalid(pt.getProposalId());
		List<Product> prods = new ArrayList<Product>();		
		List<ProductsTable> pts = daoImpl.get_products(sp.getProposalid());
		for(ProductsTable prodt : pts) {
			Product jsonp = new Product();
			jsonp.setId(prodt.getId());
			jsonp.setQuantity(prodt.getQuantity());
			jsonp.setNameOfProduct(daoImpl.get_product_name(prodt.getProductId()));
			List<FeaturesTable> fts = new ArrayList<FeaturesTable>();
			List<Feature> features = new ArrayList<Feature>();

			fts = daoImpl.get_features(prodt);
			for(FeaturesTable featt : fts) {
				Feature jsonf = new Feature();
				jsonf.setFid(featt.getFeaturesId());
				jsonf.setPriorityOrder(String.valueOf(featt.getPriorityOrder()));
				jsonf.setFeatureName(daoImpl.get_feature_name(featt.getFeaturesId(),featt.getProductsId()));
				features.add(jsonf);
			}
			jsonp.setFeatures(features);
			prods.add(jsonp);
		}
		sp.setProducts(prods);
		return sp;
	}

}
