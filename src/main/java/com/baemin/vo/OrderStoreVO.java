package com.baemin.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class OrderStoreVO {

	private int storeNum;
	private String userId;
	private int total;
	private String payMethod;
	private String orderNum;
	private String request;
	private Date orderDate;
	private String userAddress1;
	private String userAddress2;
	private String userAddress3;
	private String userPhone;
	
	private String deleveryState; // 배송상태 추가
	private int reviewCount; // 리뷰 작성 횟수 추가
	private int usePoint; // 사용 포인트 추가
	private int deleveryTime; // 예상배달시간 추가
	private Date acceptTime; //  주문 접수 시간 추가
	private int cartNum; // 같은메뉴로 옵션 다르게 선택시 구분
	private String menuOption;
	private int menuOptionPrice;
	private int optionCartNum;
	// orderVO + StoreVO
	

	private int cate;	//카테고리
	private String storeName;	//가게이름
	private String storeAddress1;   //우편번호
	private String storeAddress2;	//주소
	private String storeAddress3;	//상세주소
	private String storePhone;	//가게 번호
	private String storeImg;
	private String storeThumb;
	private int busiHours1;	//영업 시작
	private int busiHours2;	//영업 끝
	private int minDelevery; //최소 배달금액
	private int score;
	private String review1;
	private String review2;
	private int deleveryTip;
	private int good;
	private String storeDes;
	
	// orderVO + StoreVO + orderDetailVO
	private String menuName;
	private int menuPrice;
	private int amount;
	
}
