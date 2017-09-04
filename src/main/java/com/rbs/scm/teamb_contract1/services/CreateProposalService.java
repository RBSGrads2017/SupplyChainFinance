package com.rbs.scm.teamb_contract1.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import com.rbs.scm.teamb_contract1.BusinessLogic.CreateProposalBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.createProposal.Proposal;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("create")
public class CreateProposalService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET @Path("/1")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
    @POST
	@Path("/proposal")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createProposalClass(Proposal p/*,@Context HttpServletRequest httpRequest*/) {
    	/*HttpSession ses = httpRequest.getSession(false);
    	User usr =  (User)ses.getAttribute("user");*/
    	
    	
		boolean creationStatus = CreateProposalBL.create(p);
		if(creationStatus==true)
			return "<p>Proposal created !!</p>";
		
		else
		return "<p>Error occured</p>";
	}
    
    
}
