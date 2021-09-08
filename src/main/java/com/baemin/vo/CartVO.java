package com.baemin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {
	
	private String userId;
	private int storeNum;
	private String storeName;
	private String menuNum;
	private int amount;
	private int menuPrice;
	private int total;
	private String menuName;
	private int deleveryTip;
	
	private String[] menuOption;
	private String[] menuOptionPrice;

}
