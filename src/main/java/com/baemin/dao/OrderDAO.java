package com.baemin.dao;

import java.util.List;

import com.baemin.vo.OrderVO;
import com.baemin.vo.UserVO;

public interface OrderDAO {

	// 주문하기
	public void order(OrderVO orderVO);
	// 주문 상세 입력
//	public void orderDetail(OrderDetailVO orderDetailVO);
	public void orderDetail(String orderNum, int storeNum, String menuName, int menuPrice, int amount,int cartNum);
	// 주문 상세 옵션 있을때
	public void orderDetailOption(String orderNum, String menuName, String menuOption, String menuOptionPrice,int cartNum);
	// 주문목록
	public List getOrderList(String userId);
	// 주문 상세 불러오기
	public List getOrderDetail(String orderNum,int storeNum);
	// 주문 상세 옵션 불러오기
	public List orderMenuOption(String orderNum,int storeNum);
	// 포인트적립
//	public void addPoint(UserVO userVO);
	public void addPoint(String userId, int point, String storeName);
	

}
