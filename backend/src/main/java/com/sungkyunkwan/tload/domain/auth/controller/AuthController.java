package com.sungkyunkwan.tload.domain.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.domain.auth.dto.SigninRequestDto;
import com.sungkyunkwan.tload.domain.auth.dto.SigninResponseDto;
import com.sungkyunkwan.tload.domain.auth.dto.SignupRequestDto;
import com.sungkyunkwan.tload.domain.auth.dto.SignupResponseDto;
import com.sungkyunkwan.tload.domain.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupRequestDto));
	}

	@PostMapping("/login")
	public ResponseEntity<SigninResponseDto> login(@Valid @RequestBody SigninRequestDto signinRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(signinRequestDto));
	}
}