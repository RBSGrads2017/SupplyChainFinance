package com.rbs.scm.teamc_contract2.Model;

import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.PaymentTerm;

public interface PaymentsDao {
	
	public ArrayList<PaymentTerm> fetchAllPaymentTerms();

}
