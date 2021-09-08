package com.baemin.interceptor;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class storeInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println("pathVariables = " + pathVariables );
		
		if(pathVariables == null || pathVariables.equals("")) {
//			throw new NoSuchElementException();
		
			System.out.println("주소 없음");
			HttpSession session = request.getSession();
			
			session.setAttribute("storeMSG", "주소를 입력해 주세요");
			
			response.sendRedirect("/");
			return false;
			
		}
		
		return true;
	}
	
	
	

	  
	
}
	