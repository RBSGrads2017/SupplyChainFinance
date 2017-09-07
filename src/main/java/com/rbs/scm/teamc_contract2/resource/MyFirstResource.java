package com.rbs.scm.teamc_contract2.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/resources")
public class MyFirstResource {
	@GET
	public String getOutput() {
		return "Hello";
	}
}
