package com.baemin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodStoreVO {

	private String userId;
//	private int good;
	
	
//	private int count;
	
	// GoodVO + StoreVO 
	
	private int cate;	//카테고리
	private int storeNum;	//가게 번호
	private String storeName;	//가게이름
	private String storeAddress1;   //우편번호
	private String storeAddress2;		//주소
	private String storeAddress3;	//상세주소
	private String storePhone;	//가게 번호
	private String storeImg;
	private String storeThumb;
	private int busiHours1;	//영업 시작
	private int busiHours2;	//영업 끝
	private int minDelevery; //최소 배달금액
	private int deleveryTime;
	private int deleveryTip;
	private String storeDes;
	
	private int goodCheck;
	private float score; // 별점
	private int dibsCount; // 찜한사람 숫자
	private int reviewCount; // 리뷰수 추가
	private int bossCommentCount; // 사장님 댓글수 추가
	private int orderCount; // 주문 횟수
	
	private int oneScore;
	private int twoScore;
	private int threeScore;
	private int fourScore;
	private int fiveScore;

	
	
}
