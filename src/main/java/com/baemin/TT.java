package com.baemin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TT {

	
	@GetMapping("/tt")
	public String test(String test) {
		 
		
		System.out.println(test);
		
		
		return "test";
	}
	
	@PostMapping("/tt")
	public String testt(String test,Model model) {
		
		test = test.replace("\r\n","<br>");
		
		
		System.out.println(test);
		
		
		
		
		model.addAttribute( "test" , test);
		
		return "test";
	}
	
	
	
	
}
