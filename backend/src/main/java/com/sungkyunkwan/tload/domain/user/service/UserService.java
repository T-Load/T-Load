package com.sungkyunkwan.tload.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.domain.user.dto.SignupRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.SignupResponseDto;
import com.sungkyunkwan.tload.domain.user.entity.User;
import com.sungkyunkwan.tload.domain.user.entity.UserRoleEnum;
import com.sungkyunkwan.tload.domain.user.repository.UserRepository;
import com.sungkyunkwan.tload.security.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
		if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
			throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
		}

		if (userRepository.existsByNickname(signupRequestDto.getNickname())) {
			throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
		}

		User user = User.builder()
			.email(signupRequestDto.getEmail())
			.password(passwordEncoder.encode(signupRequestDto.getPassword()))
			.nickname(signupRequestDto.getNickname())
			.intro(signupRequestDto.getIntro())
			.role(UserRoleEnum.USER)
			.build();

		userRepository.save(user);

		return new SignupResponseDto(user);
	}
}