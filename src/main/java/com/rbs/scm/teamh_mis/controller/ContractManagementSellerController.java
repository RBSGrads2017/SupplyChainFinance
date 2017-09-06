package com.rbs.scm.teamh_mis.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.scm.teamh_mis.model.Addn_response;
import com.rbs.scm.teamh_mis.model.Buyer;
import com.rbs.scm.teamh_mis.model.PaymentandDeliveryDetails;
import com.rbs.scm.teamh_mis.model.Proposal_Sellers_Bid;
import com.rbs.scm.teamh_mis.model.Proposal_Sellers_Bid_Proposals;
import com.rbs.scm.teamh_mis.model.Sfeatures;
import com.rbs.scm.teamh_mis.service.ContractManagementSellerService;


/*service to fetch all the request for proposals(rfps)*/

/*service 1*/
@Component
@RestController
@RequestMapping("/contractmanagementseller")
public class ContractManagementSellerController {
	 ContractManagementSellerService service = new ContractManagementSellerService();
    
	/*service 1*/
	 @RequestMapping(value = "/viewrfp/{seller_id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal_Sellers_Bid_Proposals>> getProposals(@PathVariable("seller_id") String seller_id) {
		
		//int seller_id = Integer.parseInt(id);
		
			List<Proposal_Sellers_Bid_Proposals> proposals = new ArrayList<Proposal_Sellers_Bid_Proposals>();
			try {
				proposals = service.listAllProposals(seller_id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if (proposals.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Proposal_Sellers_Bid_Proposals>>(proposals, HttpStatus.OK);
			
		}
    
	 /*service 2*/
	 /* function to list a particular proposal if the proposal id is passed from proposal table
	  */
	 
	 @RequestMapping(value="/listfeatures/{proposal_id}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Sfeatures>> getFeatures(@PathVariable("proposal_id") String propid) {
	        int proposal_id = Integer.parseInt(propid);
			List<Sfeatures> featureslist= new ArrayList<Sfeatures>();
			try {
				featureslist = service.getFeaturesforaproduct(proposal_id);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			if(featureslist.isEmpty()){
				//return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Sfeatures>>(featureslist, HttpStatus.OK);
	    }
						
	 /*service 3
	 FUNCTION TO UPDATE SELLER'S RESPONSE TO THE FEATURES 
	 OF EVERY PRODUCT IN THE PRODUCT= WITH A/C/N RADIO BUTTONS
				*/
	
    @RequestMapping("/updatesellerresponse/{proposal_id}/{product_id}/{feature_id}/{seller_id}/{response}")
    public void updateresponse(@PathVariable("proposal_id") String propid,@PathVariable("product_id") String prodid, @PathVariable("feature_id") String fid,@PathVariable("seller_id") String sid, @PathVariable("response") String response) throws ClassNotFoundException, SQLException {
        int proposalid = Integer.parseInt(propid);
        int productid = Integer.parseInt(prodid);
        int featureid = Integer.parseInt(fid);
        int sellerid = Integer.parseInt(sid);
        service.updatesellerresponse(productid,proposalid,featureid,sellerid, response);
      
    }
    
    /* SERVICE 4 service to update the seller's status(accept/reject/later) in the proposal_sellers_bid table 
  	*/
    
    @RequestMapping(value = "/updatebidsellerstatus/{seller_id}/{proposal_id}/{seller_status}")
     public void updatebidsellerstats(@PathVariable("seller_id") String id,
    	@PathVariable("proposal_id") String pid,@PathVariable("seller_status") String sts){
    	
    	int seller_id = Integer.parseInt(id);
    	int proposal_id=Integer.parseInt(pid);
    	try {
    		//ContractManagementSellerService cs = new ContractManagementSellerService();
			 service.updateBidSellerStatus(seller_id, proposal_id, sts);
			 System.out.println("i came out");
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
    }
    
   /*service 5*/
   /*VIEW STATUS OF SENT PROPOSALS
  service to fetch the buyer's status for the proposals 
	 which the seller has already accepted*/
	 
    @RequestMapping(value="/fetchbuyerStatus/{seller_id}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Proposal_Sellers_Bid>> getBuyerStatus(@PathVariable("seller_id") String sid) {
        int seller_id = Integer.parseInt(sid);
		List<Proposal_Sellers_Bid> buyerstatuslist= new ArrayList<Proposal_Sellers_Bid>();
		try {
			buyerstatuslist = service.fetchBuyerStatus(seller_id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(buyerstatuslist.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Proposal_Sellers_Bid>>(buyerstatuslist, HttpStatus.OK);
    }
     
    
    /*service to send additional response-
     * service 6*/
    @RequestMapping(value = "/addresponse/{seller_id}/{product_id}/{specification}",method = RequestMethod.POST)
    
    public void additionalResponse(@PathVariable("seller_id") String id,
    		@PathVariable("product_id") String pid,@PathVariable("specification") String sps){
    	
    	int seller_id = Integer.parseInt(id);
    	int product_id=Integer.parseInt(pid);
    	try {
    		
			 service.addnresp(seller_id, product_id, sps);
			 
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
    }
    
    
    /*service 7 fetch add responses*/
    
    @RequestMapping(value = "/viewaddresp/{seller_id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Addn_response>> getAddResponses(@PathVariable("seller_id") String id) {
		
		int seller_id = Integer.parseInt(id);
		
			List<Addn_response> addresponses = new ArrayList<Addn_response>();
			try {
				addresponses = service.listAllAddResponses(seller_id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if (addresponses.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Addn_response>>(addresponses, HttpStatus.OK);
			
		}
    
   /* view buyer details service 8*/
    
    @RequestMapping(value = "/buyerdetails/{proposal_id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Buyer>> getBuyerdet(@PathVariable("proposal_id") String id) {
		
		int proposal_id = Integer.parseInt(id);
		
		
			List<Buyer> budet = new ArrayList<Buyer>();
			try {
				budet = service.buyerDet(proposal_id);
				System.out.println("i came out");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if (budet.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Buyer>>(budet, HttpStatus.OK);
			
		}
    /*payment and delivery details service 9*/
    
    @RequestMapping(value = "/payanddeliverydetails/{proposal_id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentandDeliveryDetails>> getPandDdet(@PathVariable("proposal_id") String id) {
		
		int proposal_id = Integer.parseInt(id);
		System.out.println("panni");
			List<PaymentandDeliveryDetails> pandd = new ArrayList<PaymentandDeliveryDetails>();
			try {
				pandd = service.pAndDdetails(proposal_id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if (pandd.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<PaymentandDeliveryDetails>>(pandd, HttpStatus.OK);
			
		}
}
  