package com.baemin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baemin.vo.OrderVO;
import com.baemin.vo.UserVO;

@Repository
public class OrderDAOImp implements OrderDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.baemin.dao.OrderDAO";
	
	// 주문하기
	@Override
	public void order(OrderVO orderVO) {
		sql.insert(namespace + ".order" , orderVO);
		
	}
	// 주문상세 입력
//	@Override
//	public void orderDetail(OrderDetailVO orderDetailVO) {
//		sql.insert(namespace + ".orderDetail" , orderDetailVO);
//		
//	}
	// 주문상세 입력
	@Override
	public void orderDetail(String orderNum, int storeNum, String menuName, int menuPrice, int amount,int cartNum) {
		Map map = new HashMap();
		
		map.put("orderNum", orderNum);
		map.put("storeNum", storeNum);
		map.put("menuName", menuName);
		map.put("menuPrice", menuPrice);
		map.put("amount", amount);
		map.put("cartNum", cartNum);
		
		sql.insert(namespace + ".orderDetail" , map);
		
	}
	// 주문상세 입력 옵션 있을때
	@Override
	public void orderDetailOption(String orderNum, String menuName, String menuOption, String menuOptionPrice,int cartNum) {
		Map map = new HashMap();
		
		map.put("orderNum", orderNum);
		map.put("menuName", menuName);
		map.put("menuOption", menuOption);
		map.put("menuOptionPrice", menuOptionPrice);
		map.put("cartNum", cartNum);
		
		sql.insert(namespace + ".orderDetailOption" , map);
		
	}
	// 주문목록
	@Override
	public List getOrderList(String userId) {
		return sql.selectList(namespace +".getOrderList" , userId);
		
	}
	// 주문 상세 불러오기
	@Override
	public List getOrderDetail(String orderNum,int storeNum) {
		Map map = new HashMap();
		
		map.put("orderNum", orderNum);
		map.put("storeNum", storeNum);
		
		return sql.selectList(namespace + ".getOrderDetail" ,map);
	}
	// 주문 상세 옵션 불러오기
	@Override
	public List orderMenuOption(String orderNum,int storeNum) {
		Map map = new HashMap();
		
		map.put("orderNum", orderNum);
		map.put("storeNum", storeNum);
		
		return sql.selectList(namespace + ".orderMenuOption" ,map) ;
	}
	// 포인트적립
//	@Override
//	public void addPoint(UserVO userVO) {
//		sql.update(namespace + ".addPoint" ,userVO);
//		
//	}
	@Override
	public void addPoint(String userId, int point, String storeName) {
		Map map = new HashMap();
		
		map.put("userId", userId);
		map.put("point", point);
		map.put("storeName", storeName);
		
		sql.update(namespace + ".addPoint" ,map);
		
	}



}
