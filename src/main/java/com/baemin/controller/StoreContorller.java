package com.baemin.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baemin.service.AdminService;
import com.baemin.service.StoreService;
import com.baemin.utils.CookieManager;
import com.baemin.utils.UploadFileUtils;
import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.ReviewVO;
import com.baemin.vo.UserVO;

@Controller
public class StoreContorller {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private AdminService  adminService;
	 
	@Autowired
	private CookieManager cookieManager;
	// 커밋
	// 매장 목록
	@GetMapping("/storeList/{category}/{address1}")
	public String storeList(HttpServletRequest request,Model model,HttpSession session,
			 @PathVariable("category") int category,
			 @PathVariable("address1") Integer address1 ) throws UnsupportedEncodingException {
		
		System.out.println("------------------- 매장 목록 -------------------");
		Map address =(Map) session.getAttribute("address"); 
		
    
		List storeList =storeService.getStoreList(category,address1/100); // 우편번호가 31090 이면  310,  주소가 310이 포함된 매장 찾기 

		
		Calendar time = Calendar.getInstance();
		
		System.out.println(storeList);
		
		model.addAttribute("time", time.get(Calendar.HOUR_OF_DAY)); // 현재시간과 가게 오픈시간 비교 
		model.addAttribute("address", address); 
		model.addAttribute("storeList",storeList);
		model.addAttribute("category",category); // 정렬을 위해서 추가
		
		
		
		System.out.println("------------------- 매장 목록 -------------------");
		return "store/storeList";
		
		
	}
	// 매장 상세
	@Transactional
	@GetMapping("/store/detail/{storeNum}")
	public String detail(@PathVariable int storeNum,Model model,HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("------------------- 매장 상세 -------------------");
		
		UserVO user = (UserVO) session.getAttribute("user");
		String userId;
		GoodStoreVO goodStoreVO;
		
		if(user != null) {
			userId = user.getUserId();
			model.addAttribute("userId",userId);
			
			goodStoreVO = adminService.getStoreDetail(storeNum,userId); // 가게 정보
			
		} else {
			userId = (String) session.getAttribute("guest");
			model.addAttribute("userId","guest");
			
			goodStoreVO = adminService.getStoreDetail(storeNum,userId); // 가게 정보
			
			Set dibsList = cookieManager.getCookie(request, "dibsList");
			
			System.out.println("매장 쿠키 = "+dibsList);
			
			if(!dibsList.add(String.valueOf(storeNum))) {
				goodStoreVO.setGoodCheck(1);
			} 
			
		}
		System.out.println(goodStoreVO);
		
		List reviewList = storeService.reviewList(storeNum);	// 리뷰 리스트
		List menuList = storeService.getMenuList(storeNum); // 메뉴리스트
//		Map<String,Object> review  = storeService.reviewCount(storeNum); // 리뷰 수,사장님 댓글 수,주문 수, 찜 수

		
		model.addAttribute("reviewList",reviewList); 
//		model.addAttribute("review",review); 
		model.addAttribute("storeVO",goodStoreVO); 
		model.addAttribute("menuList",menuList);
		
		System.out.println("------------------- 매장 상세 -------------------");
		return "store/detail";
	}
	
	// 주소 없으면 홈으로
	@ResponseBody
	@PostMapping("/adrressCheck")
	public void adrressCheck() {
		
	}
	
	// 리뷰 작성
	@Transactional
	@PostMapping("/store/review")
	public String reivew(ReviewVO reviewVO,HttpSession session,MultipartFile img) throws IOException, Exception {
		System.out.println("리뷰쓰기 = " + reviewVO );
		
		
		if(!img.isEmpty()) { // 이미지 첨부 o
			 reviewVO.setReviewImg(UploadFileUtils.upload(img));
			 
		} else {	// 이미지 첨부 x
			reviewVO.setReviewImg("");
		}
		
		UserVO user = (UserVO) session.getAttribute("user");
		
		String userId= user.getUserId();
		
		reviewVO.setUserId(userId);
		
		storeService.review(reviewVO);
		
		storeService.reivewComplete(userId, reviewVO.getOrderNum());
		
		return "redirect:/orderList";
	}
	
