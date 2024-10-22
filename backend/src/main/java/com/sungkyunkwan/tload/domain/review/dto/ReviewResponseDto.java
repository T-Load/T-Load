package com.sungkyunkwan.tload.domain.review.dto;

import java.time.LocalDateTime;

import com.sungkyunkwan.tload.domain.review.entity.Review;

import lombok.Getter;

@Getter
public class ReviewResponseDto {

	private final Long id;
	private final String title;
	private final String contents;
	private final int rating;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public ReviewResponseDto(Review review) {
		this.id = review.getId();
		this.title = review.getTitle();
		this.contents = review.getContents();
		this.rating = review.getRating();
		this.createdAt = review.getCreatedAt();
		this.updatedAt = review.getUpdatedAt();
	}
}
