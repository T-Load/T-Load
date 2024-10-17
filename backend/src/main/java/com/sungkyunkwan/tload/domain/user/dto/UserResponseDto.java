package com.sungkyunkwan.tload.domain.user.dto;

import com.sungkyunkwan.tload.domain.user.entity.User;

import lombok.Getter;

@Getter
public class UserResponseDto {

	private final Long id;
	private final String nickname;
	private final String email;
	private final String intro;

	public UserResponseDto(User user) {
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.intro = user.getIntro();
	}
}
