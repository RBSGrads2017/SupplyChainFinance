package com.rbs.scm.teamc_contract2.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamc_contract2.businessLayer.ContractBusinessLayer;
import com.rbs.scm.teamc_contract2.businessLayer.DraftContractBL;
import com.rbs.scm.teamc_contract2.data.Contract;
import com.rbs.scm.teamc_contract2.data.MinContract;


@Path("/contracts")
public class ContractResource {
	private ContractBusinessLayer contractBL;
	public ContractResource(){
		contractBL = new ContractBusinessLayer();
	}
	
	@Path("/draft/{proposalId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Contract createDraftContract(@PathParam("proposalId") int proposalId) {
		DraftContractBL draftContractBL = new DraftContractBL();
		Contract contract = draftContractBL.getDraftContract(proposalId);
		return contract;
	}
	
	// Inserting a new contract.
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean postContract(Contract contract) {
		System.out.println(contract);
		return contractBL.insertContract(contract);
	}
	 
	
	// Get the latest contract
	@Path("/{contractId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Contract getContract(@PathParam("contractId") int contractId) {
		return contractBL.getLatestContract(contractId);
	}
	
	// Get specific version of contract
	@GET
	@Path("/{contractId}/version/{contractVersion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Contract getSpecificContract(@PathParam("contractId") int contractId, @PathParam("contractVersion") int contractVersion) {
		return contractBL.getSpecificContract(contractId, contractVersion);
	}
	
	// Get all versions of contract for a specific contract ID
	@GET
	@Path("/{contractId}/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Contract> getAllContractsVersion(@PathParam("contractId") int contractId){
		return contractBL.getAllContractsVersion(contractId);
	}
	
	// Get all archive contract
	@GET
	@Path("/archive")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Contract> getAllArchivedContracts(){
		return contractBL.getAllArchivedContracts();
	}
	
	// Get all contracts involving the user. User "type" is given as Query parameter and can have 2 values,'buyer' and 'seller' 
	@GET
	@Path("/users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	// type = seller or buyer
	public ArrayList<Contract> getContractsByUser(@QueryParam("type") String userType, @PathParam("userId") String userId){
		return contractBL.getAllContractsByUser(userType, userId);
	}
	
	@GET
	@Path("/min/users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	// type = seller or buyer
	public ArrayList<MinContract> getMinContractsByUser(@QueryParam("type") String userType, @PathParam("userId") String userId){
		//return contractBL.getAllContractsByUser(userType, userId);
		return contractBL.getMinContracts(userType, userId);
	}
	
	// Get status of a contract
	@GET
	@Path("/{contractId}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public int getStatus(@PathParam("contractId") int contractId) {
		int status = contractBL.getContractStatus(contractId);
		return status;
	}
	
	//update status of contract to archive
	@POST
	@Path("/update/status")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean postContractStatus(Contract contract) {
		System.out.println(contract);
		return contractBL.updateContractStatus(contract);
	}
	
	
	
	
}
