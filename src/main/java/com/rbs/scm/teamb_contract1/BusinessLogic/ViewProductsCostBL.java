package com.rbs.scm.teamb_contract1.BusinessLogic;

import java.util.List;

import com.rbs.scm.teamb_contract1.DAO.DAOImpl;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewResponse.ProductCost;

public class ViewProductsCostBL {

	public static List<ProductCost> getProductCostForProposal(Integer pid) {
		DAOImpl daoimpl = new DAOImpl();
		int sid = daoimpl.get_selected_seller(pid);
		return daoimpl.get_product_cost(pid, sid);
	}

	public static List<ProductCost> getProductCost(Integer pid, Integer sid) {
		// TODO Auto-generated method stub
		DAOImpl daoimpl = new DAOImpl();
		return daoimpl.get_product_cost(pid, sid);
	}

}
