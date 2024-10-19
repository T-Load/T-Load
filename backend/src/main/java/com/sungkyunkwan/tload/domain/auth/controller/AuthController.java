package com.sungkyunkwan.tload.domain.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.common.dto.CommonDto;
import com.sungkyunkwan.tload.domain.auth.dto.SigninRequestDto;
import com.sungkyunkwan.tload.domain.auth.dto.SigninResponseDto;
import com.sungkyunkwan.tload.domain.auth.dto.SignupRequestDto;
import com.sungkyunkwan.tload.domain.auth.dto.SignupResponseDto;
import com.sungkyunkwan.tload.domain.auth.service.AuthService;
import com.sungkyunkwan.tload.domain.board.dto.BoardResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<CommonDto<SignupResponseDto>> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new CommonDto<SignupResponseDto>(HttpStatus.CREATED.value()
				, "회원가입에 성공하였습니다."
				, authService.signup(signupRequestDto)));
	}

	@PostMapping("/login")
	public ResponseEntity<CommonDto<SigninResponseDto>> login(@Valid @RequestBody SigninRequestDto signinRequestDto) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(new CommonDto<SigninResponseDto>(HttpStatus.OK.value()
				, "로그인에 성공하였습니다."
				, authService.login(signinRequestDto)));
	}
}