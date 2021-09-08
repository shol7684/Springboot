package com.baemin.error;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExcepHandler {

//	@org.springframework.web.bind.annotation.ExceptionHandler
//	@ExceptionHandler(NoSuchElementException.class)
//	public ModelAndView defaultException(HttpServletRequest request, NoSuchElementException  NoSuchElementException) {
//		ModelAndView mv = new ModelAndView("user/myPage");
//		mv.addObject("NoSuchElementException","로그인 후 이용 가능합니다.");
//		
//		return mv;
//	}
	@ExceptionHandler(NoSuchElementException.class)
	public String noLogin(Model model,RedirectAttributes rttr,HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		System.out.println("에러 referer = " + referer);
		
		System.out.println("에러 request = " + request.getRequestURI());
		
		if(request.getRequestURI().equals("/store/search")) {
			rttr.addFlashAttribute("noAddress","주소를 입력해주세요");
			return "redirect:/";
			
		} else {
			rttr.addFlashAttribute("msg","로그인 후 이용가능합니다");
			return "redirect:/myPage";
			
		}
		

	}
	
}