	// 리뷰 수정하기
	@PostMapping("/store/reviewModify")
	public String reviewModify(ReviewVO reviewVO,String modifyImg, HttpSession session,MultipartFile img) throws IOException, Exception {
		
		UserVO user = (UserVO) session.getAttribute("user");
		String userId= user.getUserId();
		reviewVO.setUserId(userId);
		
		
		if(!img.isEmpty()) {	//	이미지 첨부시 기존 이미지 삭제
			new File(UploadFileUtils.uploadPath +  reviewVO.getReviewImg()).delete();
			
			reviewVO.setReviewImg(UploadFileUtils.upload(img));
		}
		
		storeService.modifyReview(reviewVO);
		
//		storeService.reivewComplete(userId, reviewVO.getOrderNum());
		
		return "redirect:/orderList";
	}
	
	
	// 가게 검색
	@GetMapping("/store/search")
	public String search(HttpSession session,Model model,String storeName,HttpServletRequest request) throws UnsupportedEncodingException {
		
		Map address= (Map) session.getAttribute("address");
		
		Set searchList = cookieManager.getCookie(request, "searchWord");
		
		model.addAttribute("address",address);
		model.addAttribute("searchList" , searchList);
		
		return "/store/search" ;
		
	}
	
	// 가게 검색
	@PostMapping("/store/search")
	public String postSearch(String keyword,Integer address1,Model model,HttpServletResponse response
			,HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
		System.out.println(keyword);
		
		List storeList =storeService.searchStoreList(keyword,address1/100);
		
		Set searchList = new LinkedHashSet();
		
		searchList.add(keyword.trim());
		
		searchList.addAll(cookieManager.getCookie(request, "searchWord")); // 쿠키 불러오기
		
		cookieManager.setCookie(searchList,"searchWord", request,response); // 쿠키 저장하기 
		
		
		model.addAttribute("storeList",storeList);
		model.addAttribute("searchList" , searchList);
		model.addAttribute("searchWord" , keyword);
		
//		return "redirect:/store/search" ;
		return "/store/search" ;
	}
	
	// 가게 검색어 1개 삭제
	@ResponseBody
	@PostMapping("/searchWordDelete")
	public Set searchWordDelete(String keyword, Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("1개 삭제");
		
		Set searchList = cookieManager.getCookie(request, "searchWord"); // 쿠키 불러오기
		
		searchList.remove(keyword.trim());
		
		cookieManager.setCookie(searchList, "searchWord", request,response); // 쿠키 저장하기
		
		return searchList;
	}
	
	// 가게 검색어 전체삭제
	@ResponseBody
	@PostMapping("/searchWordDeleteAll")
	public void searchWordDeleteAll(HttpServletResponse response,HttpServletRequest reqeust) throws UnsupportedEncodingException {
		
		Cookie  cookie = new Cookie("searchWord" , null);
		
		cookie.setPath("/");
		
		response.addCookie(cookie);
		
	}
	
	
	// 가게 정렬
	@ResponseBody
	@PostMapping("/sortStoreList")
	public List sortStoreList(int index,int category,int address) {
		System.out.println(index);
		System.out.println(category);
		System.out.println(address);
		 
		List storeList = storeService.sortStoreList(index,category,address);
		
		System.out.println("정렬 = " + storeList);
		return storeList ;
	}
	
	

	
	
	@PostMapping("/clearCookie")
	public String clearCookie(HttpServletResponse response,HttpServletRequest reqeust) throws UnsupportedEncodingException {
		Cookie[] cookies = reqeust.getCookies();
		
		Cookie  cookie = new Cookie("searchWord" , null);
		
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		
		return "redirect:/store/search";
		
	}
	
	
	
	
	
}
