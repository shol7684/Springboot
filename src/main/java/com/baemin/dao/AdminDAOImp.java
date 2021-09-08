package com.baemin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.MenuVO;
import com.baemin.vo.StoreVO;

@Repository
public class AdminDAOImp implements AdminDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.baemin.dao.AdminDAO";
	
	
	// 주문목록 통합
	@Override
	public List getOrderList(int index) {
		String deleveryState = "";  
		
		if(index == 0) {
			deleveryState = "주문대기";
		} else if (index  == 1) {
			deleveryState = "처리 중";
		} else if (index == 2) {
			deleveryState = "배달 완료";
		}
		
		return sql.selectList(namespace + ".getOrderList" , deleveryState);
	}
	// 주문목록 + 옵션
	@Override
	public List getOrderListOption(int index) {
		String deleveryState = "";  
		
		if(index == 0) {
			deleveryState = "주문대기";
		} else if (index  == 1) {
			deleveryState = "처리 중";
		} else if (index == 2) {
			deleveryState = "배달 완료";
		}
		
		return sql.selectList(namespace + ".getOrderListOption" ,deleveryState);
	}
	// 관리자페이지 가게 전체
	@Override
	public List getStoreList() {
		
		return sql.selectList(namespace + ".getStoreList");
	}
	
//	------------------------------------------------
	// 매장 등록
	@Override
	public void resiStore(StoreVO storeVO) {
		System.out.println("DAO = " + storeVO);
		sql.insert(namespace + ".resiStore" , storeVO);
		
	}
	
	// 찜 구분 매장 상세
	@Override
	public GoodStoreVO getStoreDetail(int storeNum, String userId) {
			
		Map map = new HashMap();
		
		map.put("storeNum", storeNum);
		map.put("userId", userId);
		
		return sql.selectOne(namespace + ".getStoreDetail" , map);
	}
	
	// 메뉴 추가
	@Override
	public void addMenu(MenuVO menuVO) {
		System.out.println("DAO= " + menuVO);
		sql.insert(namespace + ".addMenu" , menuVO);
	}
	
	// 메뉴 옵션 추가
	@Override
	public void addMenuOption(Map menuOption) {
		sql.insert(namespace + ".addMenuOption" , menuOption);
		
	}

	// 매장 정보 수정
	@Override
	public void modifyStore(StoreVO storeVO) {
		sql.update(namespace + ".modifyStore", storeVO);
		
	}

	// 메뉴 상세
	@Override
	public MenuVO menuDetail(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".menuDetail" , menuVO);
	}
	
	// 메뉴 옵션 불러오기
	@Override
	public List getMenuOption(int menuNum) {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".getMenuOption" , menuNum) ;
	}

	 // 주문번호받아서 주문수락
	@Override
	public void accpet(String orderNum,int time) {
		Map map = new HashMap();
		
		map.put("orderNum",orderNum);
		map.put("time", time);
		
		sql.update(namespace + ".accept" , map); 
	}
	
	// 주문 거부하기
	@Override
	public void orderCancle(String cancleReason, String orderNum) {
		Map map = new HashMap();
		
		map.put("cancleReason",cancleReason);
		map.put("orderNum", orderNum);
		
		sql.update(namespace + ".orderCancle" , map); 
	}

	 // 주문번호받아서 완료
	@Override
	public void complete(String orderNum) {
		sql.update(namespace +".complete" ,orderNum); 
		
	}

	// 주문 접수 상세보기
	@Override
	public List getOrderInfo(String orderNum) {
		return sql.selectList(namespace + ".getOrderInfo" , orderNum) ;
	}
	
	// 주문 접수 상세보기 옵션
	@Override
	public List getOrderInfoOption(String orderNum) {
		return sql.selectList(namespace + ".getOrderInfoOption" , orderNum) ;
	}



	// 사장님 댓글 달기
	@Override
	public void bossComment(String content, String orderNum) {
		Map map = new HashMap();
		
		map.put("content",content);
		map.put("orderNum", orderNum);
		
		sql.update(namespace + ".bossComment" , map); 
		
	}
	
	// 메뉴 수정하기
	@Override
	public void modifyMenu(MenuVO menuVO) {
		sql.update(namespace + ".modifyMenu" , menuVO);
	}
	
	// 메뉴 옵션 수정하기 2번
	@Override
	public void modifyMenuOption(Map menuOption) {
		sql.update(namespace + ".modifyMenuOption" , menuOption);
	}
	
	// 메뉴 삭제
	@Override
	public void deleteMenu(int deleteNumber, int storeNum) {
		Map map = new HashMap();
		
		map.put("menuNum", deleteNumber);
		map.put("storeNum", storeNum);

		
		sql.delete(namespace + ".deleteMenu" , map);
	}

	// 메뉴 옵션 삭제
	@Override
	public void deleteMenuOption(Map menuOption) {
		sql.delete(namespace + ".deleteMenuOption" , menuOption);
		
	}
	
	// 일별 매출
	@Override
	public List salesStatusDay(String date) {
		return sql.selectList(namespace + ".salesStatusDay" , date);
	}
	
	// 월별 매출
	@Override
	public Map salesStatusMonth() {
		return sql.selectOne(namespace + ".salesStatusMonth");
	}





	
}
