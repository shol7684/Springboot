package com.baemin.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.baemin.service.UserService;
import com.baemin.vo.UserVO;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
	
public class LoginInterceptor implements  HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserVO user = (UserVO)session.getAttribute("user");
		System.out.println("user = " + user  );
		
		// 로그인 상태가 아닐때
		if(user == null) {
			System.out.println("LoginInterceptor");
			
			if(request.getCookies() != null ) {
				System.out.println("아이디 기억");
				
				Cookie[] cookies = request.getCookies();
				
				for(int i=0; i<cookies.length;i++) {
					
					// 쿠키에 userId(로그인 유지하기)가 있으면 로그인 
					if(cookies[i].getName().equals("userId")) {
						
						UserVO userVO = userService.continueLogin(URLDecoder.decode(cookies[i].getValue() , "UTF-8") );
						
						session.setAttribute("user", userVO);
						
						return true;
					}
				}	
			
			}
				
			
			String userId = (String) session.getAttribute("guest");
			
			System.out.println("userId =" + userId);
			if(userId == null) {
				System.out.println("세션 만료");			
				
				
				session.setMaxInactiveInterval(60*60*6);
				
				session.setAttribute("guest", "guest");
				
//				response.sendRedirect("/");
				return false;
				
			} else {
				
				System.out.println(userId);
			}
			
		}
		
		
		return true;
	
	}

	  
	
}
	