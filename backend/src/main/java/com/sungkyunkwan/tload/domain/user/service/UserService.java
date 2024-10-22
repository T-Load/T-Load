package com.sungkyunkwan.tload.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.domain.user.dto.UserInfoRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.UserPwRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.UserResponseDto;
import com.sungkyunkwan.tload.domain.user.entity.User;
import com.sungkyunkwan.tload.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserResponseDto getUser(Long userId) {
		User user = findById(userId);

		return new UserResponseDto(user);
	}

	public UserResponseDto updateUser(Long userId, UserInfoRequestDto userInfoRequestDto) {
		User user = findById(userId);

		user.updateUserInfo(userInfoRequestDto);
		userRepository.save(user);

		return new UserResponseDto(user);
	}

	public void updatePassword(Long id, UserPwRequestDto userPwRequestDto) {
		User user = findById(id);

		if (!passwordEncoder.matches(userPwRequestDto.getCurrentPassword(), user.getPassword())) {
			throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
		}

		if (passwordEncoder.matches(userPwRequestDto.getNewPassword(), user.getPassword())) {
			throw new IllegalArgumentException("새로운 비밀번호가 현재 비밀번호와 동일합니다.");
		}

		user.updatePassword(passwordEncoder.encode(userPwRequestDto.getNewPassword()));
		userRepository.save(user);
	}

	private User findById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
	}
}
