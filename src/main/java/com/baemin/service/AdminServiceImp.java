package com.baemin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baemin.dao.AdminDAO;
import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.MenuVO;
import com.baemin.vo.StoreVO;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Service
public class AdminServiceImp implements AdminService{

	@Autowired
	private AdminDAO adminDAO;
		
	// 주문목록 통합
	@Override
	public List getOrderList(int index) {
		return adminDAO.getOrderList(index);
	}
	// 주문목록 통합 +옵션	
	@Override
	public List getOrderListOption(int index) {
		return adminDAO.getOrderListOption(index);
	}
	
	// 관리자페이지 가게 전체
	@Override
	public List getStoreList() {
		// TODO Auto-generated method stub
		return adminDAO.getStoreList();
	}
//	------------------------------------------------
	

	// 매장 등록
	@Override
	public void resiStore(StoreVO storeVO) {
		adminDAO.resiStore(storeVO);
		
	}
	
	// 찜 구분 매장 상세
	@Override
	public GoodStoreVO getStoreDetail(int storeNum, String userId) {
		return adminDAO.getStoreDetail(storeNum, userId);
	}
	
	// 메뉴 추가
	@Override
	public void addMenu(MenuVO menuVO) {
		adminDAO.addMenu(menuVO);
	}
	
	// 메뉴 옵션 추가
	@Override
	public void addMenuOption(MenuVO menuVO, String[] optionName, String[] optionPrice) {
		Map menuOption = new HashMap();
		System.out.println("메뉴옵션 추가");
		
		menuOption.put("menuName", menuVO.getMenuName());
		menuOption.put("storeNum", menuVO.getStoreNum());
		
		System.out.println(optionName.length);
		
		for(int i=0;i<optionName.length;i++) {
			
			if(optionName[i] != null &&  !optionName[i].equals("")) {
				
				try {
					menuOption.put("optionPrice", optionPrice[i]);
				} catch (Exception e) {
					menuOption.put("optionPrice", 0);	
				}
				
				menuOption.put("optionName", optionName[i]);
				
				adminDAO.addMenuOption(menuOption);
				
				
				menuOption.clear();
			}
			
		}
		
	}

	// 매장 정보 수정
	@Override
	public void modifyStore(StoreVO storeVO) {
		adminDAO.modifyStore(storeVO);
		
	}

	// 메뉴 상세
	@Override
	public MenuVO menuDetail(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return adminDAO.menuDetail(menuVO);
	}
	
	// 메뉴 옵션 불러오기
	@Override
	public List getMenuOption(int menuNum) {
		return adminDAO.getMenuOption(menuNum);
	}


	 // 주문번호받아서 주문수락
	@Override
	public void accept(String orderNum,int time) {
		adminDAO.accpet(orderNum,time);
	}
	
	// 주문 거부하기
	@Override
	public void orderCancle(String cancleReason, String orderNum) {
		adminDAO.orderCancle(cancleReason, orderNum);
	}

	// 주문번호받아서 완료
	@Override
	public void complete(String orderNum) {
		adminDAO.complete(orderNum);
		
	}

	// 주문 접수 상세보기
	@Override
	public List getOrderInfo(String orderNum) {
		return adminDAO.getOrderInfo(orderNum);
	}
	
	// 주문 접수 상세보기 옵션
	@Override
	public List getOrderInfoOption(String orderNum) {
		return adminDAO.getOrderInfoOption(orderNum);
	}

	// 사장님 댓글 달기
	@Override
	public String bossComment(String content, String orderNum) {
		content = content.replace("\n","<br>").replaceAll(" ", "&nbsp");
		
		adminDAO.bossComment(content,orderNum);
		
		return content;
		
	}
	// 메뉴 수정하기
	@Override
	public void modifyMenu(MenuVO menuVO) {
		adminDAO.modifyMenu(menuVO);
		
	}

