package com.sungkyunkwan.tload.domain.board.service;

import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.domain.board.dto.BoardRequestDto;
import com.sungkyunkwan.tload.domain.board.dto.BoardResponseDto;
import com.sungkyunkwan.tload.domain.board.entity.Board;
import com.sungkyunkwan.tload.domain.board.repository.BoardRepository;
import com.sungkyunkwan.tload.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardResponseDto createBoard(User user, BoardRequestDto boardRequestDto) {
		Board board = Board.builder()
			.title(boardRequestDto.getTitle())
			.contents(boardRequestDto.getContent())
			.user(user)
			.build();

		boardRepository.save(board);

		return new BoardResponseDto(board);
	}
}
