package com.baemin.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewVO {
	
	private int storeNum;
	private String userId;
	private String content;
	
	private String bossComment;
	private Date regiDate;
	
	private String nickname;
	private float score;
	private String reviewImg;
	
	private String storeName; // 내 리뷰목록 추가
//	private int reviewNum; 
	private String orderNum; // 리뷰 번호를 주문번호로 변경
}
