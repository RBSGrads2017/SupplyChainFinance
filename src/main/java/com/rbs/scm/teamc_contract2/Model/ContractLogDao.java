package com.rbs.scm.teamc_contract2.Model;

import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.ContractLog;


public interface ContractLogDao {
	public boolean insertContractLog (ContractLog contractLog);
	public ContractLog selectLatestContractLog(int contractId);
	public ContractLog selectContractLog(int contract_id, int contractVersion);
	public ArrayList<ContractLog> selectAllContractLogSeller(String sellerId);
	public ArrayList<ContractLog> selectAllContractLogBuyer(String buyerId);
	public ArrayList<ContractLog> selectAllContractLogVersions(int contractId);
    public ArrayList<ContractLog> getAllArchivedContracts();
    public boolean updateStatus(int contractId, int contractVersion,int status);


}
