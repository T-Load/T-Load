package com.sungkyunkwan.tload.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.domain.user.dto.UserResponseDto;
import com.sungkyunkwan.tload.domain.user.service.UserService;
import com.sungkyunkwan.tload.security.user.UserDetailsImpl;

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

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.getUser(userId));
	}
}
