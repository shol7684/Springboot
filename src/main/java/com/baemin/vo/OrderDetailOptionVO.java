package com.baemin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailOptionVO {
	
//    order_num,
//    menu_name,
//    menu_option,
//    menu_option_price,
//    cart_num
	
	private String orderNum;
	private String menuName;
	private String menuOption;
	private int menuOptionPrice;
	private int cartNum;
	
	
}
