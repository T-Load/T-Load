package com.sungkyunkwan.tload.security.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sungkyunkwan.tload.domain.user.entity.User;
import com.sungkyunkwan.tload.domain.user.entity.UserRoleEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private final User user;

	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		UserRoleEnum role = user.getRole();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());
		return Collections.singletonList(authority);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}