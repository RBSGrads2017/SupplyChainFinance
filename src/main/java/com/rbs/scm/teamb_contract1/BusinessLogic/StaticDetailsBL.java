package com.rbs.scm.teamb_contract1.BusinessLogic;

import com.rbs.scm.teamb_contract1.DAO.DAOImpl;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.staticDetails.FeaturesDetails;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.staticDetails.PaymentTerms;

public class StaticDetailsBL {
	
	public static FeaturesDetails viewFeaturesDetails(int product_id) {
		
		FeaturesDetails fd = new FeaturesDetails();
		DAOImpl daoimpl = new DAOImpl();
		fd.setFeatures_list(daoimpl.get_features_by_product(product_id));
		return fd;
		
	}
	
	public static PaymentTerms viewPaymentTerms() {
		
		PaymentTerms pt = new PaymentTerms();
		DAOImpl daoimpl = new DAOImpl();
		pt.setPayment_terms(daoimpl.get_payment_terms());
		return pt;

	}

}
