package com.rbs.scm.teamb_contract1.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teamb_contract1.BusinessLogic.ViewResponseBL;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewResponse.ListOfResponses;
import com.rbs.scm.teamb_contract1.POJO.JsonParse.viewResponse.Response;

@Path("view")
public class ViewResponsesService {
	
	
	@Path("/all/responses/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ListOfResponses ViewAllResponses(@PathParam("pid") Integer pId) {
		
		System.out.println("all responses");
		return ViewResponseBL.ViewAllResponses(pId);
	}
	@Path("/response/{pid}/{sid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewResponse(@PathParam("pid") Integer pid, @PathParam("sid") Integer sid) {	
		return ViewResponseBL.viewResponse(pid, sid);
		
	}
}
