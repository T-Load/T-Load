package com.sungkyunkwan.tload.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sungkyunkwan.tload.domain.user.dto.SigninRequestDto;
import com.sungkyunkwan.tload.domain.user.dto.SigninResponseDto;
import com.sungkyunkwan.tload.domain.user.entity.User;
import com.sungkyunkwan.tload.security.user.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final JwtUtil jwtUtil;

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
		setFilterProcessesUrl("/api/user/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			SigninRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), SigninRequestDto.class);

			return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
					requestDto.getEmail(),
					requestDto.getPassword(),
					null
				)
			);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		User user = ((UserDetailsImpl) authResult.getPrincipal()).getUser();
		String accessToken = jwtUtil.createAccessToken(user);
		String refreshToken = jwtUtil.createRefreshToken();

		SigninResponseDto signinResponseDto = new SigninResponseDto(accessToken, refreshToken);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new ObjectMapper().writeValue(response.getWriter(), signinResponseDto);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
	}
}