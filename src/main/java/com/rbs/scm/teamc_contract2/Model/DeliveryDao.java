package com.rbs.scm.teamc_contract2.Model;

import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.DeliveryTerm;

public interface DeliveryDao {
	
	public ArrayList<DeliveryTerm> fetchAllDeliveryTerm();

}
