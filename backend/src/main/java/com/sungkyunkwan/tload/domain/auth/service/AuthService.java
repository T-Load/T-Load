package com.sungkyunkwan.tload.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.domain.auth.dto.SigninRequestDto;
import com.sungkyunkwan.tload.domain.auth.dto.SigninResponseDto;
import com.sungkyunkwan.tload.domain.auth.dto.SignupRequestDto;
import com.sungkyunkwan.tload.domain.auth.dto.SignupResponseDto;
import com.sungkyunkwan.tload.domain.user.entity.User;
import com.sungkyunkwan.tload.domain.user.entity.UserRoleEnum;
import com.sungkyunkwan.tload.domain.user.repository.UserRepository;
import com.sungkyunkwan.tload.security.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

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

	public SigninResponseDto login(SigninRequestDto signinRequestDto) {
		User user = userRepository.findByEmail(signinRequestDto.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

		if (!passwordEncoder.matches(signinRequestDto.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		String accessToken = jwtUtil.createAccessToken(user);
		String refreshToken = jwtUtil.createRefreshToken();

		return new SigninResponseDto(accessToken, refreshToken);
	}
}