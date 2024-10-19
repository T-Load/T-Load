package com.sungkyunkwan.tload.domain.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sungkyunkwan.tload.domain.board.dto.BoardRequestDto;
import com.sungkyunkwan.tload.domain.board.dto.BoardResponseDto;
import com.sungkyunkwan.tload.domain.board.service.BoardService;
import com.sungkyunkwan.tload.security.user.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public ResponseEntity<BoardResponseDto> createBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody BoardRequestDto boardRequestDto) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(boardService.createBoard(userDetails.getUser(), boardRequestDto));
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardResponseDto> getBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(boardService.getBoard(boardId));
	}

	@GetMapping
	public ResponseEntity<Page<BoardResponseDto>>  getBoards(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestParam(defaultValue = "0") int page) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(boardService.getBoards(page));
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<BoardResponseDto> updateBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId,
		@RequestBody BoardRequestDto boardRequestDto) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(boardService.updateBoard(userDetails.getUser().getId(), boardId, boardRequestDto));
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<String> deleteBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId) {

		boardService.deleteBoard(userDetails.getUser().getId(), boardId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			.body("게시물 삭제에 성공하였습니다.");
	}
}
