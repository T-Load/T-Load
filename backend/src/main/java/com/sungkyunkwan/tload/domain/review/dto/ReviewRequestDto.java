package com.sungkyunkwan.tload.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {

	private String title;
	private String contents;
	private int rating;
}
