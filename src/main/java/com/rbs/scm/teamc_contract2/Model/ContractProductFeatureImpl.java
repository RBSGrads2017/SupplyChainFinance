package com.rbs.scm.teamc_contract2.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rbs.scm.teamc_contract2.data.ContractProductFeatureLog;
import com.rbs.scm.teamc_contract2.database.SQLConnection;

public class ContractProductFeatureImpl implements ContractProductFeatureLogDao {

	@Override
	public boolean insertContractProductFeature(ArrayList<ContractProductFeatureLog> contractProductFeatureLog) {
		
		Connection conn = SQLConnection.getConnection();
		PreparedStatement ps = null;
		boolean result = false;
		int version = 0;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt
					.executeQuery("select max(\"version\") from \"contractProductFeatureLog\" where \"contractId\"="
							+ contractProductFeatureLog.get(0).getContractId());
			if (rs.first()) {

				version = rs.getInt(1) + 1;
				
			}

			

			for (ContractProductFeatureLog con : contractProductFeatureLog) {

				ps = conn.prepareStatement("Insert into \"contractProductFeatureLog\" values(?,?,?,?)",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

				ps.setInt(1, con.getContractId());
				ps.setInt(4, con.getProductId());
				ps.setInt(2, con.getFeatureId());
				ps.setInt(3, version);
				result = ps.execute();

				result = true;
			}

		

		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return result;

	}

	@Override
	public ArrayList<ContractProductFeatureLog> fetchContractProductFeature(int contractId, int contractVersion) {
		Connection conn = SQLConnection.getConnection();
		ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<>();
		Statement stmt = null;
	
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractProductFeatureLog\" where \"contractId\" ="
					+ contractId + "AND \"version\" =" + contractVersion);

			while (rs.next()) {
				ContractProductFeatureLog contract = new ContractProductFeatureLog();
				contract.setContractId(rs.getInt(1));
				contract.setProductId(rs.getInt(4));
				contract.setFeatureId(rs.getInt(2));
				contract.setVersion(rs.getInt(3));
				contractPFLog.add(contract);

			}
			

		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return contractPFLog;

	}

	@Override
	public ArrayList<ContractProductFeatureLog> fetchFinalContractProductFeature(int contractId) {
		Connection conn = SQLConnection.getConnection();
		ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<>();
		Statement stmt = null;
		PreparedStatement ps = null;
		int version =0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select max(\"version\") from \"contractProductFeatureLog\" where \"contractId\" =" + contractId);
			if(rs.first()) {
				version = rs.getInt(1);
				
			}
			ps = conn.prepareStatement("Select * from \"contractProductFeatureLog\" where \"contractId\" =" + contractId
					+ "AND \"version\" =" + version ,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
			ResultSet rs1 = ps.executeQuery();

			while (rs1.next()) {
				ContractProductFeatureLog contract = new ContractProductFeatureLog();
				contract.setContractId(rs1.getInt(1));
				contract.setProductId(rs1.getInt(4));
				contract.setFeatureId(rs1.getInt(2));
				contract.setVersion(version);
				
				contractPFLog.add(contract);

			}
		

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return contractPFLog;

	}

	@Override
	public ArrayList<ContractProductFeatureLog> fetchVersionsContractProductFeature(int contractId) {
		Connection conn = SQLConnection.getConnection();
		ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<>();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" =" + contractId);

			while (rs.next()) {
				ContractProductFeatureLog contract = new ContractProductFeatureLog();
				contract.setContractId(rs.getInt(1));
				contract.setProductId(rs.getInt(4));
				contract.setFeatureId(rs.getInt(2));
				contract.setVersion(rs.getInt(3));

				contractPFLog.add(contract);

			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return contractPFLog;

	}

	
}
