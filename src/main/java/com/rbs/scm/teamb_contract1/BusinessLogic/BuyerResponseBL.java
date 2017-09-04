package com.rbs.scm.teamb_contract1.BusinessLogic;

import com.rbs.scm.teamb_contract1.DAO.DAOImpl;

public class BuyerResponseBL {
	
	public static boolean award(int proposal_id, int seller_id) {
		DAOImpl daoimpl = new DAOImpl();
		return daoimpl.award(proposal_id, seller_id);			
	}
	
	public static boolean reject(int proposal_id, int seller_id) {
		DAOImpl daoimpl = new DAOImpl();
		return daoimpl.reject(proposal_id, seller_id);			
	}

}
