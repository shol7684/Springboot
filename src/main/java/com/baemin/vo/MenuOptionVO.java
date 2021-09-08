package com.baemin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuOptionVO {
	
	private int storeNum;
	private String optionName;
	private int menuNum;
	private int optionPrice;
	private int optionNum;
}