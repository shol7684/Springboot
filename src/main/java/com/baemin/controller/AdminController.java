package com.baemin.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baemin.dao.AdminDAO;
import com.baemin.service.AdminService;
import com.baemin.service.OrderService;
import com.baemin.service.StoreService;
import com.baemin.service.UserService;
import com.baemin.utils.CookieManager;
import com.baemin.utils.UploadFileUtils;
import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.MenuVO;
import com.baemin.vo.StoreVO;
import com.baemin.vo.UserVO;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private CookieManager cookieManager;
	
	// 주문 대기 목록
	@ResponseBody
	@PostMapping("/admin/orderList")
	public Map orderList(Model model,int index) {
		System.out.println("index = " + index);

		List orderList = adminService.getOrderList(index);
		List orderListOption = adminService.getOrderListOption(index); 
		
		Map orderListMap = new HashMap();
		
		orderListMap.put("orderList", orderList);
		orderListMap.put("orderListOption", orderListOption);
		
		System.out.println(index + " 번 목록 " +  orderList);
		System.out.println ("옵션 목록 = " + orderListOption);
			
		return orderListMap;
	}
	
	// 관리자 페이지
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		
		List storeList =adminService.getStoreList();
		
		model.addAttribute("storeList",storeList);
		
		return "admin/admin";
	}
	
	// 매장 등록
	@Transactional
	@PostMapping("/admin")
	public String postAdmin(HttpServletRequest request,StoreVO storeVO,MultipartFile img) throws IOException, Exception {
		
		if(!img.isEmpty()) {
			System.out.println("이미지 첨부");
			
			System.out.println("이미지 o");
			 
			storeVO.setStoreImg(UploadFileUtils.upload(img));
			 
			storeVO.setStoreThumb(UploadFileUtils.thumbUpload(img));
			 
		} else { 
			
			String fileName = File.separator + "upload" + File.separator + "none.gif";
		 
			storeVO.setStoreImg(fileName);
		 	storeVO.setStoreThumb(fileName);
		}
		
		adminService.resiStore(storeVO);
		
		System.out.println(storeVO);
		
		return "redirect:/admin";
	}

	
	
	
	// 가게 상세
	@Transactional
	@GetMapping("/admin/detail/{storeNum}")
	public String getDetail(@PathVariable int storeNum,Model model,HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
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
		
		List reviewList = storeService.reviewList(storeNum);	// 리뷰 리스트
		List menuList = storeService.getMenuList(storeNum); // 메뉴리스트
//		Map<String,Object> review  = storeService.reviewCount(storeNum); // 리뷰 수,사장님 댓글 수,주문 수, 찜 수

		
		model.addAttribute("reviewList",reviewList); 
		model.addAttribute("storeVO",goodStoreVO); 
		model.addAttribute("menuList",menuList);
		
		System.out.println("------------------- 매장 상세 -------------------");
		return "admin/detail";
	}
	
	//메뉴 추가
	@Transactional
	@PostMapping("/addMenu")
	public String addMenu(MenuVO menuVO,StoreVO storeVO,MultipartFile img,String[] optionName,
			String[] optionPrice) throws IOException, Exception {
		System.out.println("메뉴 추가");
		
		if(!img.isEmpty()) {
		 
			menuVO.setMenuImg(UploadFileUtils.upload(img));
			menuVO.setMenuThumb(UploadFileUtils.thumbUpload(img));
		 
		} else { 
		
			String fileName = File.separator + "upload" + File.separator + "none.gif";
			menuVO.setMenuImg(fileName);
			menuVO.setMenuThumb(fileName);
		}
		
		adminService.addMenu(menuVO);
		adminService.addMenuOption(menuVO,optionName,optionPrice);
		
		return "redirect:/admin/detail/" + menuVO.getStoreNum();
	}
	
	//메뉴 수정
	@Transactional
	@PostMapping("/modifyMenu")
	public String modifyMenu(MenuVO menuVO,StoreVO storeVO,MultipartFile img,String[] optionName,
			String[] optionPrice, String[] optionNum) throws IOException, Exception {
		
		System.out.println("메뉴 수정");
		
		if(!img.isEmpty()) {
			 
			menuVO.setMenuImg(UploadFileUtils.upload(img));
			menuVO.setMenuThumb(UploadFileUtils.thumbUpload(img));
			 
		}
		System.out.println(menuVO);
		System.out.println(storeVO);
		System.out.println(Arrays.toString(optionName));
		System.out.println(Arrays.toString(optionPrice));
		
		adminService.modifyMenu(menuVO);
		adminService.modifyMenuOption(menuVO,optionName,optionPrice,optionNum);
		
		return "redirect:/admin/detail/" + menuVO.getStoreNum();
	}
	
	// 메뉴 삭제
	@ResponseBody
	@PostMapping("/deleteMenu")
	public void deleteMenu(int[] deleteNumber, int storeNum) {
		
		System.out.println(Arrays.toString(deleteNumber));
		
		System.err.println(storeNum);
		
		adminService.deleteMenu(deleteNumber,storeNum);
		
	}
	
	// 가게 정보수정
	@Transactional
	@PostMapping("/storeModify")
	public String storeModify(MenuVO menuVO,StoreVO storeVO,MultipartFile img,HttpServletRequest request) throws IOException, Exception {
		System.out.println("가게 정보수정 ");
		
		
		if(!img.isEmpty()) {
			System.out.println("삭제 " + storeVO.getStoreThumb() );
			new File(UploadFileUtils.uploadPath +  storeVO.getStoreThumb()).delete();
			new File(UploadFileUtils.uploadPath +  storeVO.getStoreImg()).delete();
			
			storeVO.setStoreImg(UploadFileUtils.upload(img));
			storeVO.setStoreThumb(UploadFileUtils.thumbUpload(img));
			
		} 
//		
		adminService.modifyStore(storeVO);
		
		return "redirect:/admin/detail/" + menuVO.getStoreNum();
		
	}
	
	// 매출현황 페이지
	@GetMapping("/admin/sales")
	public String sales() {
		
		
		return "admin/sales";
	}
	
	// 일별 매출현황
	@ResponseBody
	@PostMapping("/admin/salesStatusDay")
	public List salesStatusDay(String date) {
		
		if(date != null) {
			date = date + "-01"; // 2021-05 --> 2021-05-01
		}
		
		System.out.println(date);
		
		List salesStatus = adminService.salesStatusDay(date);
		
		System.out.println(salesStatus);
		
		return salesStatus;
		
	}
	// 월별 매출현황
	@ResponseBody
	@PostMapping("/admin/salesStatusDayMonth")
	public Map salesStatusMonth() {
		
		Map salesStatus = adminService.salesStatusMonth();
		
		System.out.println(salesStatus);
		
		System.out.println(salesStatus.size());
		
		return salesStatus;
		
	}
	
	
	// 메뉴 리스트 클릭
	@Transactional
	@ResponseBody
	@PostMapping("/menuDetail")
	public Map menuDetail(MenuVO menuVO) {
		System.out.println("------------- 메뉴 리스트 클릭 ------------");
		System.out.println("메뉴 상세 = ");
		System.out.println(menuVO);
		
		MenuVO menuDetail = adminService.menuDetail(menuVO);
		List menuOption = adminService.getMenuOption(menuVO.getMenuNum());
		System.out.println("메뉴 상세 = " + menuDetail);
		System.out.println("메뉴 옵션 = " + menuOption);
		
		Map map = new HashMap();
		map.put("menuDetail", menuDetail);
		map.put("menuOption", menuOption);
		System.out.println(map);
		
		System.out.println("------------- 메뉴 리스트 클릭 ------------");
		return map;
		
	}
	
	
	
	
	// 완료버튼 클릭
	@Transactional
	@ResponseBody
	@PostMapping("/admin/complete")
	public Map complete(String orderNum,HttpServletRequest request) {
		
		System.out.println("완료하기");
		System.out.println(orderNum);
		
		adminService.complete(orderNum); // 주문번호받아서 완료
		
		List orderList = adminService.getOrderList(1); // 처리중인 주문목록
		
		List orderListOption = adminService.getOrderListOption(1); 
		
		Map orderListMap = new HashMap();
		
		orderListMap.put("orderList", orderList);
		orderListMap.put("orderListOption", orderListOption);
		
		
		return orderListMap;
		
	}
	
	// 목록에서 접수하기 클릭
	@Transactional
	@ResponseBody
	@PostMapping("/admin/orderDetail")
	public Map orderDetail (String orderNum,Model model) {
	
		
		List orderDetailMenu = adminService.getOrderInfo(orderNum);
		List orderDetailOption = adminService.getOrderInfoOption(orderNum);
		
		Map orderDetail = new HashMap();
		
		orderDetail.put("orderDetailMenu", orderDetailMenu);
		orderDetail.put("orderDetailOption", orderDetailOption);
		
		System.out.println("접수하기");
		System.out.println(orderNum);
		System.out.println(orderDetailMenu); 
		System.out.println(orderDetailOption);
		
		return orderDetail;
	}

	// 모달에서 주문 수락
	@Transactional
	@ResponseBody
	@PostMapping("/admin/accept")
	public Map accept(String orderNum,int time,int index) {
		
		System.out.println(orderNum);
		System.out.println(time);
		System.out.println("index  = " + index);
		
		adminService.accept(orderNum,time); // 주문번호받아서 주문수락
		
		List orderList = adminService.getOrderList(index); // 접수대기중인 주문목록
		List orderListOption = adminService.getOrderListOption(index); 
		
		Map orderListMap = new HashMap();
		
		orderListMap.put("orderList", orderList);
		orderListMap.put("orderListOption", orderListOption);
		
		System.out.println(orderList);
		
		return orderListMap;
	}
	
	// 모달에서 주문 거부하기
	@Transactional
	@ResponseBody
	@PostMapping("/admin/orderCancle")
	public Map orderCancle(String cancleReason,String orderNum) {
		
		System.out.println(cancleReason);
		System.out.println(orderNum);
		
		adminService.orderCancle(cancleReason, orderNum);
		
		List orderList = adminService.getOrderList(0); // 접수대기중인 주문목록
		List orderListOption = adminService.getOrderListOption(0); 
		
		Map orderListMap = new HashMap();
		
		orderListMap.put("orderList", orderList);
		orderListMap.put("orderListOption", orderListOption);
		
		return orderListMap;
		
	}
	
	
	
	
	// 사장님 댓글 달기
	@ResponseBody
	@PostMapping("/admin/bossComment")
	public String bossComment(String content,String  orderNum) {
		
		return adminService.bossComment(content,orderNum);
	}
	
	// 관리자페이지 현재 탭 저장
	@ResponseBody
	@PostMapping("/saveTabCookie")
	public void cookie (HttpServletRequest request,HttpServletResponse response,String tabNum ) {
			
		System.out.println("tabNum = " + tabNum);
		Cookie cookie = new Cookie("tabNum" , tabNum);
		
		cookie.setPath("/");
		
		response.addCookie(cookie);
		
	}
	
	// 관리자페이지 저장 탭 불러오기
	@ResponseBody
	@PostMapping("/tabCookie")
	public int getCookie (HttpServletRequest request) {
		
		System.out.println("쿠키겟");
		
		Cookie[] cookies = request.getCookies();
		int tabNum = 0;
		
		for(int i=0;i<cookies.length;i++) {
			
			if(cookies[i].getName().equals("tabNum")) {
				
				tabNum = Integer.parseInt(cookies[i].getValue());
				System.out.println("tabNum=" + tabNum);
				return tabNum;
			}
		}
		
		return tabNum;
	}

	
	
	
	
	@ResponseBody
	@PostMapping("/clear")
	public String clear(HttpSession session) {
		System.out.println("세션 초기화");
		session.invalidate();
		
		
		return "";
	}
	
	
	
	
	
	
	
	
}
