package com.seanfiles.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

/**
 * 
 * @author C45888 --> Saule Beisenova
 *
 */
public class DateUtils {
	
	/**
	 * Validate the expiration date of the Request File
	 * 
	 * @author C45888 --> Saule Beisenova
	 */
	public void validateExpDateOfRequestFile ( String appraisalWaiverPrescreenExpirationDate ) {
		String requestFileExpDate = addDays(getToday("MMddyyyy"), "MMddyyyy", 120);
		Assert.assertTrue("Request file is expired!", requestFileExpDate.equals(appraisalWaiverPrescreenExpirationDate));
	}
	
	/**
	 * Get Today's Date in MMddyyyy format
	 * 
	 * @param format
	 * @return
	 * @author C45888 --> Saule Beisenova
	 */
	public static String getToday(String format){
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateNow = formatter.format(today);
		return dateNow;
	}
	
	/**
	 * Converting String to date format
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author C45888 --> Saule Beisenova
	 */
	public static Date convertStringToDate(String date, String format){
		Date date2 = null;
		
		try{
			date2 = new SimpleDateFormat(format).parse(date);
		}catch(ParseException e){
			throw new RuntimeException("Could Not Parse The Date"+date+"With Format"+format);
		}
		return date2;
	}
	
	/**
	 * Adding Days to current Calendar date 
	 * 
	 * @param today
	 * @param format
	 * @param daysToAdd
	 * @return
	 * @author C45888 --> Saule Beisenova
	 */
	public static String addDays(String today, String format, int daysToAdd){
		Date date = convertStringToDate(today, format);
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
		
		String previousDay = dateFormat.format(calendar.getTime());
		
		return previousDay;
	}
}