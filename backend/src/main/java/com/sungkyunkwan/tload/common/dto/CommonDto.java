package com.sungkyunkwan.tload.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonDto<T> {

	private int statusCode;
	private String msg;
	private T data;
}
