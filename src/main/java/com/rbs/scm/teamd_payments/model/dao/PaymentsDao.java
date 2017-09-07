package com.rbs.scm.teamd_payments.model.dao;

import java.sql.Date;
import java.util.Map;

import com.rbs.scm.teamd_payments.model.beans.*;

public interface PaymentsDao {
//return all details
Bank[] getAllBankDetails();
Swift[] getAllSwiftDetails();
Customer_Transaction[] getAllCustomerTransactionDetails();
Bank_to_Customer[] getAllBankToCustomerDetails();
Customer_to_Bank[] getAllCustomerToBankDetails();

//return row corresponding to a key
Bank getBankDetails(String swift_id);
Swift getSwiftDetails(int transaction_id);
Customer_Transaction getCustomerTransactionDetails(int transaction_id);
Bank_to_Customer getBankToCustomerDetails(int transaction_id);
Customer_to_Bank getCustomerToBankDetails(int transaction_id);

//inserting a row to database
boolean addBank(Bank b);
boolean addSwift(Swift s);
boolean addCustomer_Transaction(Customer_Transaction ct);
boolean addBank_to_Customer(Bank_to_Customer btc);
boolean addCustomer_to_Bank(Customer_to_Bank ctb);

//to extract details of pending transactions
Customer_Transaction[] getAllPendingCustomerTransactionDetails(String status);
Bank_to_Customer[] getAllPendingBankToCustomerDetails(String status);
Customer_to_Bank[] getAllPendingCustomerToBankDetails(String status);

//to extract details of  transactions based on Aml Status
Customer_Transaction[] getAllCustomerTransactionDetailsByAmlStatus(String status);
Bank_to_Customer[] getAllBankToCustomerDetailsByAmlStatus(String status);
Customer_to_Bank[] getAllCustomerToBankDetailsByAmlStatus(String status);

//extract transactions based on payerId
Customer_Transaction[] getCustomerTransactionDetailsbyPayerId(String sid);
Bank_to_Customer[] getBankToCustomerDetailsbyPayerId(String sid);
Customer_to_Bank[] getCustomerToBankDetailsbyPayerId(String sid);

//extract transactions based on payeeId
Customer_Transaction[] getCustomerTransactionDetailsbyPayeeId(String sid);
Bank_to_Customer[] getBankToCustomerDetailsbyPayeeId(String sid);
Customer_to_Bank[] getCustomerToBankDetailsbyPayeeId(String sid);

//extract transactions based on date
Customer_Transaction[] getCustomerTransactionDetailsbyDate(Date d);
Bank_to_Customer[] getBankToCustomerDetailsbyDate(Date d);
Customer_to_Bank[] getCustomerToBankDetailsbyDate(Date d);

//getAllNostroAccountsforABank
Map<String,String> getNostroAccounts(String swift_id);

//update aml status of transaction
boolean updateAmlStatus(int transaction_id, String status);

//update status of transaction
boolean updateTransactionStatus(int transaction_id,String status);

//get bank currency
String getBankCurrency(String swift_id);

//add and get vostro accounts
Vostro_Accounts[] getVostroAccounts(String swift_id);
boolean addVostroAccount(Vostro_Accounts v);
Vostro_Accounts[] getAllVostroAccounts();

//upload and extract fx data
boolean addFXData(FXData fxd[]);
double getFXData(String currency);
FXData[] getAllFXData();
  
//aml
boolean isCountrySanctioned(String code);
boolean isPersonSanctioned(String name);
boolean addSanctionedCountry(String code,String country);
boolean addSanctionedName(String name);
}
