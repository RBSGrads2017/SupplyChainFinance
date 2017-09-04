package com.rbs.scm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.scm.model.ChartOfAccount;
import com.rbs.scm.model.GeneralLedger;
import com.rbs.scm.service.AccountingManagementService;

@Component
@RestController
@RequestMapping("/ACM")
public class AccountingManagementController {
	AccountingManagementService accountingManagementServiceObj = new AccountingManagementService();
	//@Autowired

	 @RequestMapping("/test")
	    public String service1() {
	        return "Hello, World!" ;
	    }
	 
	 @RequestMapping(value = "/viewGL",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<GeneralLedger>> ViewLedger(){
			List<GeneralLedger> generalledgerlists=accountingManagementServiceObj.getEachGLEntry();
			return new ResponseEntity<List<GeneralLedger>>(generalledgerlists, HttpStatus.OK);
		}
	 
	 @RequestMapping(value = "/viewGLBySearch",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public  ResponseEntity<List<GeneralLedger>> ViewLedgerBySearch(HttpServletRequest request,HttpServletResponse response){
			List<GeneralLedger> generalledgerlists=accountingManagementServiceObj.getEachGLEntryBySearch(request.getParameter("acEntryNo"),request.getParameter("transNo"),request.getParameter("custAcNo"),request.getParameter("swiftID"),request.getParameter("invoiceNo"),request.getParameter("drCr"),request.getParameter("paymentDate"),request.getParameter("dueDate"));
			return new ResponseEntity<List<GeneralLedger>>(generalledgerlists, HttpStatus.OK);
		}
	 
	@RequestMapping(value = "/viewCOAlist",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<ChartOfAccount>> dispCOAlist()
	 {
		 	List<ChartOfAccount> coaList=accountingManagementServiceObj.getCOAList();
		 	return new ResponseEntity<List<ChartOfAccount>>(coaList, HttpStatus.OK);
	 }
	
	 @RequestMapping(value = "/viewCOASINGLE",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public ResponseEntity<ChartOfAccount> dispCOA(HttpServletRequest request)
	 {
		 System.out.println("CONTROLLER----->SWIFTID:  "+request.getParameter("swiftID"));
		 	ChartOfAccount coa=accountingManagementServiceObj.getCOA(request.getParameter("swiftID"));
		
			System.out.println(coa.getBranch());
			return new ResponseEntity<ChartOfAccount>(coa, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/coaDelSingle",method = RequestMethod.POST)
	 @ResponseBody
	 public void delCOA(HttpServletRequest request,HttpServletResponse response)
	 {
		 /*System.out.println("CONTROLLER----->SWIFTID:  "+request.getParameter("swiftID"));
		 	ChartOfAccount coa=accountingManagementServiceObj.getCOA(request.getParameter("swiftID"));
		
			System.out.println(coa.getBranch());
			return new ResponseEntity<ChartOfAccount>(coa, HttpStatus.OK);*/
		 System.out.println("Inside delCOA");
		
		 accountingManagementServiceObj.deleteCOASingle(request.getParameter("swiftID"));
		 
	 }
	 
	 	 
	 @RequestMapping(value = "/addCOAContoller",method = RequestMethod.POST)
	 @ResponseBody
	 public void addCOAController(HttpServletRequest request,HttpServletResponse response)
	 {
		 System.out.println("inside addCOAController");
		 ChartOfAccount coa=new ChartOfAccount();
		 coa.setHead(request.getParameter("head"));
		 coa.setLegalEntity(request.getParameter("legalEntity"));
		 coa.setCountry(request.getParameter("country"));
		 coa.setBranch(request.getParameter("branch"));
		 coa.setProduct(request.getParameter("product"));
		 coa.setCurrency(request.getParameter("currency"));
		 coa.setBook(Integer.parseInt(request.getParameter("book")));
		 coa.setProductSwiftID(request.getParameter("productSwiftID"));
		 System.out.println(coa.toString());
		 System.out.println("Before calling add");
		 accountingManagementServiceObj.addCOAService(coa);
		 System.out.println("After calling add");
	 }
	 
	 @RequestMapping(value = "/CheckCompliance",method = RequestMethod.POST)
	 @ResponseBody
	 public String complianceCheck(HttpServletRequest request,HttpServletResponse response){
	
		 
			String resultString="";
			String individualCountry=request.getParameter("Countryname");
			String individualname=request.getParameter("individualname");
			
			List<String> lstCountries = new ArrayList<String>();
			List<String> lstNames = new ArrayList<String>();
			
			lstCountries = accountingManagementServiceObj.sanctionedCountries();
			lstNames =accountingManagementServiceObj.sanctionedIndividuals();
			
		int c1=	checkCountry(lstCountries, individualCountry);
		int c2=	checkName(lstNames, individualname);
			
		if(c1==0||c2==0)
		{
			resultString="Approval Denied. Under Sanctioned List";
		}
		else
			resultString="Approved";
			
			return resultString;
		}
	 public int checkCountry(List<String> lstCountries,String country){
				for(int i=0;i<lstCountries.size();i++)
				    if(country.equalsIgnoreCase((String) lstCountries.get(i)))
				    	return 0;
				return 1;
			}
			
	public int checkName(List<String> lstNames,String name){
				for(int i=0;i<lstNames.size();i++)
					if(name.equalsIgnoreCase((String) lstNames.get(i)))
					     return 0;
				return 1;
		}
	
	public double calculateAccruedIncomePerDay(Date paymentDate,Date dueDate,double amount){
		long noOfDays =((TimeUnit.MINUTES.convert(dueDate.getTime() - paymentDate.getTime(),TimeUnit.MILLISECONDS))/(60*24));
		double AccruedIncomePerDay = amount/noOfDays;
		return AccruedIncomePerDay;
	}
	
	public double calculateAccruedIncome(Date paymentDate,Date dueDate,double amount){
		AccountingManagementController x = new AccountingManagementController();
		double AccruedIncomePerDay = x.calculateAccruedIncomePerDay(paymentDate, dueDate, amount);
		long noOfDays =((TimeUnit.MINUTES.convert(new java.util.Date().getTime() - paymentDate.getTime(),TimeUnit.MILLISECONDS))/(60*24));
		double AccuredIIncome = AccruedIncomePerDay*noOfDays;
		return AccuredIIncome;
	} 
	
}