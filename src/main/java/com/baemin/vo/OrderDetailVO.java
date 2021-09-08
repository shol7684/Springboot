package com.baemin.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVO {
//    order_num number not null,
//    menu_name varchar2(50) not null,
//    menu_price number not null,
//    amount number   not null
	
	private String orderNum;
	private String menuName;
	private int menuPrice;
	private int amount;
	
	
}
