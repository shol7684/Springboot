package com.baemin.service;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baemin.dao.UserDAO;
import com.baemin.utils.CookieManager;
import com.baemin.vo.UserVO;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CookieManager cookieManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 회원가입
	@Override
	public void join(UserVO userVO) {
		
		String encoderPwd = passwordEncoder.encode(userVO.getUserPwd());
		
		userVO.setUserPwd(encoderPwd);
		
		userDAO.join(userVO);
	}

	// 로그인
	@Override
	public int login(UserVO userVO, HttpSession session, String continueLogin, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		UserVO user = userDAO.login(userVO);
		
		// 아이디 없으면
		if(user == null) {
			return 0;
		} 
		
		// 아이디 있는데
		if(user != null ) {
			
			String dbPwd = user.getUserPwd();
			String inputPwd = userVO.getUserPwd();
			
			
			
			// 비번 틀리면
			if(passwordEncoder.matches(inputPwd, dbPwd) == false) {
				return 1;
			} 
			
			// 비번 맞으면	
			if(passwordEncoder.matches(inputPwd, dbPwd) == true) {	
			
				if("on".equals(continueLogin)) {
					Set userId = new HashSet();
					
					userId.add(userVO.getUserId());

					cookieManager.setCookie(userId, "userId", request, response);
					
				} 
				
				
				user.setUserPwd(null);
				session.setMaxInactiveInterval(60*60*6);
				session.setAttribute("user", user);
				
				return 2;
			}
			
		}
		
		return 5;
		
	}
	
	// 로그인 유지하기
	@Override
	public UserVO continueLogin(String userId) {
		return userDAO.continueLogin(userId);
	}


	// 카카오유저 이메일로 가입되있는지 확인
	@Override
	public Map kakkoIdCheck(String userEmail) {
		return userDAO.kakkoIdCheck(userEmail);
	}
	
	// 카카오 연동하기
	@Override
	public void kakkoConnetion(String userId) {
		userDAO.kakkoConnetion(userId);
	}
	
	
	// 회원가입 아이디 체크 or 닉네임 체크
	@Override
	public int overlapCheck(UserVO userVO,int functinoNum) {
		return userDAO.overlapCheck(userVO,functinoNum);
	}
	// 회원정보
	@Override
	public UserVO userInfo(String userId) {
		return userDAO.userInfo(userId);
	}
	
	// 찜하기
	@Override
	public void dibs(String userId, int storeNum) {
		userDAO.dibs(userId, storeNum);
		
	}

	// 회원 찜한 가게
	@Override
	public List dibsLogin(String userId) {
		return userDAO.dibsLogin(userId);
	}
	
	// 비회원 찜한 가게
	@Override
	public List dibsNoLogin(String storeNums) {
		return userDAO.dibsNoLogin(storeNums);
	}
	
	// 내가쓴  리뷰
	@Override
	public List getReviewList(String userId) {
		return userDAO.getReviewList(userId);
	}
	
//	// 네이버 로그인인지 체크
//	@Override
//	public UserVO naverLoginCheck(Map naverInfo) {
//		return userDAO.naverLoginCheck(naverInfo);
//	}
//
//	// 네이버 정보로 로그인
//	@Override
//	public UserVO naverJoin(Map<String, Object> naverInfo) {
//		return userDAO.naverJoin(naverInfo);
//	}
//	
//	// 네이버 연동  NaverLogin을 naverInfo.get("id")로 업데이트
//	@Override
//	public void naverConnection(String id, String email) {
//		userDAO.naverConnection(id,email);
//		
//	}
	
	// 정보수정
	@Override
	public void modifyInfo(String info, String userId, int functionNum) {
		
		if(functionNum == 1)  { // 비번수정 
			info = passwordEncoder.encode(info);
		}
		
		userDAO.modifyInfo(info,userId,functionNum);
		
	}
	// 포인트 확인
	@Override
	public List getPoint(String userId) {
		return userDAO.getPoint(userId);
	}
	// 상품권 확인
	@Override
	public Map giftCardCheck(String giftCardNum) {
		return userDAO.giftCardCheck(giftCardNum);
	}

	// 카카오 회원가입
	@Override
	public void kakkoJoin(UserVO userVO) {
		userDAO.kakkoJoin(userVO);
	}






	
	
	









	
}
