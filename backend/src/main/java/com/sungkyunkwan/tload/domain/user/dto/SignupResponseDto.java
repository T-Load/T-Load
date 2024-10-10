package com.sungkyunkwan.tload.domain.user.dto;

import com.sungkyunkwan.tload.domain.user.entity.User;

import lombok.Getter;

@Getter
public class SignupResponseDto {

	private final Long id;
	private final String email;
	private final String nickname;
	private final String intro;

	public SignupResponseDto(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.intro = user.getIntro();
	}
}