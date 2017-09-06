package com.rbs.scm;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.rbs.scm.teame_funding.controller.db.FundingDAOimpl;
import com.rbs.scm.teame_funding.controller.services.Fetch;
import com.rbs.scm.teame_funding.dao.Business;
import com.rbs.scm.teame_funding.model.pojos.Invoice;
import com.rbs.scm.teame_funding.model.pojos.PurchaseOrder;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("")
public class FetchLibor {

	/*
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@Path("fetchlibor")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		Fetch f = new Fetch();
		String s = f.fetch();
		System.out.print(s);
		return s;
	}

	/*@Path("/sessioncheck")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sessionId(@CookieParam("SessionId") Cookie sessionId, @Context HttpServletRequest request) //var- session id
			{
				String userid;
				HttpSession sess = request.getSession(false);
				if(sess==null)
				{
					userid = "1";
				}
				return "success";
			}*/

	@Path("invoice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//public List<InvoicePlusPoPojo> InvoiceList(@QueryParam("Buyer_name") String buyer_name, @QueryParam("Seller_name") String seller_name)
	public List<Invoice> InvoiceList()
	{
		String buyer_name="shreya";
		String seller_name="shivam";
		System.out.println("hellooo");
//		InvoiceList inv=new InvoiceList(buyer_name, seller_name);
//		List<Invoice> p= inv.runInvoiceQuery(); //List<InvoiceListPojo>
		
		FundingDAOimpl db = new FundingDAOimpl();
		List<Invoice> invoices = db.view_invoices(buyer_name, seller_name);

		for(Invoice invoice:invoices)
		{
			System.out.println("test3");
			System.out.println(invoice.getInvoiceDueDate());
			System.out.println(invoice.getInvoiceAmount());
		}

		return invoices;
	}

//	@Path("mainpath")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public boolean checkuser()
//	{
//		FundingDAOimpl f=new FundingDAOimpl();
//		boolean a= f.checkuser();
//		return a;
//	}
	
	
	
	@Path("purchase_orders_list_seller")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseOrder> retrievePO(@QueryParam("Username") String username)
	{ 
		FundingDAOimpl f=new FundingDAOimpl();
		List<PurchaseOrder> a= f.getPOList(username);
		return a;
	}

	@Path("purchase_order_list_bank_user")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseOrder> getPOdata(){
		FundingDAOimpl f=new FundingDAOimpl();
		return  f.getPOList(null);
	}
	
	@Path("processPO")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getProcessData(@QueryParam("Currency") String currency, @QueryParam("Dates") String Dates){
		Business b=new Business();
		b.setDiscount_rate(currency, Dates);
		return Double.toString(b.getDiscount_rate());
		
	}
	
	@Path("createPO")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean newPO(PurchaseOrder po, @Context HttpServletResponse servletResponse) {
		
		
		
		try {
			servletResponse.sendRedirect("../purchase_order_list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}