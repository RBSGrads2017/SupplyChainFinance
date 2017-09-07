package com.rbs.scm.teamb_contract1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.rbs.scm.teamb_contract1.POJO.JsonParse.PublishProposal.IdentifiedSellers;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewResponse.ProductCost;
import com.rbs.scm.teamb_contract1.POJO.Table.FeaturesDetailsTable;
import com.rbs.scm.teamb_contract1.POJO.Table.FeaturesTable;
import com.rbs.scm.teamb_contract1.POJO.Table.PaymentTermsTable;
import com.rbs.scm.teamb_contract1.POJO.Table.ProductsTable;
import com.rbs.scm.teamb_contract1.POJO.Table.ProposalSellersBid;
import com.rbs.scm.teamb_contract1.POJO.Table.ProposalsTable;
import com.rbs.scm.teamb_contract1.POJO.Table.ResponseTable;

public class DAOImpl {

	private Connection create_connection() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection connection = null;
		ResultSet rs = null;

		try {
			/*
			 * connection = DriverManager.getConnection(
			 * "jdbc:postgresql://localhost:5432/vi", "postgres", "1");
			 */

			String url = "jdbc:postgresql://ec2-23-21-85-76.compute-1.amazonaws.com:5432/d11rrktmmgd00t?user=liybotsvyembvp&password=e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			Properties props = new Properties();
			props.setProperty("user", "liybotsvyembvp");
			props.setProperty("password", "e40dd8cba8730c3a7b167d3648acbb0d442bdef9c92cb9062110193cf126b37a");
			props.setProperty("ssl", "true");
			connection = DriverManager.getConnection(url, props);

			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		/*
		 * try { String url =
		 * "jdbc:postgresql://ec2-50-17-217-166.compute-1.amazonaws.com:5432/ddq7urlrfunt2g?user=vkpofcuxsisuio&password=02a43c5a4c1e333a2f24847cb75629d6ce8a0e05276a6ae98c246bd0af103009&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		 * Properties props = new Properties(); props.setProperty("user",
		 * "vkpofcuxsisuio"); props.setProperty("password",
		 * "02a43c5a4c1e333a2f24847cb75629d6ce8a0e05276a6ae98c246bd0af103009");
		 * props.setProperty("ssl", "true"); connection =
		 * DriverManager.getConnection(url, props);
		 * 
		 * if (connection != null) { Statement s = connection.createStatement(); rs =
		 * s.executeQuery("select * from emp"); rs.next(); rs.next(); return
		 * rs.getInt(1); } } catch (SQLException e) {
		 * System.out.println("Connection Failed! Check output console");
		 * e.printStackTrace(); return -2; } finally { try { connection.close();
		 * rs.close(); } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
	}

	public Integer create_proposal(ProposalsTable pt) {

		System.out.println("create proposal called");
		Integer i;
		PreparedStatement statement = null;
		Connection connection = create_connection();
		try {
			/*
			 * Class.forName("org.postgresql.Driver"); connection =
			 * DriverManager.getConnection(
			 * "jdbc:postgresql://localhost:5432/cm?currentSchema=public", "postgres", "1");
			 */

			/*
			 * Class.forName("org.postgresql.Driver"); String url =
			 * "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			 * Properties props = new Properties(); props.setProperty("user",
			 * "lqmwbjsssdqgqd"); props.setProperty("password",
			 * "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			 * props.setProperty("ssl", "true"); connection =
			 * DriverManager.getConnection(url, props);
			 */

			String sql = "INSERT into \"Proposals\" (";
			sql += "buyer_id, description, buyer_status, d_terms_id, p_terms_id, contract_status";
			sql += ") VALUES (";
			sql += "?, ?, ?, ?, ?, ?";
			sql += ") RETURNING proposal_id";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, pt.getBuyerId());
			statement.setString(2, pt.getDescription());
			statement.setString(3, Character.toString(pt.getBuyerStatus()));
			statement.setInt(4, pt.getdTermsId());
			statement.setInt(5, pt.getpTermsId());
			statement.setString(6, "n");
			ResultSet rs = statement.executeQuery();
			rs.next();
			i = new Integer(rs.getInt(1));
			System.out.println("In proposal dao proposal id is ............................." + i);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			i = new Integer(-1);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return i;

	}

	public Integer enter_proposal_product(ProductsTable pt) {

		PreparedStatement statement = null;
		Connection connection = create_connection();
		Integer i;

		try {
			/*
			 * Class.forName("org.postgresql.Driver"); connection =
			 * DriverManager.getConnection(
			 * "jdbc:postgresql://localhost:5432/cm?currentSchema=public", "postgres", "1");
			 * System.out.println("Inserting product");
			 */

			/*
			 * Class.forName("org.postgresql.Driver"); String url =
			 * "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			 * Properties props = new Properties(); props.setProperty("user",
			 * "lqmwbjsssdqgqd"); props.setProperty("password",
			 * "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			 * props.setProperty("ssl", "true"); connection =
			 * DriverManager.getConnection(url, props);
			 */

			String sql = "INSERT into \"Products\" (";
			sql += "product_id, proposal_id, quantity";
			sql += ") VALUES (";
			sql += "?, ?, ?";
			sql += ") RETURNING id";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, pt.getProductId());
			statement.setInt(2, pt.getProposalId());
			statement.setInt(3, pt.getQuantity());
			ResultSet rs = statement.executeQuery();
			rs.next();
			i = new Integer(rs.getInt(1));
			System.out.println("In product dao product id is ............................." + i);
			System.out.println("In product dao proposal id is ............................." + pt.getProposalId());

			rs.close();
		} catch (SQLException e) {
			i = new Integer(-1);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return i;
	}

	public Integer enter_product_features(FeaturesTable ft) {

		PreparedStatement statement = null;
		Connection connection = create_connection();
		Integer i;

		try {
			/*
			 * Class.forName("org.postgresql.Driver"); connection =
			 * DriverManager.getConnection(
			 * "jdbc:postgresql://localhost:5432/cm?currentSchema=public", "postgres", "1");
			 */

			/*
			 * Class.forName("org.postgresql.Driver"); String url =
			 * "jdbc:postgresql://ec2-107-20-226-93.compute-1.amazonaws.com:5432/d3gt88jfl1r6i7?user=lqmwbjsssdqgqd&password=b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			 * Properties props = new Properties(); props.setProperty("user",
			 * "lqmwbjsssdqgqd"); props.setProperty("password",
			 * "b05b46f323cb29b4b459ae7d33febec7b16b6e303364a74623ca627c47213afd");
			 * props.setProperty("ssl", "true"); connection =
			 * DriverManager.getConnection(url, props);
			 */

			String sql = "INSERT into \"Features\" (";
			sql += "priority_order, f_id, p_id, proposal_id";
			sql += ") VALUES (";
			sql += "?, ?, ?, ?";
			sql += ")";

			statement = connection.prepareStatement(sql);
			statement.setInt(3, ft.getProductsId());
			statement.setInt(2, ft.getFeaturesId());
			statement.setString(1, Character.toString(ft.getPriorityOrder()));
			statement.setInt(4, ft.getProposalId());
			i = new Integer(statement.executeUpdate());

			System.out.println("In features dao feature id is ............................." + i);
			System.out.println("In features dao product id is ............................." + ft.getProductsId());

		} catch (SQLException e) {
			i = new Integer(-1);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return i;
	}

	public List<ProposalsTable> view_proposals(int buyer_id) {

		Statement statement = null;
		List<ProposalsTable> pt_list = new ArrayList<ProposalsTable>();

		Connection connection = create_connection();
		System.out.println("entered view proposals");

		try {
			String sql = "SELECT ";
			sql += "proposal_id, bid_seller_id, description, buyer_status, contract_status, d_terms_id, p_terms_id";
			sql += " from \"Proposals\"";
			sql += " WHERE buyer_id=";
			sql += buyer_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ProposalsTable pt = new ProposalsTable();
				pt.setProposalId(rs.getInt(1));
				pt.setBidSellerId(rs.getInt(2));
				pt.setDescription(rs.getString(3));
				pt.setBuyerStatus((rs.getString(4)).charAt(0));
				pt.setContractStatus((rs.getString(5)).charAt(0));
				pt.setdTermsId(rs.getInt(6));
				pt.setpTermsId(rs.getInt(7));
				pt_list.add(pt);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pt_list;

	}

	public String get_category_name(int product_id) {

		Statement statement = null;
		Connection connection = create_connection();
		String product_name = null;

		try {
			String sql = "SELECT ";
			sql += "\"Category\"";
			sql += " from \"Products_list\"";
			sql += " WHERE \"Product_id\"=";
			sql += product_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			rs.next();
			product_name = rs.getString(1);
			return product_name;
		} catch (SQLException e) {
			e.printStackTrace();
			return product_name;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<FeaturesDetailsTable> get_features_by_product(int product_id) {

		Statement statement = null;
		Connection connection = create_connection();
		List<FeaturesDetailsTable> fdt_list = new ArrayList<FeaturesDetailsTable>();

		try {
			String sql = "SELECT ";
			sql += "feature_id, feature_name";
			sql += " from \"feature\"";
			sql += " WHERE \"product_id\"=";
			sql += product_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				FeaturesDetailsTable fdt = new FeaturesDetailsTable();
				fdt.setFeature_id(rs.getInt(1));
				fdt.setFeature_name(rs.getString(2));
				fdt_list.add(fdt);
			}
			rs.close();
			return fdt_list;
		} catch (SQLException e) {
			e.printStackTrace();
			return fdt_list;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean InactivateProposal(Integer proposalId) {
		Statement statement = null;
		Connection connection = create_connection();
		char buyer_status = get_buyer_status(proposalId);
		boolean result = false;

		if (buyer_status == 'y') {
			try {
				String sql = "UPDATE ";
				sql += "\"Proposals\" ";
				sql += "SET \"buyer_status\"=";
				sql += "'i'";
				sql += " WHERE \"proposal_id\"=";
				sql += proposalId;
				System.out.println(sql);
				statement = connection.createStatement();
				int rs = statement.executeUpdate(sql);
				if (rs == 1) {
					result = true;
				} else {
					result = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println(buyer_status);
			result = false;
		}

		return result;
	}

	public char get_buyer_status(int proposal_id) {

		Statement statement = null;
		Connection connection = create_connection();
		char buyer_status = 0;

		try {
			String sql = "SELECT ";
			sql += "\"buyer_status\"";
			sql += " from \"Proposals\"";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			buyer_status = rs.getString(1).charAt(0);
			return buyer_status;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return buyer_status;
	}

	public List<ResponseTable> get_response(int proposal_id, int seller_id) {

		Statement statement = null;
		Connection connection = create_connection();
		List<ResponseTable> rt_list = new ArrayList<ResponseTable>();

		try {
			String sql = "SELECT";
			sql += " *";
			sql += " from \"Response\"";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			sql += " AND \"seller_id\"=";
			sql += seller_id;
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ResponseTable rt = new ResponseTable();
				int pid = rs.getInt(4);
				rt.setResponse_status((rs.getString(1)).charAt(0));
				rt.setSeller_id(rs.getInt(2));
				rt.setFeature_name(get_feature_name(rs.getInt(3), pid));
				rt.setProduct_name(get_product_name(pid));
				rt.setProposal_id(rs.getInt(5));
				rt_list.add(rt);
			}
			rs.close();
			return rt_list;
		} catch (SQLException e) {
			e.printStackTrace();
			return rt_list;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<ProposalsTable> view_proposals_by_status(char status) {

		Statement statement = null;
		List<ProposalsTable> pt_list = new ArrayList<ProposalsTable>();

		Connection connection = create_connection();
		System.out.println("entered view proposals");

		try {
			String sql = "SELECT ";
			sql += "proposal_id, bid_seller_id, description, buyer_status, contract_status, d_terms_id, p_terms_id";
			sql += " from \"Proposals\"";
			sql += " WHERE buyer_status=";
			sql += "'" + status + "'";

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ProposalsTable pt = new ProposalsTable();
				pt.setProposalId(rs.getInt(1));
				pt.setBidSellerId(rs.getInt(2));
				pt.setDescription(rs.getString(3));
				pt.setBuyerStatus((rs.getString(4)).charAt(0));
				pt.setContractStatus((rs.getString(5)).charAt(0));
				pt.setdTermsId(rs.getInt(6));
				pt.setpTermsId(rs.getInt(7));
				pt_list.add(pt);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pt_list;
	}

	public ProposalsTable get_proposal(Integer proposal_id) {
		Statement statement = null;
		ProposalsTable pt = new ProposalsTable();

		Connection connection = create_connection();
		System.out.println("entered get proposals");

		try {
			String sql = "SELECT ";
			sql += "proposal_id, bid_seller_id, description, buyer_status, contract_status, d_terms_id, p_terms_id";
			sql += " from \"Proposals\"";
			sql += " WHERE proposal_id=";
			sql += proposal_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				pt.setProposalId(rs.getInt(1));
				pt.setBidSellerId(rs.getInt(2));
				pt.setDescription(rs.getString(3));
				pt.setBuyerStatus((rs.getString(4)).charAt(0));
				pt.setContractStatus((rs.getString(5)).charAt(0));
				pt.setdTermsId(rs.getInt(6));
				pt.setpTermsId(rs.getInt(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println(pt.getDescription());
		return pt;
	}

	public List<ProductsTable> get_products(Integer proposal_id) {
		Statement statement = null;
		List<ProductsTable> pt_list = new ArrayList<ProductsTable>();

		Connection connection = create_connection();
		System.out.println("entered get products");

		try {
			String sql = "SELECT ";
			sql += "product_id, quantity, id";
			sql += " from \"Products\"";
			sql += " WHERE proposal_id=";
			sql += proposal_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ProductsTable pt = new ProductsTable();
				pt.setProductId(rs.getInt(1));
				pt.setQuantity(rs.getInt(2));
				pt.setId(rs.getInt(3));
				pt.setProposalId(proposal_id);
				pt_list.add(pt);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pt_list;

	}

	public String get_product_name(Integer product_id) {
		Statement statement = null;
		Connection connection = create_connection();
		String product_name = null;

		try {
			String sql = "SELECT ";
			sql += "\"Name\"";
			sql += " from \"Products_list\"";
			sql += " WHERE \"Product_id\"=";
			sql += product_id;
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			product_name = rs.getString(1);
			return product_name;
		} catch (SQLException e) {
			e.printStackTrace();
			return product_name;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<FeaturesTable> get_features(ProductsTable pt) {
		int p_id = pt.getProductId();
		int proposal_id = pt.getProposalId();

		Statement statement = null;
		List<FeaturesTable> ft_list = new ArrayList<FeaturesTable>();

		Connection connection = create_connection();
		System.out.println("entered get products");

		try {
			String sql = "SELECT ";
			sql += "priority_order, f_id";
			sql += " from \"Features\"";
			sql += " WHERE proposal_id=";
			sql += proposal_id;
			sql += " AND p_id=";
			sql += p_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				FeaturesTable ft = new FeaturesTable();
				ft.setPriorityOrder((rs.getString(1).charAt(0)));
				ft.setFeaturesId(rs.getInt(2));
				ft.setProductsId(p_id);
				ft.setProposalId(proposal_id);
				ft_list.add(ft);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ft_list;
	}

	public String get_feature_name(Integer feature_id, Integer product_id) {
		Statement statement = null;
		Connection connection = create_connection();
		String feature_name = null;

		try {
			String sql = "SELECT ";
			sql += "\"feature_name\"";
			sql += " from \"feature\"";
			sql += " WHERE \"product_id\"=";
			sql += product_id;
			sql += " AND \"feature_id\"=";
			sql += feature_id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			rs.next();
			feature_name = rs.getString(1);
			return feature_name;
		} catch (SQLException e) {
			e.printStackTrace();
			return feature_name;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<ProposalSellersBid> get_all_responses(Integer pid) {
		Statement statement = null;
		Connection connection = create_connection();
		List<ProposalSellersBid> psb_list = new ArrayList<ProposalSellersBid>();

		try {
			String sql = "SELECT * FROM ";
			sql += "\"Proposal_sellers_bid\" ";
			sql += " WHERE \"proposal_id\"=";
			sql += pid;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ProposalSellersBid psb = new ProposalSellersBid();
				psb.setSellerId(rs.getInt(2));
				psb.setCostAvail(rs.getInt(3));
				psb.setCostAvailCust(rs.getInt(4));
				psb.setSellerStatus((rs.getString(5)).charAt(0));
				psb.setScore(rs.getInt(6));
				psb.setBuyerBidStatus((rs.getString(7)).charAt(0));
				psb_list.add(psb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return psb_list;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return psb_list;
	}

	public boolean UpdateProposalsTable(Integer proposalid, String description) {
		Statement statement = null;
		Connection connection = create_connection();
		boolean result;

		try {
			String sql = "UPDATE \"Proposals\" ";
			sql += "SET \"description\"=";
			sql += "'" + description + "'";
			sql += " WHERE \"proposal_id\"=";
			sql += proposalid;

			statement = connection.createStatement();
			int rs = statement.executeUpdate(sql);

			if (rs == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public boolean UpdateProductTable(Integer id, Integer quantity) {
		Statement statement = null;
		Connection connection = create_connection();
		boolean result;

		try {
			String sql = "UPDATE \"Products\" ";
			sql += "SET \"quantity\"=";
			sql += quantity;
			sql += "WHERE \"id\"=";
			sql += id;
			System.out.println(sql);
			statement = connection.createStatement();
			int rs = statement.executeUpdate(sql);

			if (rs == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean UpdateFeatureTable(Integer fid, Integer id, Integer proposalid, String priorityOrder) {
		Statement statement = null;
		Connection connection = create_connection();
		boolean result;

		try {
			String sql = "SELECT \"product_id\"";
			sql += " FROM \"Products\"";
			sql += " WHERE \"id\"=";
			sql += id;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			int productid = rs.getInt(1);

			sql = "UPDATE \"Features\" ";
			sql += "SET \"priority_order\"=";
			sql += "'" + priorityOrder.charAt(0) + "'";
			sql += "WHERE \"f_id\"=";
			sql += fid;
			sql += " AND \"p_id\"=";
			sql += productid;
			sql += " AND \"proposal_id\"=";
			sql += proposalid;

			System.out.println(sql);
			statement = connection.createStatement();
			int a = statement.executeUpdate(sql);
			if (a == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public List<String> ListSellers(int proposalId) {

		List<String> listOfSellers = new ArrayList<String>();
		List<String> UniquelistOfSellers = null;
		Statement statement = null;
		Connection connection = create_connection();
		System.out.println("Entered into dao");
		try {
			String sql = "SELECT \"product_id\"";
			sql += " FROM \"Products\"";
			sql += " WHERE \"proposal_id\"=";
			sql += proposalId;

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				sql = "SELECT \"Name\"";
				sql += " FROM \"Products_list\"";
				sql += " WHERE \"Product_id\"=";
				sql += rs.getInt(1);
				statement = connection.createStatement();
				ResultSet rs2 = statement.executeQuery(sql);
				rs2.next();
				sql = "SELECT \"Username\"";
				sql += " FROM \"User_products\"";
				sql += " WHERE \"Products_name\"=";
				sql += "'" + rs2.getString(1) + "'";
				statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(sql);
				while (rs1.next()) {
					listOfSellers.add(rs1.getString(1));
					System.out.println(rs1.getString(1));
					System.out.println("sql :" + sql);
				}
				Set<String> unique_sellers = new HashSet<String>(listOfSellers);
				UniquelistOfSellers = new ArrayList<String>(unique_sellers);

				rs1.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return UniquelistOfSellers;

	}

	public void PublishProposal(IdentifiedSellers identifiedSellers, Integer proposalId) {

		List<Integer> userId = new ArrayList<Integer>();
		Statement statement = null;
		PreparedStatement pstatement = null;
		Connection connection = create_connection();
		try {
			for (String user : identifiedSellers.getSellers()) {
				String sql = "SELECT \"user_id\"";
				sql += " FROM \"user_id_name\"";
				sql += " WHERE \"Username\"=";
				sql += "'" + user + "'";

				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				System.out.println(sql);
				userId.add(rs.getInt(1));

			}
			for (Integer id : userId) {

				String sql = "INSERT into \"Proposal_sellers_bid\" (";
				sql += "proposal_id, seller_id, seller_status, buyer_bid_status";
				sql += ") VALUES (";
				sql += "?, ?, ?, ?";
				sql += ")";

				pstatement = connection.prepareStatement(sql);
				pstatement.setInt(1, proposalId);
				pstatement.setInt(2, id);
				pstatement.setString(3, "p");
				pstatement.setString(4, "n");
				System.out.println(sql);
				pstatement.executeUpdate();

			}

			String sql = "UPDATE \"Proposals\"";
			sql += " SET \"buyer_status\"=";
			sql += "'p'";
			sql += " WHERE \"proposal_id\"=";
			sql += proposalId;
			statement = connection.createStatement();
			int i = statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean award(int proposal_id, int seller_id) {

		Statement statement = null;
		Connection connection = create_connection();

		try {
			String sql = "UPDATE \"Proposals\"";
			sql += " SET \"buyer_status\"=";
			sql += "'c',";
			sql += " \"bid_seller_id\"=";
			sql += seller_id;
			sql += ", \"contract_status\"=";
			sql += "'y'";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			System.out.println(sql);
			statement = connection.createStatement();
			int a = statement.executeUpdate(sql);
			if (a != 1) {
				return false;
			}

			sql = "SELECT \"seller_id\", \"buyer_bid_status\" ";
			sql += " FROM \"Proposal_sellers_bid\"";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			System.out.println(sql);

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				int s_id = rs.getInt(1);
				if (s_id != seller_id) {
					sql = "UPDATE \"Proposal_sellers_bid\"";
					sql += " SET \"buyer_bid_status\"=";
					sql += "'r'";
					sql += " WHERE \"proposal_id\"=";
					sql += proposal_id;
					sql += " AND \"seller_id\"=";
					sql += s_id;
				} else {
					sql = "UPDATE \"Proposal_sellers_bid\"";
					sql += " SET \"buyer_bid_status\"=";
					sql += "'a'";
					sql += " WHERE \"proposal_id\"=";
					sql += proposal_id;
					sql += " AND \"seller_id\"=";
					sql += s_id;
				}
				System.out.println(sql);
				statement = connection.createStatement();
				int b = statement.executeUpdate(sql);
				if (b != 1) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;

	}

	public boolean reject(int proposal_id, int seller_id) {

		Statement statement = null;
		Connection connection = create_connection();

		try {
			String sql = "UPDATE \"Proposal_sellers_bid\"";
			sql += " SET \"buyer_bid_status\"=";
			sql += "'r'";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			sql += " AND \"seller_id\"=";
			sql += seller_id;

			System.out.println(sql);
			statement = connection.createStatement();
			int a = statement.executeUpdate(sql);
			if (a != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public List<PaymentTermsTable> get_payment_terms() {

		Statement statement = null;
		Connection connection = create_connection();
		List<PaymentTermsTable> ptt_list = new ArrayList<PaymentTermsTable>();

		try {
			String sql = "SELECT";
			sql += " *";
			sql += " from \"Payment_terms\"";
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				PaymentTermsTable ptt = new PaymentTermsTable();
				ptt.setId(rs.getInt(1));
				ptt.setName(rs.getString(2));
				ptt.setDescription(rs.getString(3));
				ptt_list.add(ptt);
			}
			rs.close();
			return ptt_list;
		} catch (SQLException e) {
			e.printStackTrace();
			return ptt_list;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<ProductCost> get_product_cost(int proposal_id, int seller_id) {

		Statement statement = null;
		Connection connection = create_connection();
		List<ProductCost> pc_list = new ArrayList<ProductCost>();

		try {
			String sql = "SELECT";
			sql += " \"id\", \"product_id\"";
			sql += " from \"Products\"";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				ProductCost pc = new ProductCost();
				int product_id = rs.getInt(1);
				String sql2 = "SELECT";
				sql2 += " \"cost\"";
				sql2 += " from \"product_cost_quot\"";
				sql2 += " WHERE \"product_id\"=";
				sql2 += product_id;
				sql2 += " AND \"seller_id\"=";
				sql2 += seller_id;
				System.out.println(sql2);
				Statement statement2 = connection.createStatement();
				ResultSet rs2 = statement2.executeQuery(sql2);
				rs2.next();
				pc.setPrice(rs2.getInt(1));
				pc.setProduct_name(productid_to_productname(rs.getInt(2)));
				rs2.close();
				pc_list.add(pc);
			}
			rs.close();
			return pc_list;
		} catch (SQLException e) {
			e.printStackTrace();
			return pc_list;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public String productid_to_productname(int product_id) {

		Statement statement = null;
		Connection connection = create_connection();

		try {
			String sql = "SELECT";
			sql += " \"Name\"";
			sql += " from \"Products_list\"";
			sql += " WHERE \"Product_id\"=";
			sql += product_id;
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			String a = rs.getString(1);
			rs.close();
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public int get_selected_seller(int proposal_id) {

		Statement statement = null;
		Connection connection = create_connection();

		try {
			String sql = "SELECT";
			sql += " \"bid_seller_id\"";
			sql += " from \"Proposals\"";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			int a = rs.getInt(1);
			rs.close();
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
public int get_available_cost(int proposal_id, int seller_id) {
		
		Statement statement = null;
		Connection connection = create_connection();
		int cost_a = 0;
		
		try {
			String sql = "SELECT \"cost\" FROM";
			sql += " \"Response\" ";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			sql += " AND \"seller_id\"=";
			sql += seller_id;
			sql += " AND \"response_status\"=";
			sql += "'A'";
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				cost_a += rs.getInt(1);
			}
			rs.close();
			return cost_a;
		} catch (SQLException e) {
			e.printStackTrace();
			return cost_a;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	public int get_available_customisable_cost(int proposal_id, int seller_id) {
		
		Statement statement = null;
		Connection connection = create_connection();
		int cost_ac = 0;
		
		try {
			String sql = "SELECT \"cost\" FROM";
			sql += " \"Response\" ";
			sql += " WHERE \"proposal_id\"=";
			sql += proposal_id;
			sql += " AND \"seller_id\"=";
			sql += seller_id;
			sql += " AND \"response_status\"!=";
			sql += "'N'";
			System.out.println(sql);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				cost_ac += rs.getInt(1);
			}
			rs.close();
			return cost_ac;
		} catch (SQLException e) {
			e.printStackTrace();
			return cost_ac;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
