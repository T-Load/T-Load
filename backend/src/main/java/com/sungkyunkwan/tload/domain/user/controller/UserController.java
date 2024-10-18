package com.sungkyunkwan.tload.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.domain.user.dto.UserInfoRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.UserPwRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.UserResponseDto;
import com.sungkyunkwan.tload.domain.user.service.UserService;
import com.sungkyunkwan.tload.security.user.UserDetailsImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@GetMapping("/profile/{userId}")
	public ResponseEntity<UserResponseDto> getUser(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long userId) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(userService.getUser(userId));
	}

	@PutMapping("/profile")
	public ResponseEntity<UserResponseDto> updateUser(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody UserInfoRequestDto userInfoRequestDto) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(userService.updateUser(userDetails.getUser().getId(), userInfoRequestDto));
	}

	@PutMapping("/password")
	public ResponseEntity<String> updatePassword(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody @Valid UserPwRequestDto userPwRequestDto) {

		userService.updatePassword(userDetails.getUser().getId(), userPwRequestDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body("비밀번호 수정에 성공하였습니다.");
	}
}
