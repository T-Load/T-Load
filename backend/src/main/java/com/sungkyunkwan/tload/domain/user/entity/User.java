package com.sungkyunkwan.tload.domain.user.entity;

import com.sungkyunkwan.tload.domain.user.dto.UserInfoRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.UserPwRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column
	private String intro;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRoleEnum role;

	@Builder
	public User(Long id, String email, String password, String nickname, String intro, UserRoleEnum role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.intro = intro;
		this.role = role;
	}

	public void updateUserInfo(UserInfoRequestDto userInfoRequestDto) {
		this.nickname = userInfoRequestDto.getNickname();
		this.intro = userInfoRequestDto.getIntro();
	}

	public void updatePassword(UserPwRequestDto userPwRequestDto) {
		this.password = userPwRequestDto.getNewPassword();
	}
}