package com.rbs.scm.teamc_contract2.Model;

import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.ContractProductPrice;

public interface ContractProductPriceDAO {
	public boolean insertContractProduct(ContractProductPrice contractProductPrice);
	public ArrayList<ContractProductPrice> selectContractProductDetails(int contractId, int contractVersion);
	public ArrayList<ContractProductPrice> selectAllContractProductDetails(int contractId);
	public ArrayList<ContractProductPrice> selectLatestContractProductDetails(int contractId);
	

}
