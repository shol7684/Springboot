package com.baemin.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public class OrderNumber {

	public static String orderNumber() {
		String orderNum= "";
		
		Calendar cal  = Calendar.getInstance();
		
		String y = new DecimalFormat("00").format(cal.get(Calendar.YEAR)); 
		String m = new DecimalFormat("00").format(cal.get(Calendar.MONDAY) + 1 );
		String d = new DecimalFormat("00").format(cal.get(Calendar.DATE)); 
		
		String r = ""; 
		
	    for(int i=0; i<8;i++) {
	    	r += (int) (Math.random()*10);
	    }
	    
	    orderNum = y + m + d + "_" + r;
	    
	    return orderNum;
		
	}

}
