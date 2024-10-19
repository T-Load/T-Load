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

import com.sungkyunkwan.tload.common.dto.CommonDto;
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
	public ResponseEntity<CommonDto<BoardResponseDto>> createBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody BoardRequestDto boardRequestDto) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new CommonDto<BoardResponseDto>(HttpStatus.CREATED.value()
				, "게시물 생성에 성공하였습니다."
				, boardService.createBoard(userDetails.getUser(), boardRequestDto)));
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<CommonDto<BoardResponseDto>> getBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(new CommonDto<BoardResponseDto>(HttpStatus.OK.value()
				, "게시물 조회에 성공하였습니다."
				, boardService.getBoard(boardId)));
	}

	@GetMapping
	public ResponseEntity<CommonDto<Page<BoardResponseDto>>>  getBoards(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestParam(defaultValue = "0") int page) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(new CommonDto<Page<BoardResponseDto>>(HttpStatus.OK.value()
				, "게시물 조회에 성공하였습니다."
				, boardService.getBoards(page)));
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<CommonDto<BoardResponseDto>> updateBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId,
		@RequestBody BoardRequestDto boardRequestDto) {

		return ResponseEntity.status(HttpStatus.OK)
			.body(new CommonDto<BoardResponseDto>(HttpStatus.OK.value()
				, "게시물 수정에 성공하였습니다."
				, boardService.updateBoard(userDetails.getUser().getId(), boardId, boardRequestDto)));
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<CommonDto<Void>> deleteBoard(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable Long boardId) {

		boardService.deleteBoard(userDetails.getUser().getId(), boardId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			.body(new CommonDto<Void>(HttpStatus.NO_CONTENT.value()
				, "게시물 삭제에 성공하였습니다."
				, null));
	}
}
