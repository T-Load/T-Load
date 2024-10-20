package com.sungkyunkwan.tload.domain.review.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.common.dto.CommonDto;
import com.sungkyunkwan.tload.domain.review.dto.ReviewRequestDto;
import com.sungkyunkwan.tload.domain.review.dto.ReviewResponseDto;
import com.sungkyunkwan.tload.domain.review.service.ReviewService;
import com.sungkyunkwan.tload.security.user.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<CommonDto<ReviewResponseDto>> createReview(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody ReviewRequestDto reviewRequestDto) {

		return ResponseEntity.ok()
			.body(new CommonDto<ReviewResponseDto>(HttpStatus.CREATED.value()
				, "후기 생성에 성공하였습니다."
				, reviewService.createReview(userDetails.getUser(), reviewRequestDto)));
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<CommonDto<ReviewResponseDto>> getReview(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long reviewId) {

		return ResponseEntity.ok()
			.body(new CommonDto<ReviewResponseDto>(HttpStatus.OK.value()
				, "후기 조회에 성공하였습니다."
				, reviewService.getReview(reviewId)));
	}

	@GetMapping
	public ResponseEntity<CommonDto<Page<ReviewResponseDto>>>  getReviews(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestParam(defaultValue = "0") int page) {

		return ResponseEntity.ok()
			.body(new CommonDto<Page<ReviewResponseDto>>(HttpStatus.OK.value()
				, "후기 조회에 성공하였습니다."
				, reviewService.getReviews(page)));
	}

	@PutMapping("/{reviewId")
	public ResponseEntity<CommonDto<ReviewResponseDto>> updateReview(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long reviewId,
		@RequestBody ReviewRequestDto reviewRequestDto) {

		return ResponseEntity.ok()
			.body(new CommonDto<ReviewResponseDto>(HttpStatus.OK.value()
				, "후기 수정에 성공하였습니다."
				, reviewService.updateReview(userDetails.getUser().getId(), reviewId, reviewRequestDto)));
	}
}
