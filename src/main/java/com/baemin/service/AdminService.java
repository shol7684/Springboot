package com.baemin.service;

import java.util.List;
import java.util.Map;

import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.MenuVO;
import com.baemin.vo.StoreVO;

public interface AdminService {
	
	public List getOrderList(int index); // 주문목록 통합 
	
	public List getOrderListOption(int index); // 주문목록 옵션
	
	public List getStoreList(); // 관리자페이지 가게 전체

	public void resiStore(StoreVO storeVO);	//매장 등록
	
	public GoodStoreVO getStoreDetail(int storeNum, String userId); // 찜 구분 매장 상세

	public void addMenu(MenuVO menuVO); // 메뉴 추가
	
	public void addMenuOption(MenuVO menuVO, String[] optionName, String[] optionPrice); // 메뉴 옵션 추가

	public void modifyStore(StoreVO storeVO); // 매장 정보수정

	public MenuVO menuDetail(MenuVO menuVO); // 메뉴 상세

	public List getMenuOption(int menuNum); // 메뉴 옵션 불러오기
	
	public void accept(String orderNum, int time); // 주문번호받아서 주문수락

	public void orderCancle(String cancleReason, String orderNum); // 주문 거부하기

	public void complete(String orderNum);  // 주문번호받아서 완료

	public List getOrderInfo(String orderNum); // 주문 접수 상세보기
	
	public List getOrderInfoOption(String orderNum); // 주문 접수 상세보기 옵션
	
	public String bossComment(String content,String orderNum); // 사장님 댓글 달기

	public void modifyMenu(MenuVO menuVO);// 메뉴 수정하기

	public void modifyMenuOption(MenuVO menuVO, String[] optionName, String[] optionPrice, String[] optionNum); // 메뉴 옵션 수정하기

	public void deleteMenu(int[] deleteNumber, int storeNum); // 메뉴 삭제

	public List salesStatusDay(String date); // 일별 매출

	public Map salesStatusMonth();// 월별 매출



	

	



	

	


}
