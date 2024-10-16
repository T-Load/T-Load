package com.sungkyunkwan.tload.domain.user.service;

import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.domain.user.dto.UserInfoRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.UserResponseDto;
import com.sungkyunkwan.tload.domain.user.entity.User;
import com.sungkyunkwan.tload.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

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

	private User findById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
	}
}
