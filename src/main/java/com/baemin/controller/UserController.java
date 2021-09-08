package com.baemin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baemin.service.OrderService;
import com.baemin.service.StoreService;
import com.baemin.service.UserService;
import com.baemin.utils.CookieManager;
import com.baemin.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CookieManager cookieManager;
	
	
	@GetMapping("/myPage")
	public String myPage() throws IOException {
		
		return "user/myPage";
	}
	
	
	
	// 로그인화면
	@GetMapping("/login")
	public String getLogin(Model model,HttpSession session,HttpServletRequest request) {
		System.out.println("-------------------GET 로그인-------------------");
		
		String referer = request.getHeader("Referer");

		
		session.setAttribute("prevPage", referer);
		System.out.println("referer= " + referer );
		
		
		return "user/login";
	}
	
	// 로그인 버튼 클릭
	@PostMapping("/login")
	public String postLogin(UserVO userVO,String continueLogin, HttpSession session, RedirectAttributes rttr, 
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("-------------------POST 로그인-------------------");
		
		int login = userService.login(userVO, session, continueLogin, request, response); // userVO에서 아이디와 비번만 사용
		
		if(login == 0 ) {  
			
			rttr.addFlashAttribute("loginFail","아이디를 확인해주세요");
			return "redirect:/login";
			
		} else if (login == 1 ) {
			
			rttr.addFlashAttribute("loginFail","비밀번호를 확인해주세요");
			return "redirect:/login";
		
		} 

		String prevPage = (String) session.getAttribute("prevPage");  // 주소창에 바로 입력시 null
		
		System.out.println("이전 페이지 = " + prevPage);
		
		if(prevPage == null || prevPage.contains("join") || prevPage.contains("login") ) {
			return "redirect:myPage"; 
		} 
		
		
		
		return "redirect:" + prevPage;
	}
	
	// 회원가입 확면
	@GetMapping("/join")
	public String getJoin() {
		
		return "user/join";
	}
	
	// 회원가입 버튼 클릭
	@PostMapping("/join")
	public String postJoin(UserVO userVO,Model model, RedirectAttributes rttr) {
		System.out.println(userVO);  
		System.out.println("-------------------POST 회원가입-------------------");
		
		int idCheck = userService.overlapCheck(userVO,1); // 1=아이디체크,0=닉네임체크  userVO에서 아이디만 사용해서 아이디가 사용중인지 확인 
		
		if(idCheck == 0 ) {
			userService.join(userVO);
		} else {
			rttr.addFlashAttribute("joinFail","아이디를 확인해주세요");
			return "redirect:/join";
		}
		

		return "redirect:/login";
	}
	
	// 네이버 연동
