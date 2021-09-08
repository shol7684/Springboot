package com.baemin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baemin.dao.StoreDAO;
import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.ReviewVO;

@Service
public class StoreServiceImp implements StoreService {
	
	@Autowired
	private StoreDAO storeDAO;
	
	// 매장 목록
	@Override
	public List getStoreList(int category,int address1) {
		return storeDAO.getStoreList(category,address1);
	}

	
	// 매장 상세
	@Override
	public GoodStoreVO getStoreDetail(int storeNum) {
		return storeDAO.getStoreDetail(storeNum);
	}

	// 메뉴 리스트
	@Override
	public List getMenuList(int storeNum) {
		return storeDAO.getMenuList(storeNum);
	}
	
	// 전체 찜 숫자
//	@Override
//	public int goodCount(int storeNum) {
//		// TODO Auto-generated method stub
//		return storeDAO.goodCount(storeNum);
//	}

	// 리뷰 작성 
	@Override
	public void review(ReviewVO reviewVO) {
		
		reviewVO.setContent(reviewVO.getContent().replace("\r\n","<br>").replace(" ", "&nbsp") );
		
		storeDAO.review(reviewVO);
	}
	

	// 리뷰 완료
	@Override
	public void reivewComplete(String userId, String orderNum) {
		storeDAO.reivewComplete(userId,orderNum);
		
	}
	
	// 리뷰 수정하기 
	@Override
	public void modifyReview(ReviewVO reviewVO) {
		
		reviewVO.setContent(reviewVO.getContent().replace("\r\n","<br>").replace(" ", "&nbsp"));
		
		storeDAO.modifyReview(reviewVO);
		
	}
	
	// 리뷰 목록
	@Override
	public List reviewList(int storeNum) {
		return storeDAO.reviewList(storeNum);
	}

	// 매장 검색
	@Override
	public List searchStoreList(String storeName, int address) {
		return storeDAO.searchStoreList(storeName,address);
	}

	// 매장 정렬
	@Override
	public List sortStoreList(int index, int category, int address) {
		return storeDAO.sortStoreList(index, category, address);
	}

	// 리뷰 수 / 사장님 리뷰 수 / 주문 수 / 별점
	@Override
	public Map reviewCount(int storeNum) {

//		Map<String,Object> review  = storeService.reviewCount(storeNum); // 리뷰 수 join 실패
		Map review =storeDAO.reviewCount(storeNum);
		
		int bossComment = Integer.parseInt(String.valueOf(review.get("BOSSCOMMENT")));
		int reviewCount = Integer.parseInt(String.valueOf(review.get("REVIEWCOUNT")));
		int orderCount = Integer.parseInt(String.valueOf(review.get("ORDERCOUNT")));
		float score = Float.parseFloat(String.valueOf(review.get("SCORE")));
		
		review.clear();
		
		review.put("bossComment", bossComment);
		review.put("reviewCount", reviewCount);
		review.put("orderCount", orderCount);
		review.put("score", score);
		
		return review;
	}






}
