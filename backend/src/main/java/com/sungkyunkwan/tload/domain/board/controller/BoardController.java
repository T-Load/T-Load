package com.sungkyunkwan.tload.domain.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.domain.board.dto.BoardRequestDto;
import com.sungkyunkwan.tload.domain.board.dto.BoardResponseDto;
import com.sungkyunkwan.tload.domain.board.service.BoardService;
import com.sungkyunkwan.tload.security.user.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public ResponseEntity<BoardResponseDto> createBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody BoardRequestDto boardRequestDto) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(boardService.createBoard(userDetails.getUser(), boardRequestDto));
	}
}
