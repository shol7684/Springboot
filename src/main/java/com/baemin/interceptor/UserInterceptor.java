package com.baemin.interceptor;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baemin.vo.UserVO;

public class UserInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("myPage interseptor");
		
		HttpSession session = request.getSession();
		
		UserVO user = (UserVO) session.getAttribute("user");
		
		if(user == null) {
			System.out.println("null");
			throw new NoSuchElementException();
		}
		
		return true;
	
	}

}
