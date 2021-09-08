package com.baemin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baemin.dao.OrderDAO;
import com.baemin.vo.OrderVO;
import com.baemin.vo.UserVO;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderDAO orderDAO;
	
	// 주문하기
	@Override
	public void order(OrderVO orderVO) {
		orderVO.setRequest(orderVO.getRequest().replace("\r\n", "<br>").replace(" ", "&nbsp"));
		orderDAO.order(orderVO);

	}
	// 주문상세 입력
	@Override
	public void orderDetail(String orderNum, int storeNum, String menuName, int menuPrice, int amount,int cartNum) {
		orderDAO.orderDetail(orderNum,storeNum,menuName,menuPrice,amount,cartNum);
		
	}
	// 주문상세 옵션 있을때
	@Override
	public void orderDetailOption(String orderNum, String menuName, String menuOption, String menuOptionPrice,int cartNum) {
		orderDAO.orderDetailOption(orderNum, menuName, menuOption, menuOptionPrice,cartNum);
		
	}
	
	// 주문목록
	@Override
	public List getOrderList(String userId) {
		return orderDAO.getOrderList(userId);
		
	}
	// 주문상세 불러오기
	@Override
	public List getOrderDetail(String orderNum,int storeNum) {
		return orderDAO.getOrderDetail(orderNum,storeNum);
	}
	// 주문상세 옵션 불러오기
	@Override
	public List orderMenuOption(String orderNum,int storeNum) {
		// TODO Auto-generated method stub
		return orderDAO.orderMenuOption(orderNum,storeNum);
	}
	// 포인트적립
	@Override
	public void addPoint(String userId, int point, String storeName) {
		orderDAO.addPoint(userId,point,storeName);
		
	}


}
