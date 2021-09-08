package com.baemin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baemin.vo.CartVO;
import com.baemin.vo.MenuVO;

@Controller
public class CartContoller {
	
	// 카트리스트
	@ResponseBody
	@PostMapping("/cartList")
	public List cartList(HttpSession session) {
		
		List cartList = (List)session.getAttribute("cartList"); // 세션에 저장된 장바구니 불러오기
		
		return cartList;
		
	}
	// 장바구니 담기
	@ResponseBody
	@PostMapping("/addCart")
	public 
	List addCart(Model model,MenuVO menuVO, CartVO cartVO,String[] menuOptionPrice,
			String[] menuOption, HttpSession session,int deleveryTip) {
		
		System.out.println(menuVO);
		System.out.println(cartVO);
		System.out.println(deleveryTip);
		System.out.println("menuOption = " + Arrays.toString(menuOption));
		System.out.println("menuOptionPrice= " + Arrays.toString(menuOptionPrice));
		
		List cartList = new ArrayList(); 
		List option = new ArrayList();
		List optionPrice = new ArrayList();
		
		if(session.getAttribute("cartList") != null) {
			cartList = (List) session.getAttribute("cartList");
		}
		cartVO.setMenuOption(menuOption);
		cartVO.setMenuOptionPrice(menuOptionPrice);
		cartList.add(cartVO);
		session.setAttribute("cartList", cartList);
		
		System.out.println("------------- 장바구니 담기 성공 ------------");
		
		return cartList;
		
	}
		
	
	// 장바구니 1개 삭제
	@ResponseBody
	@PostMapping("/deleteCartOne")
	public List deleteCartOne(Model model,int index,HttpSession session) {
		System.out.println(index);
		
		List cartList = (List)session.getAttribute("cartList");
		
		System.out.println(cartList);
		
		cartList.remove(index);
		
		System.out.println(cartList);
		
		
		
		System.out.println("------------- 1개 삭제 성공 -------------");
		
		return cartList;
	}
	
	
	
	// 장바구니 전체 삭제
	@ResponseBody
	@PostMapping("/deleteCartAll")
	public void deleteCartAll(HttpSession session) {
		
		session.removeAttribute("cartList");
		
		System.out.println("------------- 전체 삭제 성공 -------------");
		
	}
	
	
	// 주문페이지 수량 변경
	@ResponseBody
	@PostMapping("/amountModify")
	public void amountModify(HttpSession session,int cartNum,int amount) {
		List cartList = (List) session.getAttribute("cartList");
		
		System.out.println(cartList.get(cartNum));
		System.out.println("cartNum = " + cartNum);
		System.out.println("amount = " + amount);
		
		CartVO cartVO = (CartVO) cartList.get(cartNum);
		cartVO.setAmount(amount);
		
		cartList.set(cartNum, cartVO);
		
	}
		
		
		
		
}
