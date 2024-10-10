package com.sungkyunkwan.tload.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일은 필수 입력 항목입니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
	@Pattern(
		regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[~!@#$%^&*]).{8,}$",
		message = "비밀번호는 대소문자, 숫자, 특수문자(~!@#$%^&*)를 포함하여 8자 이상이어야 합니다."
	)
	private String password;

	@NotBlank(message = "닉네임은 필수 입력 항목입니다.")
	private String nickname;

	private String intro;
}