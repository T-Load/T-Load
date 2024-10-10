package com.sungkyunkwan.tload.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String BEARER_PREFIX = "Bearer ";

	@Value("${jwt.access.token}")
	private Long accessTokenTime;
	@Value("${jwt.refresh.token}")
	private Long refreshTokenTime;
	@Value("${jwt.secret.key}")
	private String secretKey;

	private Key key;
	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	@PostConstruct
	public void init() {
		byte[] bytes = Base64.getDecoder().decode(secretKey);
		key = Keys.hmacShaKeyFor(bytes);
	}

	public String createAccessToken(String username) {
		Date now = new Date();
		return BEARER_PREFIX + Jwts.builder()
			.setSubject(username)
			.setExpiration(new Date(now.getTime() + accessTokenTime))
			.signWith(key, signatureAlgorithm)
			.compact();
	}

	public String createRefreshToken() {
		Date now = new Date();
		return Jwts.builder()
			.setExpiration(new Date(now.getTime() + refreshTokenTime))
			.signWith(key, signatureAlgorithm)
			.compact();
	}

	public void addJwtToHeader(String token, HttpServletResponse response) {
		response.setHeader(AUTHORIZATION_HEADER, token);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException | UnsupportedJwtException e) {
			throw new RuntimeException("Invalid JWT signature.", e);
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("토큰이 만료되었습니다.", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("JWT claims is empty.", e);
		}
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public String getTokenFromRequest(HttpServletRequest req) {
		String bearerToken = req.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String substringToken(String token) {
		return token.substring(BEARER_PREFIX.length());
	}
}