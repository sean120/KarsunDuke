package com.seanfiles.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ACEGFSDateUtil {
	
	public static final String GFSCreatedDateFormat="yyyy-MM-dd HH:mm:ss.SSS";
	public static final String GFSCreatedDateTimezone="UTC";
	
	public static Date convertStringToDate(String dateStr) {
		return convertStringToDate(dateStr, GFSCreatedDateFormat, GFSCreatedDateTimezone);
	}
	
	public static String convertDateToString(Date dt) {
		return convertDateToString(dt,GFSCreatedDateFormat,GFSCreatedDateTimezone);
	}
	
	public static Date getTransactionDate(String deploymentDateStr, String dateDescStr) {
		return getTransactionDate(convertStringToDate(deploymentDateStr), dateDescStr);
	}
	
	public static Date convertStringToDate(String dateStr, String fmt, String timeZone) {
		Date dt=null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(fmt);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(timeZone));
		try {
			dt=dateFormatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public static String convertDateToString(Date dt, String fmt, String timeZone) {
		SimpleDateFormat formattedDate = new SimpleDateFormat(fmt);
		formattedDate.setTimeZone(TimeZone.getTimeZone(timeZone));
		return formattedDate.format(dt);
	}
	
	public static Date getTransactionDate(Date deploymentDate, String dateDescStr) {
		int nDaysToAdd=0;
		String[] dateDescStrSplited = dateDescStr.split("\\s+");
		if(dateDescStr.contains("post")) {
			nDaysToAdd=Integer.parseInt(dateDescStrSplited[0]);
		}
		else if(dateDescStr.contains("pre")) {
			nDaysToAdd= -1 * Integer.parseInt(dateDescStrSplited[0]);			
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(deploymentDate);
		calendar.add(Calendar.DATE, nDaysToAdd);
		return calendar.getTime();
	}

	public static Date getTransactionDate(String transactionDate) {
		return convertStringToDate(transactionDate+" 00:00:00.000");
	}	
}
