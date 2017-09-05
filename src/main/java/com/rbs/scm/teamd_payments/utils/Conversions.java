package com.rbs.scm.teamd_payments.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conversions {
	public static Date convertToSqlDate(String inputString) throws ParseException
	{
		String startDate=inputString;
    	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    	java.util.Date dat = sdf1.parse(startDate);
    	java.sql.Date sqlStartDate = new java.sql.Date(dat.getTime());
    	return sqlStartDate;
    	
	}
}
