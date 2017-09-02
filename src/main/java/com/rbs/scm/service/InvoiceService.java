package com.rbs.scm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rbs.scm.DAO.DataBaseConnection;
import com.rbs.scm.model.Invoice;
import com.rbs.scm.model.InvoiceItems;
import com.rbs.scm.model.ProductInvoice;
import com.rbs.scm.util.CustomMessage;

@Service("invoiceServiceObj")
public class InvoiceService {
	public Invoice search(double InvoiceID){
		DataBaseConnection dbobj = new DataBaseConnection();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where invoice_id='"+InvoiceID+"' and deletestatus=0");
				invobj = new Invoice();
				//System.out.println(rs.toString());
				if(rs.next()==false) {
					
				}
				else{
					invobj.setInvoiceID(rs.getDouble(1)); 
					do{
				
					//if(rs.wasNull())
						//System.out.println("empty");
					
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
			}while(rs.next());
		}
			}
			catch(Exception e)
			{							
				System.out.println(e);
			}
		return invobj;			
	}
	
	public List<Invoice> listAllInvocies(double sellerID){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where sellerid=? and deletestatus=0");
				stmt.setDouble(1,sellerID);
				ResultSet rs = stmt.executeQuery();				
				
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
					lst.add(invobj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;			
	}
	public List<Invoice> approvedInvocies(int approvalStatus){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where approvalStatus=? and deletestatus=0 and draftStatus=0");
				stmt.setInt(1,approvalStatus);
				ResultSet rs = stmt.executeQuery();				
				invobj = new Invoice();
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
					lst.add(invobj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;			
	}
	public List<Invoice> listSentInvoices(double sellerID) {
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where approvalStatus=0 and deletestatus=0 and senderid=? and draftStatus=0");
				stmt.setDouble(1,sellerID);
				ResultSet rs = stmt.executeQuery();				
				invobj = new Invoice();
				System.out.println(rs.toString());
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
					lst.add(invobj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;	
	}

	public List<Invoice> listReceivedInvoices(double sellerID) {
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where receiverid=? and deletestatus=0 and approvalStatus=0 and draftStatus=0");
				stmt.setDouble(1,sellerID);
				ResultSet rs = stmt.executeQuery();				
				invobj = new Invoice();
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
					lst.add(invobj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;	
	}
	public List<Invoice> listDraftInvoices(double sellerID) {
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where sellerid=? and deletestatus=0 and draftStatus=1");
				stmt.setDouble(1,sellerID);
				ResultSet rs = stmt.executeQuery();				
				invobj = new Invoice();
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
					lst.add(invobj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;	
	}
	
	public CustomMessage addInvoice(double invoiceID,double contractID,double productID,double quantity, double sellerID, double buyerID, double billbookNo,double senderID,double receiverID, Date paymentDate, float invoiceAmount,Date invoiceDueDate) {
		DataBaseConnection dbobj = new DataBaseConnection();
		Invoice invobj = null;
		CustomMessage msg = null; 
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
				invobj = new Invoice();
				
				//double =invobj.invoiceNo++;
				//String p="insert into invoice(invoice_id) value(22)";
				//String updateTableSQL1 ="insert into invoice(invoice_id,contractID,sellerID,buyerID, billbookNo,senderID,receiverID,Payment_Date,invoice_amount,invoice_due_date) values(?,?,?,?,?,?,?,NULL,?,NULL)";
				/*stmt.executeQuery("insert into invoice values("+invoiceNo+","+contractNo+","+buyerId+","+sellerId+","
				+productId+","+unitPrice+","+quantity+","+grossAmount+","+tax+","+netAmount+","+"0,1,0");*/
			    
				String updateTableSQL1 ="insert into invoice values(" +invoiceID +","+contractID+","+sellerID+","+buyerID+","+billbookNo+","+senderID+","+receiverID+","+"0,0,1,NULL,NULL"+","+invoiceAmount+","+ "NULL,1,0,NULL )";
				System.out.println(updateTableSQL1);
				PreparedStatement preparedStatement  = con.prepareStatement(updateTableSQL1);
				//preparedStatement.setDouble(1, invoiceID);preparedStatement.setDouble(2,contractID);preparedStatement.setDouble(3,sellerID);preparedStatement.setDouble(4,buyerID);preparedStatement.setDouble(5,billbookNo);preparedStatement.setDouble(6,senderID);preparedStatement.setDouble(7,receiverID);preparedStatement.setDouble(9,invoiceAmount);
				preparedStatement.executeUpdate();
				
				System.out.println("Record is inserted to DBUSER table!");
				String updateTableSQL2 = "COMMIT";
				//preparedStatement = con.prepareStatement(p);
				//preparedStatement.executeUpdate();
				preparedStatement = con.prepareStatement(updateTableSQL2);
				preparedStatement.executeUpdate();
				msg.setMessage("Succesfully Updated");
				
				
				InvoiceItems i= getItemDetails((int)invoiceID,(int)productID,(int)quantity);
				 addItems((int)invoiceID,(int)productID,(int)quantity,i.getGrossAmount(),i.getTax(),i.getNetAmount());
						
				con.close();
				
					System.out.println(msg.getMessage());
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
				msg.setMessage("Adding Invocie Failed due to:"+e.getMessage());
			}
		return msg;			
}
	
		public List<InvoiceItems> viewProduct(int id){
			DataBaseConnection dbobj = new DataBaseConnection();
			List<InvoiceItems> lst = new ArrayList<InvoiceItems>();
			InvoiceItems itemobj = null;
			try{
				Connection con = dbobj.getConnection();
					PreparedStatement stmt = con.prepareStatement("select * from invoiceitems where invoice_id=? and (select deletestatus from invoice where invoice_id=?)=0");
					stmt.setInt(1,id);
					stmt.setInt(2,id);
					ResultSet rs = stmt.executeQuery();				
					
					while(rs.next()){
						itemobj = new InvoiceItems();
						itemobj.setItemNo(rs.getInt(1)); 
						itemobj.setInvoiceID(rs.getInt(2));
						itemobj.setProductID(rs.getInt(3));
						itemobj.setQuantity(rs.getInt(4));
						itemobj.setGrossAmount(rs.getFloat(5));
						itemobj.setTax(rs.getFloat(6));
						itemobj.setNetAmount(rs.getFloat(7));
						lst.add(itemobj);
				}
				}
				catch(Exception e)
				{							
					System.out.println(e.getMessage());
				}
			return lst;			
		}
		
		
		public InvoiceItems getItemDetails(int invoiceID, int productID, int quantity) {
			DataBaseConnection dbobj = new DataBaseConnection();
			InvoiceItems itemobj = null;
			CustomMessage msg = null; 
			try{
				Connection con = dbobj.getConnection();
				msg = new CustomMessage();
					itemobj = new InvoiceItems();
					
					PreparedStatement preparedStatement  = con.prepareStatement("select unitprice,tax from Product where product_id=?");
					preparedStatement.setInt(1,productID);
					ResultSet rs=preparedStatement.executeQuery();
					double unitPrice=0;
					float tax=0;
					while(rs.next())
					{
						unitPrice=rs.getDouble(1);
						tax= rs.getFloat(2);
							
					}
					double grossAmount=quantity*unitPrice;

					System.out.println("gross"+grossAmount);
					
					float grossTax=(float)(grossAmount*(tax/100));
					double netAmount=grossAmount+grossTax;
					System.out.println("grossAmount="+grossAmount);
							System.out.println("tax="+grossTax);
						con.close();
						itemobj.setInvoiceID(invoiceID);
						itemobj.setProductID(productID);
						itemobj.setTax(grossTax);
						itemobj.setUnitprice(unitPrice);
						itemobj.setQuantity(quantity);
						itemobj.setGrossAmount(grossAmount);
						itemobj.setNetAmount(netAmount);
						System.out.println(itemobj.toString());
						System.out.println(msg.getMessage());
				}
				catch(Exception e)
				{							
					System.out.println(e.getMessage());
				}
			return itemobj;			
	}	
		
		
		public void updateInvoiceAmount(int invoiceID)
		{
			DataBaseConnection dbobj = new DataBaseConnection();
			InvoiceItems itemobj = null;
			CustomMessage msg = null; 
			try{
				Connection con = dbobj.getConnection();
				msg = new CustomMessage();
					itemobj = new InvoiceItems();
					
					PreparedStatement preparedStatement  = con.prepareStatement("select sum(net_amount) from invoiceitems where invoice_id=? and(select deletestatus from invoice where invoice_id=?)=0");
					preparedStatement.setInt(1,invoiceID);
					preparedStatement.setInt(2,invoiceID);
					
					ResultSet rs=preparedStatement.executeQuery();
					double sum=0;
					while(rs.next())
					{
						sum=rs.getDouble(1);
						System.out.println("sum="+sum);
					}
					PreparedStatement preparedStatement1  = con.prepareStatement("update invoice set invoice_amount=? where invoice_id=? and deletestatus=0");
					preparedStatement1.setDouble(1, sum);
					preparedStatement1.setInt(2,invoiceID);
					preparedStatement1.executeUpdate();
					
					PreparedStatement preparedStatement2  = con.prepareStatement("commit");
					
					preparedStatement2.executeUpdate();
					}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		public InvoiceItems addItems( int invoiceID,int productId,double quantity, double grossAmount, float tax,double netAmount) {
					DataBaseConnection dbobj = new DataBaseConnection();
					InvoiceItems itemobj = null;
					CustomMessage msg = null; 
					try{
						Connection con = dbobj.getConnection();
						msg = new CustomMessage();
							itemobj = new InvoiceItems();
							
							InvoiceItems in=new InvoiceItems();
									in=getItemDetails(invoiceID,productId,(int)quantity);
								
							String updateTableSQL1 ="insert into invoiceitems (invoice_id,product_id,quantity,gross_amount,tax,net_amount) values("+invoiceID+",'"
									+productId+"',"+quantity+","+in.getGrossAmount()+","+in.getTax()+","+in.getNetAmount()+")";
							
							System.out.println(updateTableSQL1);
							PreparedStatement preparedStatement  = con.prepareStatement(updateTableSQL1,Statement.RETURN_GENERATED_KEYS);
							
							preparedStatement.executeUpdate();
							updateInvoiceAmount(invoiceID);
							/*
		                     ResultSet tableKeys = preparedStatement.getGeneratedKeys();
		                     tableKeys.next();
		                     int itemNo = tableKeys.getInt(1);
							*/
							String updateTableSQL2 = "COMMIT";
							preparedStatement = con.prepareStatement(updateTableSQL2);
							preparedStatement.executeUpdate();
							msg.setMessage("Succesfully added item");
								con.close();
								itemobj.setInvoiceID(invoiceID);
								itemobj.setProductID(productId);
								itemobj.setTax(tax);
								itemobj.setQuantity(quantity);
								itemobj.setGrossAmount(grossAmount);
								itemobj.setNetAmount(netAmount);
						
								System.out.println(itemobj.toString());
								System.out.println(msg.getMessage());
						}
						catch(Exception e)
						{							
							System.out.println(e.getMessage());
						}
					return itemobj;			
			}

			
			
	public CustomMessage deleteItem(int invoiceNo ,int productID) {
			
			
			DataBaseConnection dbobj = new DataBaseConnection();
			CustomMessage msg=null;
			
			try{
				Connection con = dbobj.getConnection();
				msg = new CustomMessage();
				PreparedStatement stmt=con.prepareStatement("delete from invoiceitems where invoice_id  =? and Product_id=?");
				stmt.setInt(1,invoiceNo);
				stmt.setInt(2,productID);
				stmt.executeUpdate();
				String updateTableSQL3 = "COMMIT";
				stmt = con.prepareStatement(updateTableSQL3);
				stmt.executeUpdate();
				updateInvoiceAmount(invoiceNo);
				String updateTableSQL2 = "COMMIT";
				stmt = con.prepareStatement(updateTableSQL2);
				stmt.executeUpdate();
				msg.setMessage("successfully deleted");
				
			}
			catch(Exception e){
				msg.setMessage(e.getMessage());
				System.out.println(e.getMessage());
				}
			return msg;
		}		
	public List<ProductInvoice> listProducts(){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<ProductInvoice> lst = new ArrayList<ProductInvoice>();
		ProductInvoice itemobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from product");
				ResultSet rs = stmt.executeQuery();				
				
				while(rs.next()){
					itemobj = new ProductInvoice();
					itemobj.setProductID(rs.getInt(1));
					itemobj.setProductName(rs.getString(2));
					itemobj.setUnitPrice(rs.getInt(3));
					itemobj.setTax(rs.getFloat(4));
					itemobj.setDescription(rs.getString(5));
					lst.add(itemobj);
				}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;			
	}
	public CustomMessage deleteInvoice(double invoiceID) {
		//insert your logic Here can change input and return parameters to your requirements
		
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg=null;
		
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
			PreparedStatement stmt=con.prepareStatement("update invoice set deletestatus=1 where invoice_id=? and deletestatus=0");
			stmt.setDouble(1,invoiceID);
			stmt.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			String updateTableSQL2 = "COMMIT";
			stmt = con.prepareStatement(updateTableSQL2);
			stmt.executeUpdate();
			msg.setMessage("successfully deleted");
			//ResultSet re=stmt.executeQuery();
			//while(re.next()){
				System.out.println("invoice deleted");
				//System.out.println("invoice number"+re.getInt(1)+"ContractNo"+re.getInt(2)+"BuyerId"+re.getInt(3)+"SellerId"+re.getInt(4)+"ProductId"+re.getInt(5)+"UnitPrice"+re.getInt(6)+"Quantity number"+re.getInt(7)+"GrossAmount"+re.getInt(8)+"tax"+re.getInt(9)+"NetAmount "+re.getInt(10)+"ApprovalStatus "+re.getInt(11)+"Draft number"+re.getInt(12)+"FinancialStatus "+re.getInt(13));
			//}
		}
		catch(Exception e){
			msg.setMessage(e.getMessage());
			System.out.println(e.getMessage());
			}
		return msg;
	}
	
	
	private static String UPLOADED_FOLDER = "C://temp//";
    public String uploadInvoice(MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "nofile";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return UPLOADED_FOLDER +file.getOriginalFilename();
    }

    
    
    
    
    //private static final String FILE_NAME = "C:/temp/.xlsx";

    
    public String processInvoice(String FILE_NAME) {
    	String s="<html><body><table style=\"border:1px solid black\">";
        try {
        	
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int rowcount=0;
            while (iterator.hasNext()) {
            	s+="<tr>";
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int count=0;
                while (cellIterator.hasNext()) {
                	count++;
                	
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        s+="<td>";
                        //System.out.println("string type");
                    	s+=currentCell.getStringCellValue();
                    } else {
                        s+="<td>";
                        //System.out.println("integer type");
                    	s+=currentCell.getNumericCellValue();
                    }
                    s+="</td>";
                }
                rowcount++;
                s+="</tr>";
                System.out.println("count="+count);
                if(count!=12)
                {
                	s="<html><body>not in right format...<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/uploadInvoice.html'\" value=\"Go back and upload\"></body></html>" ;
                	return s;
                }
                
                //excelFile.close();
            }
            if(rowcount>14)
            {
            	s="<html><body>exceeds maximum allowed rows(max-rows per file=14)..<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/uploadInvoice.html'\" value=\"Go back and upload\"></body></html>" ;
            	return s;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        s+="</table></body></html>";
        s+="in right format...add to db<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/invoice/insertAll'\" value=\"Enter data into db\"></body></html>";
        return s;
    }

    public String insertAll(String file)
    {
    	
    	int rowcount=0;
    	
    	String s="<html><body><table style=\"border:1px solid black\">";
        try {
        	
            FileInputStream excelFile = new FileInputStream(new File(file));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            double invoiceNo=0;double contractNo=0,qty=0, buyerId=0,sellerId=0; double quantity=0, productId=0; double unitPrice=0; double  grossAmount=0;double netAmount=0; double tax=0;
            Date invDate=new Date(),dueDate=new Date();
            int count=0;
            while (iterator.hasNext()) {
            	s+="<tr>";
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                count=0;
                
                while (cellIterator.hasNext()) {
                	count++;
                	
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        s+="<td>";
                    	s+=currentCell.getStringCellValue();
                    	//System.out.println("string value detected");
                    	
                    	switch(count)
                    	{
                    	case 10:
                    		String c=currentCell.getStringCellValue();
                    		//invDate=c.;
                    		System.out.println("date="+c);
                    		break;
                    	case 12:
                    		String q=currentCell.getStringCellValue();
                    		System.out.println("date="+q);
                    		
                    		//dueDate=
                    		break;
                    	}
                    } else {
                        s+="<td>";
                    	s+=currentCell.getNumericCellValue();
                    	//System.out.println("op"+currentCell.getNumericCellValue());
                    	switch(count)
                    	{
                    	case 1:
                    		invoiceNo=currentCell.getNumericCellValue();
                    		break;
                    	
                    	case 2:
                    		contractNo=currentCell.getNumericCellValue();
                    		break;
                    	case 3:
                    		productId=currentCell.getNumericCellValue();
                    		break;
                    	case 4:
                    		qty=currentCell.getNumericCellValue();
                        	break;
                    	case 6:
                    		buyerId=currentCell.getNumericCellValue();
                    		break;
                    	case 5:
                    		sellerId=currentCell.getNumericCellValue();
                    		break;
                    	
                    	case 7:
                    		unitPrice=currentCell.getNumericCellValue();
                    		break;
                    	case 8:
                    		grossAmount=currentCell.getNumericCellValue();
                    		break;
                    	case 9:
                    		netAmount=currentCell.getNumericCellValue();
                    		break;
                    	
                    	case 11:
                    		tax=currentCell.getNumericCellValue();
                    		break;
                    	
                    	default:
                    		tax=currentCell.getNumericCellValue();
                    		break;
                    	}
                    }
                    s+="</td>";
                }
                rowcount++;
                System.out.println(invoiceNo);
                addInvoice(invoiceNo,contractNo,productId,qty,buyerId,sellerId,unitPrice,grossAmount,netAmount,invDate,(float)tax,dueDate);
                s+="</tr>";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s+="</table>"+(rowcount-1)+" rows inserted"+"</body></html>";
        s+="in right format...add to db<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/invoice/insertAll'\" value=\"Enter data into db\"></body></html>";
        
        return s;
    }
	public CustomMessage sendInvoice(int invoiceID) {
		// TODO Auto-generated method stub
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg=null;
		
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
			PreparedStatement stmt=con.prepareStatement("update invoice set draftStatus=0,approvalstatus=0 where invoice_id=? and deletestatus=0");
			stmt.setDouble(1,invoiceID);
			stmt.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			String updateTableSQL2 = "COMMIT";
			stmt = con.prepareStatement(updateTableSQL2);
			stmt.executeUpdate();
			msg.setMessage("Invoice no"+invoiceID+ "Sent for approval");
			//ResultSet re=stmt.executeQuery();
			//while(re.next()){
				//System.out.println("invoice number"+re.getInt(1)+"ContractNo"+re.getInt(2)+"BuyerId"+re.getInt(3)+"SellerId"+re.getInt(4)+"ProductId"+re.getInt(5)+"UnitPrice"+re.getInt(6)+"Quantity number"+re.getInt(7)+"GrossAmount"+re.getInt(8)+"tax"+re.getInt(9)+"NetAmount "+re.getInt(10)+"ApprovalStatus "+re.getInt(11)+"Draft number"+re.getInt(12)+"FinancialStatus "+re.getInt(13));
			//}
		}
		catch(Exception e){
			msg.setMessage(e.getMessage());
			System.out.println(e.getMessage());
			}
		return msg;
	}
	
		
	public CustomMessage approveInvoice(int invoiceID) {
		// TODO Auto-generated method stub
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg=null;
		String s="";
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
			PreparedStatement stmt=con.prepareStatement("update invoice set draftStatus=0,approvalstatus=1 where invoice_id=? and deletestatus=0");
			stmt.setDouble(1,invoiceID);
			stmt.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			String updateTableSQL2 = "COMMIT";
			stmt = con.prepareStatement(updateTableSQL2);
			stmt.executeUpdate();

			msg.setMessage("Invoice no"+invoiceID+ "approved");
			//ResultSet re=stmt.executeQuery();
			//while(re.next()){
				//System.out.println("invoice number"+re.getInt(1)+"ContractNo"+re.getInt(2)+"BuyerId"+re.getInt(3)+"SellerId"+re.getInt(4)+"ProductId"+re.getInt(5)+"UnitPrice"+re.getInt(6)+"Quantity number"+re.getInt(7)+"GrossAmount"+re.getInt(8)+"tax"+re.getInt(9)+"NetAmount "+re.getInt(10)+"ApprovalStatus "+re.getInt(11)+"Draft number"+re.getInt(12)+"FinancialStatus "+re.getInt(13));
			//}
		}
		catch(Exception e){
			msg.setMessage(e.getMessage());
			System.out.println(e.getMessage());
			}
		return msg;
	}
	public CustomMessage rejectInvoice(int invoiceID) {
		// TODO Auto-generated method stub
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg=null;
		String s="";
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
			PreparedStatement stmt=con.prepareStatement("update invoice set draftStatus=0,approvalstatus=2 where invoice_id=? and deletestatus=0");
			stmt.setDouble(1,invoiceID);
			stmt.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			String updateTableSQL2 = "COMMIT";
			stmt = con.prepareStatement(updateTableSQL2);
			stmt.executeUpdate();

			msg.setMessage("Invoice no"+invoiceID+ "rejected");
			//ResultSet re=stmt.executeQuery();
			//while(re.next()){
				//System.out.println("invoice number"+re.getInt(1)+"ContractNo"+re.getInt(2)+"BuyerId"+re.getInt(3)+"SellerId"+re.getInt(4)+"ProductId"+re.getInt(5)+"UnitPrice"+re.getInt(6)+"Quantity number"+re.getInt(7)+"GrossAmount"+re.getInt(8)+"tax"+re.getInt(9)+"NetAmount "+re.getInt(10)+"ApprovalStatus "+re.getInt(11)+"Draft number"+re.getInt(12)+"FinancialStatus "+re.getInt(13));
			//}
		}
		catch(Exception e){
			msg.setMessage(e.getMessage());
			System.out.println(e.getMessage());
			}
		return msg;
	}

	

}