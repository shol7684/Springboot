package com.baemin.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.baemin.service.UserService;
import com.baemin.vo.KakkoOauthTokenVO;
import com.baemin.vo.KakkoUserInfoVO;
import com.baemin.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class KaKaoLoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/kakkoLogin")
	public String kakkoLogin() {
	
		
		System.out.println(1111);
		String url = "https://kauth.kakao.com/oauth/authorize?"; 
		String clientId = "client_id=2393835f59d6ac4e44bc28411bcd22db&";
		String redirectUri = "redirect_uri=http://localhost:8080/authorizeCode&";
		String responseType = "response_type=code";
		
		// 여기로 이동하면 /authorizeCode 으로 code를 준다
		return "redirect:" + url + clientId + redirectUri + responseType;
	}
	
	
	@GetMapping("/authorizeCode")
	public String authorizeCode(String code,Model model,HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		System.out.println("인증코드 = " + code);
		
		
//		String url = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=2393835f59d6ac4e44bc28411bcd22db&"
//				+ "redirect_uri=http://localhost:8080/authorizeCode&code="+code;
//		
	        
	        HttpHeaders headers = new HttpHeaders();
	        
	        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	        
	        MultiValueMap params = new LinkedMultiValueMap();
	        
	        params.add("grant_type", "authorization_code");
	        params.add("client_id", "2393835f59d6ac4e44bc28411bcd22db");
	        params.add("redirect_uri", "http://localhost:8080/authorizeCode");
	        params.add("code", code);
	        
	        HttpEntity kakaoRequest1 = new HttpEntity<>(params, headers);	        
	        
	        RestTemplate rt = new RestTemplate();
		
	        // 엑세스토큰 받기
	        ResponseEntity<String> tokenResponse = rt.exchange(
	        		"https://kauth.kakao.com/oauth/token",
	        		HttpMethod.POST,
	        		kakaoRequest1,
	        		String.class );
	        
	        ObjectMapper om = new ObjectMapper();
	        
	        // 카카오에서 받은 JSON 변환
	        KakkoOauthTokenVO ot = om.readValue(tokenResponse.getBody(), KakkoOauthTokenVO.class);
	        
	        
	        // 카카오 엑세스토큰
	        String tocken = ot.getAccess_token();
	        
	        // 카카오 엑세스토큰으로 사용자 정보받을 헤더생성
	        HttpHeaders headers2 = new HttpHeaders();
	        
	        headers2.add("Authorization", "Bearer " +  ot.getAccess_token());
	        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
	        HttpEntity kakaoRequest2 = new HttpEntity(headers2);

	        ResponseEntity<String> profileResponse = rt.exchange(
	        	"https://kapi.kakao.com/v2/user/me",
	        	HttpMethod.POST,
	        	kakaoRequest2,
	        	String.class
	        );
	        
	        KakkoUserInfoVO userInfo = om.readValue(profileResponse.getBody(), KakkoUserInfoVO.class);
	        
	        String userEmail = userInfo.getKakao_account().getEmail();
	        String nickname = userInfo.getProperties().getNickname();
	        
	        // 카카오 아이디와 동일한 계정이 있는지 확인
	        Map userCheck = userService.kakkoIdCheck(userEmail);
	        System.out.println(userInfo);
	        
	        // 없을때
	        if(userCheck == null) {
	        	model.addAttribute("kakkoIdCheck", 0);
	        	model.addAttribute("userEmail", userEmail);
	        	model.addAttribute("nickname", nickname);
	        	
	        }
	        
	        
	        // 이미 카카오 연동중이면 바로 로그인 처리
	        if(userCheck != null && "1".equals(userCheck.get("kakkoLogin"))) {
	        	UserVO user = userService.continueLogin(userEmail);
	    		
	    		session.setAttribute("user", user);
	        	return "redirect:/myPage";
	        }
	        
	        // 카카오아이디와 같은 계정이 있고 연동중이 아닐때
	        if(userCheck != null && userCheck.get("kakkoLogin") == null ) {
	        	System.out.println("카카오연동중아님");
	        	model.addAttribute("kakkoIdCheck", 1);
	        	model.addAttribute("userCheck" , userCheck);
	        	model.addAttribute("nickname",nickname);
	        	
	        }
			
		return "user/join";
		
	}
	
	// 카카오이메일과 같은 이메일이 사용중일때
	@Transactional
	@PostMapping("/kakkoConnetion")
	public String kakkoConnetion(String userId, String userPhone,String nickname,HttpSession session) {
		
		System.out.println(userId);
		System.out.println(userPhone);
		System.out.println(nickname);
		
		userService.kakkoConnetion(userId); // 카카오 연동
		
		UserVO user = userService.continueLogin(userId); // 유저정보 가져오기
		
		session.setAttribute("user", user);
		
		return "redirect:/myPage";
	}
	
	// 카카오 아이디로 회원가입
	@PostMapping("/kakkoJoin")
	public String kakkoJoin(UserVO userVO,HttpSession session) {
		
		System.out.println(userVO);
		
		UUID ud = UUID.randomUUID();
		
		userVO.setUserPwd(String.valueOf(ud));
		
		userService.kakkoJoin(userVO);
		
		UserVO user = userService.continueLogin(userVO.getUserId()); // 유저정보 가져오기
	
		session.setAttribute("user", user);
		
		return "redirect:/myPage";
		
	}
	
	

}