//	@PostMapping("/connetion")
//	public String connetion(String id,String email,HttpSession session) {
//		
//		userService.naverConnection(id,email); // 네이버 연동  NaverLogin을 naverInfo.get("id")로 업데이트
//		
//		Map<String, Object> naverInfo  = new HashMap<String, Object>();
//		naverInfo.put("email", email);
//		naverInfo.put("id", id);
//		
//		UserVO user = userService.naverJoin(naverInfo); // 로그인
//		session.setAttribute("user", user);
//		
//		return "redirect:/myPage";	
//	}
	
	// 회원가입/닉네임 수정에서 이메일 닉네임을 입력할때마다  확인
	@ResponseBody
	@PostMapping("/overlapCheck")
	public int idCheck(UserVO userVO,int functionNumber) {
		System.out.println("-------------------ID 체크-------------------");
			
		int overlapCheck = userService.overlapCheck(userVO,functionNumber); // userVO에서 아이디 || 닉네임을 받아서 사용해중 확인
		System.out.println("overlapCheck = " + overlapCheck);
	
		
		return overlapCheck;
	}
	
	// 닉네임 or 비밀번호 수정
	@ResponseBody
	@PostMapping("/infoModify")
	public void nicknameModify(String info,int functionNum,HttpSession session)  {
		
		System.out.println(info);
		
		UserVO userVO = (UserVO) session.getAttribute("user");
		
		String userId = userVO.getUserId();
		
		userService.modifyInfo(info,userId,functionNum); // functionNum 0 닉네임수정, 1 비번수정
				
	}
	
	
	// 로그아웃
	@GetMapping("/logout2")
	public String logout(HttpSession session,HttpServletResponse response) {
		System.out.println("로그아웃");
		
		
//		String url = "/oauth/logout?";
//		String clientId = "client_id=2393835f59d6ac4e44bc28411bcd22db&";
//		String logoutRedirectUri = "logout_redirect_uri=http://localhost:8080/myPage";
		
		
		session.removeAttribute("user");
		
		Cookie cookie = new Cookie("userId", null);
		
		cookie.setPath("/");
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		
		return "redirect:/myPage";
		
		
	}
	
	// 포인트확인
	@GetMapping("/myPage/point")
	public String point(HttpSession session,Model model) {
		UserVO user = (UserVO)session.getAttribute("user");
		System.out.println(user);
		
		List pointList = userService.getPoint(user.getUserId());
		System.out.println(pointList);
		model.addAttribute("point" , user.getPoint());
		model.addAttribute("pointList",pointList);
		
		return "user/point";
	}
	
	// 상품권 등록
	@PostMapping("/pointRegister")
	public String pointRegister(String giftCardNum,HttpSession session) {
		UserVO user = (UserVO)session.getAttribute("user");
		
		Map  giftCard = userService.giftCardCheck(giftCardNum) ;
		
		if(giftCard != null) {
			String title =  (String) giftCard.get("TITLE");
			int point =  Integer.parseInt(String.valueOf(giftCard.get("POINT")));
			
			orderService.addPoint(user.getUserId(),point,title);
			user.setPoint(user.getPoint() + point );
			
			session.setAttribute("user", user);
			
		}
		
		return "redirect:/myPage/point";
	}
	
	// 마이페이지 내 정보
	@GetMapping("/myPage/info")
	public String info(HttpSession session,Model model) {
		UserVO user = (UserVO)session.getAttribute("user");
		  
		
		model.addAttribute("user", userService.userInfo(user.getUserId()));
		
		return "user/info";
	}
	
	
	// 찜하기
	@ResponseBody
	@PostMapping("/dibs")
	public void dibs(HttpSession session,int storeNum,HttpServletRequest request,HttpServletResponse response ) throws UnsupportedEncodingException {
		
		System.out.println(storeNum);
		
		UserVO user = (UserVO) session.getAttribute("user");
		
		System.out.println("user = " + user);
		
		
		if(user != null) {
			System.out.println("호그인상태");
			
			userService.dibs(user.getUserId(), storeNum);
			
			return;
		} 
		
		if(user == null) {
			
			System.out.println("비호그인상태");
			
			Set dibsList = cookieManager.getCookie(request, "dibsList");
			
			if(dibsList.contains(String.valueOf(storeNum))) {
				dibsList.remove(String.valueOf(storeNum));
				
				cookieManager.setCookie(dibsList, "dibsList", request, response);
				
			} else {
				Set newDibsList = new LinkedHashSet();
				
				newDibsList.add(storeNum);
				
				newDibsList.addAll(dibsList);
				
				cookieManager.setCookie(newDibsList, "dibsList", request, response);
				
			}
			
			
			
		}

	}
	
	// 찜한 가게
	@GetMapping("/myPage/dibs")
	public String dibs(HttpSession session,Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		UserVO user = (UserVO) session.getAttribute("user");
		System.out.println(user);
		List dibsList = null;
		
		if(user !=null) {
			System.out.println("로그인상태");
			dibsList =userService.dibsLogin(user.getUserId());
			System.out.println(dibsList);
		} else {
			System.out.println("비로그인상태");
			
			
			Cookie[] getCookie = request.getCookies();
			
			for(int i=0;i<getCookie.length;i++) {
				if(getCookie[i].getName().equals("dibsList")) {
					
					String storeNums = URLDecoder.decode(getCookie[i].getValue() , "UTF-8");
					
					if(!storeNums.equals("")) {
						System.out.println(storeNums);
						
						dibsList = userService.dibsNoLogin(storeNums);
					}
					
				}
			}
		}
		
		model.addAttribute("dibsList",dibsList);
		
		return "user/dibs";
	}
	
	// 내가 쓴 리뷰
	@GetMapping("/myPage/myReview")
	public String myReview(HttpSession session,Model model) {
		UserVO user = (UserVO) session.getAttribute("user");
		String userId = user.getUserId();
				
		List reviewList = userService.getReviewList(userId);
		
		model.addAttribute("reviewList",reviewList);
		
		return "user/myReview";
	}

	
//	@GetMapping("/myPage/coupon")
//	public String coupon(RedirectAttributes rttr) {
//		
//		rttr.addFlashAttribute("update" ,"업데이트 중 입니다");
//		
//		
//		return "redirect:/myPage";
//				
//				
//	}
//	
//	@GetMapping("/myPage/gift")
//	public String gift(RedirectAttributes rttr) {
//		
//		rttr.addFlashAttribute("update" ,"업데이트 중 입니다");
//		
//		
//		return "redirect:/myPage";
//		
//		
//	}


	
	
	
	
	
}
