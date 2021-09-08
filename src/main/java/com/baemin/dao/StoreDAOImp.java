package com.baemin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baemin.vo.GoodStoreVO;
import com.baemin.vo.ReviewVO;

@Repository
public class StoreDAOImp implements StoreDAO {
	
	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.baemin.dao.StoreDAO";
	
	// 매장 목록
	@Override
	public List getStoreList(int category,int address1) {

		Map map = new HashMap();
		map.put("category", category);
		map.put("address1", address1);
		
		return sql.selectList(namespace + ".getStoreList" , map) ;
	}

	// 매장 상세
	@Override
	public GoodStoreVO getStoreDetail(int storeNum) {
		return sql.selectOne(namespace + ".getStoreDetail" , storeNum);
	}
	
	// 메뉴 목록
	@Override
	public List getMenuList(int storeNum) {
		return sql.selectList(namespace + ".getMenuList" , storeNum);
	}

	//  전체 찜 숫자 안씀
//	@Override
//	public int goodCount(int storeNum) {
//		return sql.selectOne(namespace + ".goodCount" , storeNum);
//	}

	// 리뷰 작성 수정
	@Override
	public void review(ReviewVO reviewVO) {
		sql.insert(namespace + ".review" ,reviewVO); 
		
	}	
	
	
	
	
	// 리뷰 작성 완료
	@Override
	public void reivewComplete(String userId, String orderNum) {
		Map map = new HashMap();
		
		map.put("orderNum", orderNum);
		map.put("userId", userId);
		
		sql.update(namespace + ".reivewComplete" , map); 
	}
	
	// 리뷰 목록
	@Override
	public List reviewList(int storeNum) {
		return sql.selectList(namespace + ".reviewList" , storeNum) ;
	}

	// 매장 검색
	@Override
	public List searchStoreList(String storeName, int address) {
		Map map = new HashMap();
		
		map.put("storeName", storeName);
		map.put("address", address);
		
		return sql.selectList(namespace + ".searchStoreList" ,map)  ;
	}

	// 매장 정렬
	@Override
	public List sortStoreList(int index, int category, int address) {
		Map map = new HashMap();
		
		
		map.put("index", index);
		map.put("category", category);
		
		map.put("address", address/100);
		
		System.out.println(map);
		
		return sql.selectList(namespace + ".sortStoreList" , map);
	}
	
	// 리뷰 수
	@Override
	public Map reviewCount(int storeNum) {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".reviewCount" , storeNum);
	}
	// 리뷰 수정하기 이미지첨부 o
	@Override
	public void modifyReview(ReviewVO reviewVO) {
		sql.update(namespace + ".modifyReview" , reviewVO);
		
	}



}
