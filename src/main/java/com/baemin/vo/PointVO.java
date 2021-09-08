package com.baemin.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PointVO {
	private String userId;
	private Date regiDate;
	private int point; 
	private String useInfo;
}
