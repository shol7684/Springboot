package com.baemin.interceptor;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class searchInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
//		request.getMethod().equals(HttpMethod.POST.name())
		
		System.out.println(request.getMethod());
		
		if(request.getMethod().equals("POST")) {
			HttpSession session = request.getSession();
			
			Map address = (Map)session.getAttribute("address");
			
			if(address == null ) {
				throw new NoSuchElementException();
			}
			
			System.out.println(address);
		}
		
		return true;
	}
	
}
