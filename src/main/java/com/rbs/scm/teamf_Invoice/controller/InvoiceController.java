package com.rbs.scm.teamf_Invoice.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rbs.scm.teamf_Invoice.controller.*;
import com.rbs.scm.teamf_Invoice.model.*;
import com.rbs.scm.teamf_Invoice.service.*;
import com.rbs.scm.teamf_Invoice.util.*;

@Component
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	InvoiceService invoiceServiceObj = new InvoiceService();
	//@Autowired
	//InvoiceService invoiceServiceObj;
	
// List all the invoices by id
		
	@RequestMapping(value = "/viewInvoices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Invoice>> listAllInvocies(@PathParam("sellerID") double sellerID) {
		System.out.println("id is"+sellerID);
			List<Invoice> invoices = invoiceServiceObj.listAllInvocies(sellerID);
			if (invoices.isEmpty()) {
				return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
	
	@RequestMapping(value = "/SentInvoices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> listSentInvoices(@PathParam("sellerID") double sellerID) {
	System.out.println("id is"+sellerID);
		List<Invoice> invoices = invoiceServiceObj.listSentInvoices(sellerID);
		if (invoices.isEmpty()) {
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	@RequestMapping(value = "/ReceivedInvoices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> listReceivedInvoices(@PathParam("sellerID") double sellerID) {
	System.out.println("id is"+sellerID);
		List<Invoice> invoices = invoiceServiceObj.listReceivedInvoices(sellerID);
		if (invoices.isEmpty()) {
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	@RequestMapping(value = "/approvedInvoices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Invoice>> listApprovedInvoices(@PathParam("approvalStatus") int approvalStatus) {
		System.out.println("id is"+approvalStatus);
			List<Invoice> invoices = invoiceServiceObj.approvedInvocies(approvalStatus);
			if (invoices.isEmpty()) {
				return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
	@RequestMapping(value = "/listProducts", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductInvoice>> listProducts() {
		List<ProductInvoice> invoices = invoiceServiceObj.listProducts();
		if (invoices.isEmpty()) {
			return new ResponseEntity<List<ProductInvoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductInvoice>>(invoices, HttpStatus.OK);
	}
	@RequestMapping(value = "/draftInvoices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> listDraftInvoices(@PathParam("sellerID") double sellerID) {
	System.out.println("id is"+sellerID);
		List<Invoice> invoices = invoiceServiceObj.listDraftInvoices(sellerID);
		if (invoices.isEmpty()) {
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	@RequestMapping(value = "/sendInvoice", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomMessage listAllUsers12(@PathParam("invoiceID") int invoiceID) {
	
		CustomMessage s=invoiceServiceObj.sendInvoice(invoiceID);
	return s;
	
	}
	
	@RequestMapping(value = "/approveInvoice", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomMessage listAllUsers123(@PathParam("invoiceID") int invoiceID) {
	
	CustomMessage s=invoiceServiceObj.approveInvoice(invoiceID);
	return s;
	
	}
	@RequestMapping(value = "/rejectInvoice", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomMessage listAllUsers1234(@PathParam("invoiceID") int invoiceID) {
	
	CustomMessage s=invoiceServiceObj.rejectInvoice(invoiceID);
	return s;
	
	}
	
	@RequestMapping(value = "/searchInvoice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> searchInvoice(@PathParam("invoiceID") double invoiceID) throws ClassNotFoundException, SQLException {
		System.out.println("Getting Invoice with Invoice no"+ invoiceID);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		
		Invoice invoiceObj = invoiceServiceObj.search(invoiceID);
		if(invoiceObj == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"is not found");
			return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.OK);
    }
	@RequestMapping(value = "/addInvoice",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<CustomMessage> addInvoice(@PathParam("invoiceID") double invoiceID,@PathParam("productID") double productID,@PathParam("quantity") double quantity,@PathParam("contractID") double contractID,@PathParam("sellerID") double sellerID,@PathParam("buyerID") double buyerID,@PathParam("senderID") double senderID,@PathParam("receiverID") double receiverID,@PathParam("billbookNo") double billbookNo,@PathParam("invoiceCreatedDate") Date invoiceCreatedDate,@PathParam("paymentDate") Date paymentDate,@PathParam("invoiceAmount") float  invoiceAmount,@PathParam("invoiceDueDate") Date invoiceDueDate) throws ClassNotFoundException, SQLException {
		System.out.println("Adding Invoice with Invoice no"+ invoiceID);
		
		CustomMessage msg = invoiceServiceObj.addInvoice(invoiceID,contractID,productID,quantity,sellerID,buyerID,billbookNo,senderID,receiverID,paymentDate,invoiceAmount,invoiceDueDate); 
			if(msg == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"cannot be added");
			return new ResponseEntity<CustomMessage>(msg,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomMessage>(msg,HttpStatus.OK);
   }
	
	@RequestMapping(value = "/viewProduct", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceItems>> listAllUsers(@PathParam("id") int id) {
	System.out.println("id is"+id);
		List<InvoiceItems> invoicesItems = invoiceServiceObj.viewProduct(id);
		if (invoicesItems.isEmpty()) {
			return new ResponseEntity<List<InvoiceItems>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InvoiceItems>>(invoicesItems, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getItemDetails",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	
	 public ResponseEntity<InvoiceItems> getItemDetails(@PathParam("invoiceID") int invoiceID,@PathParam("productID") int productID,@PathParam("quantity") int quantity) throws ClassNotFoundException, SQLException {
		System.out.println("getting item details");
		
		InvoiceItems invoiceObj=invoiceServiceObj.getItemDetails(invoiceID,productID,quantity);
		if(invoiceObj == null){
			System.out.println("item is not found");
			return new ResponseEntity<InvoiceItems>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InvoiceItems>(invoiceObj,HttpStatus.OK);
  }
	
	@RequestMapping(value = "/addItems",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	
	 public ResponseEntity<InvoiceItems> addItems(@PathParam("invoiceID") int invoiceID,@PathParam("productID") int productID,@PathParam("quantity") int quantity,@PathParam("grossAmount") double grossAmount,@PathParam("tax") float tax,@PathParam("netAmount") double  netAmount) throws ClassNotFoundException, SQLException {
		System.out.println("Adding Items with Invoice no"+ invoiceID+"and ProductID"+productID);
		
		InvoiceItems invoiceObj=invoiceServiceObj.addItems(invoiceID,productID,quantity,grossAmount,tax,netAmount);
		if(invoiceObj == null){
			System.out.println("Item with Invoice No"+invoiceID+"and productID "+productID+"is not found");
			return new ResponseEntity<InvoiceItems>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InvoiceItems>(invoiceObj,HttpStatus.OK);
  }
	
	@RequestMapping(value = "/deleteItem",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomMessage> deleteInvoice(@PathParam("invoiceID") int invoiceID,@PathParam("productID") int productID) throws ClassNotFoundException, SQLException {
		System.out.println("delete Item with Invoice no"+ invoiceID+" and productID "+productID);
		CustomMessage msg = invoiceServiceObj.deleteItem(invoiceID,productID);
		if(msg == null){
			System.out.println("Invoice with Invoice No"+invoiceID+" and productID "+productID+"is not found");
			return new ResponseEntity<CustomMessage>(msg,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomMessage>(msg,HttpStatus.OK);
    }
	
	@RequestMapping(value = "/deleteInvoice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomMessage> deleteInvoice(@PathParam("invoiceID") double invoiceID) throws ClassNotFoundException, SQLException {
		System.out.println("delete Invoice with Invoice no"+ invoiceID);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		
		CustomMessage msg = invoiceServiceObj.deleteInvoice(invoiceID);
		if(msg == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"is not found");
			return new ResponseEntity<CustomMessage>(msg,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomMessage>(msg,HttpStatus.OK);
    }
	
	
	
	
	private static String s;
	@PostMapping("/upload")
	public String uploadInvoice(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
		String fileExtention1 = ".xlsx";
		String fileExtention2=".xls";
		String fileName = file.getOriginalFilename();
		int lastIndex = fileName.lastIndexOf('.');
		String substring = fileName.substring(lastIndex, fileName.length());
		System.out.println("extension ="+substring);
		if(fileExtention1.equals(substring) ||fileExtention2.equals(substring))
		{s=invoiceServiceObj.uploadInvoice(file, redirectAttributes);
		String res=invoiceServiceObj.processInvoice(s);
		return res;
		}
		else
			return "not an excel file";
	}
	@RequestMapping(value="/insertAll",method = RequestMethod.GET,produces=MediaType.ALL_VALUE)
	public String insertAll()
	{
		String t=invoiceServiceObj.insertAll(s);
		return t;
	
	}
	
	/*@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param) throws IOException {

		File file = new File("C:\\temp\\test.xlsx");
		 HttpHeaders headers = new HttpHeaders();
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");
	    Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

	    return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(resource);
	}*/
	@RequestMapping(value = "/getContractNos",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contract>> getContractNos(@PathParam("sellerID") int sellerID)
	{
		List<Contract> invoicesItems = invoiceServiceObj.getContractNos(sellerID);
		if (invoicesItems.isEmpty()) {
			return new ResponseEntity<List<Contract>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contract>>(invoicesItems, HttpStatus.OK);
	
	}
	@RequestMapping(value = "/getContractNo",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contract> getContractNo(@PathParam("sellerID") int sellerID)
	{
		Contract invoicesItems = invoiceServiceObj.getContractNo(sellerID);
		if (invoicesItems==null) {
			return new ResponseEntity<Contract>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Contract>(invoicesItems, HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/getInvoicesForUpdate", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> listAllInvocies1(@PathParam("sellerID") double sellerID) {
	System.out.println("id is"+sellerID);
		List<Invoice> invoices = invoiceServiceObj.listAllInvocies1(sellerID);
		if (invoices.isEmpty()) {
			return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	@RequestMapping(value = "/getContractItems",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ContractItems>> getContractItems(@PathParam("sellerID") int sellerID)
	{
		List<ContractItems> invoicesItems = invoiceServiceObj.getContractItems(sellerID);
		if (invoicesItems.isEmpty()) {
			return new ResponseEntity<List<ContractItems>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ContractItems>>(invoicesItems, HttpStatus.OK);
	
	}
	@RequestMapping(value = "/getProductDetails",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductInvoice> getProductDetails(@PathParam("invoiceID") int invoiceID) throws ClassNotFoundException, SQLException {
		System.out.println("Getting Invoice with Invoice no"+ invoiceID);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		
		ProductInvoice invoiceObj = invoiceServiceObj.getProductDetails(invoiceID);
		if(invoiceObj == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"is not found");
			return new ResponseEntity<ProductInvoice>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductInvoice>(invoiceObj,HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getCPG",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contract> getContractNo1(@PathParam("sellerID") int sellerID)
	{
		Contract invoicesItems = invoiceServiceObj.getContractNoPg(sellerID);
		if (invoicesItems==null) {
			return new ResponseEntity<Contract>(invoicesItems,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Contract>(invoicesItems, HttpStatus.OK);
	
	}
	@RequestMapping(path = "/getNextInvoiceNo", method = RequestMethod.GET)
	public ResponseEntity<Invoice> download1() {
	Invoice n=new Invoice();
	CustomMessage msg= null;
	n=invoiceServiceObj.getNextInvoiceNo();
	return new ResponseEntity<Invoice>(n,HttpStatus.OK);
	}
}
