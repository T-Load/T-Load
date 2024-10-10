package com.sungkyunkwan.tload.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sungkyunkwan.tload.domain.user.dto.SigninRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.SigninResponseDto;
import com.sungkyunkwan.tload.domain.user.dto.SignupRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.SignupResponseDto;
import com.sungkyunkwan.tload.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(@Valid SignupRequestDto signupRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(signupRequestDto));
	}

	@PostMapping("/login")
	public ResponseEntity<SigninResponseDto> login(@Valid SigninRequestDto signinRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(signinRequestDto));
	}
}