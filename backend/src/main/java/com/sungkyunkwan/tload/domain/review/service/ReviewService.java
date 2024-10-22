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
			.contents(reviewRequestDto.getContents())
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

	public ReviewResponseDto updateReview(Long id, Long reviewId, ReviewRequestDto reviewRequestDto) {
		if (!isReviewByUser(id, reviewId)) {
			throw new IllegalArgumentException("해당 후기에 접근할 권한이 없습니다.");
		}

		Review review = findById(reviewId);
		review.updateReview(reviewRequestDto);
		reviewRepository.save(review);

		return new ReviewResponseDto(review);
	}

	public void deleteReview(Long id, Long reviewId) {
		if (!isReviewByUser(id, reviewId)) {
			throw new IllegalArgumentException("해당 후기에 접근할 권한이 없습니다.");
		}

		Review review = findById(reviewId);
		reviewRepository.delete(review);
	}

	private Boolean isReviewByUser(Long userId, Long reviewId) {
		return reviewRepository.existsByIdAndUser_Id(reviewId, userId);
	}

	private Review findById(Long reviewId) {
		return reviewRepository.findById(reviewId)
			.orElseThrow(() -> new IllegalArgumentException("해당 후기가 존재하지 않습니다."));
	}
}
