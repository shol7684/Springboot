package com.baemin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;


@Repository
public class CookieManager {

	public Set getCookie(HttpServletRequest request,String cookieName) throws UnsupportedEncodingException {
			
			
			Cookie[] getCookies = request.getCookies();
			
			String cookie = "";
			
			for(int i=0;i<getCookies.length; i++) {
				
				if(getCookies[i].getName().equals(cookieName)) {
				
					cookie =  URLDecoder.decode(getCookies[i].getValue() , "UTF-8") ;
				}
			}
			String[] temp = cookie.split(",");
			
//			List searchList = new ArrayList();
			Set cookieList = new LinkedHashSet();
			
			
			if(!temp[0].equals("")) {
				
				for(int i =0;i<temp.length;i++) {
					cookieList.add(temp[i]);
				}
				
			}
			
			return cookieList;
			
	}
	
	
	public void setCookie(Set searchList,String CookieName, HttpServletRequest request, HttpServletResponse response ) throws UnsupportedEncodingException {
		
		String cookie = "";
		
		Iterator it = searchList.iterator();
		
		int index = 0;
		
		while(it.hasNext() ) {
			
			cookie = cookie  + "," +  it.next();
			
			if(index > 8 ) break;
			
			index++;
		} 
		
		
		if(cookie.startsWith(",")) {
			
			cookie = cookie.substring(1 , cookie.length()).trim();
		}	
		
		System.out.println("쿠키 저장 ="+  cookie);
		
		Cookie setCookie = new Cookie(CookieName , URLEncoder.encode(cookie, "UTF-8"));
		
		setCookie.setPath("/");
		
		setCookie.setMaxAge(60 * 60 * 24 * 7 ); //일주일
		
		response.addCookie(setCookie);
		
	}
	
	
	
	
}
