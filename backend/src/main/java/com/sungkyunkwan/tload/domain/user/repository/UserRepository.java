package com.sungkyunkwan.tload.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sungkyunkwan.tload.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	boolean existsByNickname(String nickname);
}