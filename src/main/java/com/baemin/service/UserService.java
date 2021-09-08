package com.baemin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baemin.vo.UserVO;

public interface UserService {
	// 회원 가입
	public void join(UserVO userVO);
	
	// 로그인
	public int login(UserVO userVO, HttpSession session, String continueLogin, HttpServletRequest request, HttpServletResponse response )  throws UnsupportedEncodingException;
	
	// 로흐인 유지하기
	public UserVO continueLogin(String userId);
	
	// 회원가입 아이디 체크 or 닉네임 체크
	public int overlapCheck(UserVO userVO, int functionNum);
	
	// 카카오 로그인
	public Map kakkoIdCheck(String userEamil);
	
	// 회원정보
	public UserVO userInfo(String userId);
	
	// 찜하기
	public void dibs(String userId, int storeNum);	
	
	// 회원 찜한 가게
	public List dibsLogin(String userId);
	
	// 비회원 찜한 가게
	public List dibsNoLogin(String storeNums);
	
	// 내가쓴  리뷰
	public List getReviewList(String userId);
	
//	// 네이버 로그인인지 체크
//	public UserVO naverLoginCheck(Map naverInfo);
//	
//	// 네이버 정보로 로그인
//	public UserVO naverJoin(Map<String, Object> naverInfo);
//	
//	// 네이버 연동  NaverLogin을 naverInfo.get("id")로 업데이트
//	public void naverConnection(String id, String email);
	
	// 정보 수정
	public void modifyInfo(String info, String userId, int functionNum);
	
	// 포인트 확인
	public List getPoint(String userId);
	
	// 상품권 확인
	public Map giftCardCheck(String giftCardNum);

	// 카카오 연동
	public void kakkoConnetion(String userId);

	// 카카오 회원가입
	public void kakkoJoin(UserVO userVO);



	

	
	
	
	


	
	
	

}
