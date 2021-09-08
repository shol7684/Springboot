package com.baemin.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

	 
	@GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
//
//        System.out.println("state" + status.toString());
//        System.out.println(httpStatus.getReasonPhrase());
//        
//        
//        model.addAttribute("code", status.toString());
//        model.addAttribute("msg", httpStatus.getReasonPhrase());
//        model.addAttribute("timestamp", new Date());
        
        
        return "error/errorPage";
    }

	
	
	
}
