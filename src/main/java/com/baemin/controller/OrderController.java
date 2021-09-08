package com.baemin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baemin.service.OrderService;
import com.baemin.utils.OrderNumber;
import com.baemin.vo.CartVO;
import com.baemin.vo.OrderDetailVO;
import com.baemin.vo.OrderVO;
import com.baemin.vo.UserVO;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	// 주문하기
	@GetMapping("/order")
	public String order(HttpSession session,Model model,RedirectAttributes rttr) {
		System.out.println("------------------- 주문하기 -------------------");
		List cartList =(List) session.getAttribute("cartList");
		Map address = (Map) session.getAttribute("address");
		System.out.println(cartList);
		System.out.println(address);
		 
		if(cartList == null) {
			rttr.addFlashAttribute("msg" , "장바구니가 비었습니다");
			return "redirect:/";
		}
		
		
		UserVO user = (UserVO) session.getAttribute("user");
		
		if(user != null) {
			String userId = user.getUserId();
			model.addAttribute("userId",userId);
		}
		
		model.addAttribute("cartList",cartList);
		model.addAttribute("address",address);
		
		
		System.out.println("------------------- 주문하기 -------------------");
		return "order/order"; 
	}
		
	// 주문하기 
	@Transactional
	@PostMapping("/order")
	public String orderList(CartVO cartVO,OrderVO orderVO,Model model,
			UserVO userVO,HttpSession session) {
		System.out.println(cartVO);
		System.out.println(cartVO.getStoreNum());
		
		
		
		List cartList = (List) session.getAttribute("cartList");
		
		System.out.println("cartList = " + cartList);
		
		
		String orderNum= OrderNumber.orderNumber();
	    
	    orderVO.setOrderNum(orderNum);
	    int cartTotal = cartVO.getTotal();
	    int total = cartVO.getDeleveryTip() + cartTotal;
	    
	    orderVO.setTotal(total);
	    
	    System.out.println(orderVO);
	    
	    UserVO user = (UserVO) session.getAttribute("user");
	    String userId = "";
	    if(user==null) {
	    	String guest =(String)session.getAttribute("guest"); 
	    	userId ="guest";
	    } else {
	    	userId = user.getUserId();
	    }
	    orderVO.setUserId(userId);

	    System.out.println(orderVO);
	    
		orderService.order(orderVO);
		
		model.addAttribute("orderVO",orderVO);
		
		
		for(int i=0;i<cartList.size();i++) {
			 cartVO = (CartVO) cartList.get(i);
			 int cartNum = i; // 같은 메뉴를 옵션만 다르게 했을때 구분
			 int storeNum = cartVO.getStoreNum();
			 int amount = cartVO.getAmount();
			 int menuPrice = cartVO.getMenuPrice();
			 String menuNum= cartVO.getMenuNum();
			 String menuName = cartVO.getMenuName();
			 
			 orderService.orderDetail(orderNum,storeNum,menuName,menuPrice,amount,cartNum);
			 
			 String[] menuOption = cartVO.getMenuOption();
			 String[] menuOptionPrice = cartVO.getMenuOptionPrice();
			 
			 if(menuOption != null) {
				 for(int j=0;j<menuOption.length;j++) {
					 orderService.orderDetailOption(orderNum,menuName,menuOption[j],menuOptionPrice[j],cartNum);
					 
				 }
			 }
		}
		
		if(user != null) { // 회원이면 포인트적립
			System.out.println("적립 포인트 = " + cartTotal/10 + user.getPoint());
			
			int point = cartTotal/10 ;
			String storeName = orderVO.getStoreName();
			
			System.out.println("현재포인트 = " + point);
			userVO.setUserId(userId);
			userVO.setPoint(point);
			
			orderService.addPoint(userId,point,storeName);
			
			if(orderVO.getUsePoint() != 0) {
				orderService.addPoint(userId,(orderVO.getUsePoint() * -1),storeName);
			}
			
			
			user.setPoint(user.getPoint() + point - orderVO.getUsePoint());
			session.setAttribute("user", user); // 세션도 업데이트
			
			System.out.println("회원 포인트 적립 " + point); 
		}
		
		session.removeAttribute("cartList");
		
		System.err.println("=======================주문하기=========================");
		return "redirect:/orderList";
	}
	
	//주문 목록
	@GetMapping("/orderList")
	public String getOrderList(HttpSession session,Model model)  {
		System.err.println("=======================주문 목록=========================");
		UserVO user =(UserVO)session.getAttribute("user");
		System.out.println(user);
		
		List orderList;
		
		if(user == null) {
			String userId = (String) session.getAttribute("guest");
			orderList = orderService.getOrderList(userId);
			model.addAttribute("orderList",orderList);
		} else {
			String userId = user.getUserId();
			orderList = orderService.getOrderList(userId);
			model.addAttribute("orderList",orderList);
			model.addAttribute("user",user);
		}
		
		System.out.println(orderList);
		
		
		
		
		System.err.println("=======================주문 목록=========================");
		
		
		return "order/orderList";
	}
	
	// 주문상세 불러오기
	@GetMapping("/orderDetail/{storeNum}/{orderNum}")
	public String orderDetail(@PathVariable(value = "orderNum") String orderNum,Model model,HttpSession session,
							  @PathVariable(value = "storeNum") int storeNum) {
		
		System.err.println("=======================주문상세 불러오기=========================");
		List orderDetail = orderService.getOrderDetail(orderNum,storeNum);
		List orderMenuOption = orderService.orderMenuOption(orderNum,storeNum);
		
		UserVO user = (UserVO) session.getAttribute("user");
		
		
		model.addAttribute("orderDetail",orderDetail);
		model.addAttribute("orderMenuOption",orderMenuOption);
		
		System.out.println(orderDetail);
		
		System.err.println("=======================주문상세 불러오기=========================");
		return "order/orderDetail";
	}
	
	
	
	
	
}
