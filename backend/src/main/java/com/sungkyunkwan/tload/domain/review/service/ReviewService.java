package com.sungkyunkwan.tload.domain.review.service;

import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.domain.review.dto.ReviewRequestDto;
import com.sungkyunkwan.tload.domain.review.dto.ReviewResponseDto;
import com.sungkyunkwan.tload.domain.review.entity.Review;
import com.sungkyunkwan.tload.domain.review.repository.ReviewRepository;
import com.sungkyunkwan.tload.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	private final ReviewRepository reviewRepository;

	public ReviewResponseDto createBoard(User user, ReviewRequestDto reviewRequestDto) {
		Review review = Review.builder()
			.title(reviewRequestDto.getTitle())
			.contents(reviewRequestDto.getContent())
			.rating(reviewRequestDto.getRating())
			.user(user)
			.build();

		reviewRepository.save(review);

		return new ReviewResponseDto(review);
	}
}
