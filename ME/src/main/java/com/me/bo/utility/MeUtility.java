package com.me.bo.utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeUtility {
	
	public static DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	public static String dd_MM_yyyy = "dd/MM/yyyy";

	
	/**
	 * getDateTime can be used to get current date/time in in String format( format can be changed by input param "yyyy/MM/dd HH:mm:ss" ).
	 *  
	 * 
	 * @return  <b> String </b>  current date/time
	 * @param  format  Date / Time formatter String   
	 * 
	 * */
	 public static String getDateTime(String format) {    
		  // "yyyy/MM/dd HH:mm:ss"
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now);  
		  }    
	 

	 public static LocalDateTime getLocalDateTimeFromStrDate(String strDate) {    
		  // "yyyy/MM/dd HH:mm:ss"
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   return LocalDateTime.parse(strDate,dtf);  
		  }   
	 
	 
	 public static String getStrDate(LocalDateTime dateTime, String reqFormat) {    
		  // "yyyy/MM/dd HH:mm:ss"
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern(reqFormat);  
		   return dtf.format(dateTime);
		  }   
	 
}
