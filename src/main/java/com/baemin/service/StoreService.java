package com.baemin.service;

import java.util.List;
import java.util.Map;

import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.ReviewVO;

public interface StoreService {

	// 매장 목록
	public List getStoreList(int category,int address1); 

	// 매장 상세
	public GoodStoreVO getStoreDetail(int storeNum);
	// 메뉴 리스트
	public List getMenuList(int storeNum);
	
	// 전체 찜 숫자
//	public int goodCount(int storeNum);
	
	// 리뷰 작성
	public void review(ReviewVO reviewVO); // 수정
	
	// 리뷰 완료
	public void reivewComplete(String userId, String orderNum); 
	
	// 리뷰 목록
	public List reviewList(int storeNum);
	
	// 매장 검색
	public List searchStoreList(String storeName, int address);
	
	// 매장 정렬
	public List sortStoreList(int index, int category, int address);

	// 리뷰 수
	public Map reviewCount(int storeNum);
	
	// 리뷰 수정하기 이미지첨부 o
	public void modifyReview(ReviewVO reviewVO);



}
