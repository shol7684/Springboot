package com.baemin.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.MenuVO;
import com.baemin.vo.StoreVO;

public interface AdminDAO {
	
	// 주문목록 통합
	public List getOrderList(int index);
	
	// 주문목록 + 옵션
	public List getOrderListOption(int index);
	
	// 관리자페이지 가게 전체
	public List getStoreList();
//	------------------------------------------------	
	
	// 매장 등록
	public void resiStore(StoreVO storeVO);
	
	// 찜 구분 매장 상세
	public GoodStoreVO getStoreDetail(int storeNum, String userId);
	
	// 메뉴 추가
	public void addMenu(MenuVO menuVO);
	
	// 메뉴 옵션 추가
	public void addMenuOption(Map menuOption);
	
	// 매장 정보 수정
	public void modifyStore(StoreVO storeVO);
	
	// 메뉴 상세
	public MenuVO menuDetail(MenuVO menuVO);
	
	// 메뉴 옵션 불러오기
	public List getMenuOption(int menuNum);

	 // 주문번호받아서 주문수락
	public void accpet(String orderNum, int time);
	
	// 주문 거부하기
	public void orderCancle(String cancleReason, String orderNum);
	
	 // 주문번호받아서 완료
	public void complete(String orderNum);
	
	// 주문 접수 상세보기
	public List getOrderInfo(String orderNum);
	
	// 사장님 댓글 달기
	public void bossComment(String content, String orderNum);

	// 메뉴 수정하기
	public void modifyMenu(MenuVO menuVO);
	
	// 메뉴 옵션 확인
//	public int optionCheck(int menuNum);
	
	// 메뉴 옵션 수정하기
//	public void updateMenuOption(Map menuOption);
	
	// 메뉴 옵션 수정하기 2번
	public void modifyMenuOption(Map menuOption);

	// 메뉴 삭제
	public void deleteMenu(int deleteNumber, int storeNum);
	
	// 메뉴 옵션 삭제
	public void deleteMenuOption(Map menuOption);
	
	// 주문 접수 상세보기 옵션
	public List getOrderInfoOption(String orderNum);

	//  일별 매출
	public List salesStatusDay(String date);
	
	// 월별 매출
	public Map salesStatusMonth();



	
	
	



}
