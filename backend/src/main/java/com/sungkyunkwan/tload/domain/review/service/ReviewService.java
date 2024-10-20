package com.sungkyunkwan.tload.domain.review.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.common.size.PageSize;
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

	public ReviewResponseDto createReview(User user, ReviewRequestDto reviewRequestDto) {
		Review review = Review.builder()
			.title(reviewRequestDto.getTitle())
			.contents(reviewRequestDto.getContent())
			.rating(reviewRequestDto.getRating())
			.user(user)
			.build();

		reviewRepository.save(review);

		return new ReviewResponseDto(review);
	}

	public ReviewResponseDto getReview(Long boardId) {
		return new ReviewResponseDto(findById(boardId));
	}

	public Page<ReviewResponseDto> getReviews(int page) {
		Pageable pageable = PageRequest.of(page, PageSize.REVIEW.getSize());
		Page<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc(pageable);
		List<ReviewResponseDto> reviewResponseDtos = reviews.stream()
			.map(ReviewResponseDto::new)
			.toList();

		return new PageImpl<>(reviewResponseDtos, pageable, reviews.getTotalElements());
	}

	private Review findById(Long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> new IllegalArgumentException("해당 후기가 존재하지 않습니다."));
	}
}
