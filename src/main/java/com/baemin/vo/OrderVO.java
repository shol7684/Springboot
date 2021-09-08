package com.baemin.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {

//    store_num,
//    user_id,
//    total,
//    pay_method,
//    order_num,
//    request,
//    order_date,
//    user_address1,
//    user_address2,
//    user_address3,
//    user_phone
	
	private int storeNum; // 매장 번호
	private String userId;	//유저 아이디
	private int total;	// 메뉴 가격 총합
	private String payMethod; // 결제방법
	private String orderNum; // 주문 번호
	private String request; // 요청사항
	private Date orderDate; // 주문 시간
	private String userAddress1; // 우편번호
	private String userAddress2; // 주소
	private String userAddress3; // 상세주소
	private String userPhone; // 유저 전화번호
	
	private String deleveryState; // 배송상태 추가
	private int reviewCount; // 리뷰 작성 횟수 추가
	private int usePoint ; // 사용 포인트 추가
	private String storeName;
	private int point; 
	private int deleveryTip;
	
	
}
