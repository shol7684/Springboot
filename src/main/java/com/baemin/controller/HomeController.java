package com.baemin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.baemin.service.StoreService;

@Controller
public class HomeController {
	
	@Autowired
	private StoreService service;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/")
	public String getHome(Model model,HttpSession session) throws UnsupportedEncodingException {
		
		if((String) session.getAttribute("storeMSG") != null) {
			String storeMSG = "주소를 입력해 주세요";
			
			model.addAttribute("noAddress" , storeMSG );
			session.removeAttribute("storeMSG");
			return "home";
		}
		
		System.out.println("home");
		
		Map address = (Map)session.getAttribute("address");
		System.out.println(address);
		
		session.setAttribute("address", address);
		
		model.addAttribute("address",address);
		
		return "home";
	}
	
	@PostMapping("/")
	public String postHome(Model model, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		System.out.println("post");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String address4 = request.getParameter("address4");
		
		Map map = new HashMap();
		
		map.put("address1", address1);
		map.put("address2", address2);
		map.put("address3", address3);
		map.put("address4", address4);
		
		session.setAttribute("address", map);
		
		System.out.println("address = " + map);		

		model.addAttribute("address" ,map);
		
		return "redirect:/";
	}
}
