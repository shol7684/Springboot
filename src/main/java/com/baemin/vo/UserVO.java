package com.baemin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
//            user_id varchar2(50) not null,
//            user_pwd varchar2(50) not null,
//            nickname varchar2(50) not null,
//            point number ,
//            user_phone number not null,
//            rating varchar2(50) not null
	
	private String userId;
	private String userPwd;
	private String nickname;	//유저 닉네임
	private int point;	// 유저 포인트
	private String userPhone; // 유저 전화번호
	private String rating; // 등급	
	private int adminCheck;
	
	private String kakkoLoing; 
	
	
}
