package com.baemin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baemin.vo.UserVO;

@Repository
public class UserDAOImp implements UserDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.baemin.dao.UserDAO";
	
	// 회원가입
	@Override
	public void join(UserVO userVO) {
		sql.insert(namespace + ".join" , userVO);
	}

	// 로그인
	@Override
	public UserVO login(UserVO userVO) {
		return sql.selectOne(namespace + ".login" , userVO);
	}
	
	// 로그인 유지하기
	@Override
	public UserVO continueLogin(String userId) {
		return sql.selectOne(namespace + ".continueLogin" , userId );
	}

	
	// 회원가입 아이디 체크
	@Override
	public int overlapCheck(UserVO userVO,int functinoNum) {
		
		Map map = new HashMap();
		String overlapCheck = "";
		
		if(functinoNum == 0 ) {
			overlapCheck = userVO.getNickname();
		} else {
			overlapCheck = userVO.getUserId();
		}
		
		
		map.put("overlapCheck", overlapCheck);
		map.put("functinoNum", functinoNum);
		
		
		return sql.selectOne(namespace + ".overlapCheck" , map) ;
	}
	// 회원정보
	@Override
	public UserVO userInfo(String userId) {
		return sql.selectOne(namespace + ".userInfo" , userId);
	}
	
	// 찜하기
	@Override
	public void dibs(String userId, int storeNum) {
		Map map = new HashMap();
		
		map.put("userId",userId);
		map.put("storeNum", storeNum);
		
		sql.update(namespace + ".dibs" , map);
		
	}

	// 찜한 가게 회원
	@Override
	public List dibsLogin(String userId) {
		System.out.println("userId = " + userId);
		return sql.selectList(namespace + ".dibsLogin" , userId) ;
	}
	
	// 찜한 가게 비회원
	@Override
	public List dibsNoLogin(String storeNums) {
		return sql.selectList(namespace + ".dibsNoLogin" ,storeNums) ;
	}
	
	// 내가쓴  리뷰
	@Override
	public List getReviewList(String userId) {
		return sql.selectList(namespace + ".getReviewList" , userId);
	}
	
//	// 네이버 로그인인지 체크 네이버 연동 되어있는지 확인
//	@Override
//	public UserVO naverLoginCheck(Map naverInfo) {
//		return sql.selectOne(namespace + ".naverLoginCheck" , naverInfo);
//	}
//	// 네이버 정보로 로그인
//	@Override
//	public UserVO naverJoin(Map<String, Object> naverInfo) {
//		return sql.selectOne(namespace + ".naverJoin" , naverInfo);
//	}
//
//	// 네이버 연동  NaverLogin을 naverInfo.get("id")로 업데이트
//	@Override
//	public void naverConnection(String id, String email) {
//
//		Map map = new HashMap();
//		
//		map.put("id", id);
//		map.put("email", email);
//		
//		sql.update(namespace + ".naverConnection" , map);
//		
//	}

	
	// 정보수정
	@Override
	public void modifyInfo(String info,String userId,int functionNum) {

		Map map = new HashMap();
		
		map.put("info",info);
		map.put("userId", userId);
		map.put("functionNum", functionNum);
		
		System.out.println(map);
		
		sql.update(namespace + ".modifyInfo",map); 
		
	}
	// 포인트 확인
	@Override
	public List getPoint(String userId) {
		return sql.selectList(namespace + ".getPoint" , userId) ;
	}

	// 상품권 확인
	@Override
	public Map giftCardCheck(String giftCardNum) {
		return sql.selectOne(namespace + ".giftCardCheck" ,giftCardNum);
	}

	// 카카오유저 이메일로 가입되있는지 확인
	@Override
	public Map kakkoIdCheck(String userEmail) {
		return sql.selectOne(namespace + ".kakkoIdCheck" , userEmail); 
	}

	// 카카오 연동하기
	@Override
	public void kakkoConnetion(String userId) {
		sql.update(namespace + ".kakkoConnetion", userId);
		
	}

	// 카카오 회원가입
	@Override
	public void kakkoJoin(UserVO userVO) {
		sql.insert(namespace + ".kakkoJoin" , userVO); 
	}


	
	





}