	// 메뉴 옵션 수정하기
	@Override
	public void modifyMenuOption(MenuVO menuVO, String[] optionName, String[] optionPrice,String[] optionNum ) {
		Map menuOption = new HashMap();
		System.out.println("메뉴 옵션 수정하기");
		
		menuOption.put("storeNum", menuVO.getStoreNum());
		menuOption.put("menuNum", menuVO.getMenuNum());
		
		
		if(optionName == null || optionName.length == 0) {
			adminDAO.deleteMenuOption(menuOption);
			return;
		} 
		
		int optionLength = optionName.length;

		for(int i=0;i<optionLength;i++) {
			
			try {
				menuOption.put("optionNum", optionNum[i]);
				menuOption.put("optionPrice", optionPrice[i]);
			} catch (Exception e) {
				menuOption.put("optionNum", 0);
				menuOption.put("optionPrice", 0);
			}
			
			// 옵션번호가 0이면 insert
			// insert시 이름이 없으면 패스
			if(menuOption.get("optionNum").equals(0) && optionName[i].equals("")) {
				continue;
			}
			
			menuOption.put("optionName", optionName[i]);
			
			adminDAO.modifyMenuOption(menuOption);
			
			menuOption.clear();
			
		}
		
//		int optionCheck =adminDAO.optionCheck(menuVO.getMenuNum()); // 메뉴의 옵션 총갯수
//			
//			System.out.println(optionName.length);
//			System.out.println(Arrays.toString(optionName));
//			System.out.println(Arrays.toString(optionPrice));
//			System.out.println(Arrays.toString(optionNum));
//			
//			int storeNum = menuVO.getStoreNum();
//			int menuNum = menuVO.getMenuNum();
//			
//			System.out.println("optionCheck = " + optionCheck);
//			System.out.println("optionName = " + optionName.length);
//			
//			if(optionName.length == 0 ) {
//				menuOption.put("storeNum", menuVO.getStoreNum());
//				menuOption.put("menuNum", menuVO.getMenuNum());
//				menuOption.put("optionNum", optionNum[0]);
//				
//				adminDAO.deleteMenuOption(menuOption);
//				
//				return;
//			}
//			
//		
//		for(int i=0;i<optionCheck;i++) {
//			menuOption.put("menuName", menuVO.getMenuName());
//			menuOption.put("storeNum", menuVO.getStoreNum());
//			menuOption.put("menuNum", menuVO.getMenuNum());
//			menuOption.put("optionName", optionName[i]);
//			menuOption.put("optionPrice", optionPrice[i]);
//			menuOption.put("optionNum", optionNum[i]);
//			
//			if(optionName[i] != null &&  !optionName[i].equals("") && optionPrice[i] !=null && !optionPrice[i].equals("")) {
//								
//				System.out.println("업데이트");
//				adminDAO.updateMenuOption(menuOption);
//				
//			} else {
//				System.out.println("삭제");
//				adminDAO.deleteMenuOption(menuOption);
//			}
//			
//			menuOption.clear();
//		}
//			
//			
//		if(optionCheck < optionName.length ) {
//			
//			for(int i=optionCheck;i<optionName.length;i++) {
//				
//				if(optionName[i] != null &&  !optionName[i].equals("") && optionPrice[i] !=null && !optionPrice[i].equals("")) {
//					menuOption.put("menuName", menuVO.getMenuName());
//					menuOption.put("storeNum", menuVO.getStoreNum());
//					menuOption.put("menuNum", menuVO.getMenuNum());
//					menuOption.put("optionName", optionName[i]);
//					menuOption.put("optionPrice", optionPrice[i]);
//					
//					System.out.println("추가");
//					adminDAO.addMenuOption(menuOption);
//					
//					menuOption.clear();
//					
//				}
//				
//			}
//		}
		
	}
	
	// 메뉴 삭제
	@Override
	public void deleteMenu(int[] deleteNumber, int storeNum) {
		for(int i=0;i<deleteNumber.length;i++) {
			adminDAO.deleteMenu(deleteNumber[i],storeNum);
		}
	}
	

	// 일별 매출
	@Override
	public List salesStatusDay(String date) {
		return adminDAO.salesStatusDay(date);
	}
	// 월별 매출
	@Override
	public Map salesStatusMonth() {
		return adminDAO.salesStatusMonth();
	}






	

	
}
