package com.sungkyunkwan.tload.domain.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
				, reviewService.createBoard(userDetails.getUser(), reviewRequestDto)));
	}
}
