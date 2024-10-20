package com.sungkyunkwan.tload.domain.board.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sungkyunkwan.tload.common.size.PageSize;
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
			.contents(boardRequestDto.getContents())
			.user(user)
			.build();

		boardRepository.save(board);

		return new BoardResponseDto(board);
	}

	public BoardResponseDto getBoard(Long boardId) {
		return new BoardResponseDto(findById(boardId));
	}

	public Page<BoardResponseDto> getBoards(int page) {
		Pageable pageable = PageRequest.of(page, PageSize.BOARD.getSize());
		Page<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
		List<BoardResponseDto> boardResponseDtos = boards.stream()
			.map(BoardResponseDto::new)
			.toList();

		return new PageImpl<>(boardResponseDtos, pageable, boards.getTotalElements());
	}

	public BoardResponseDto updateBoard(Long id, Long boardId, BoardRequestDto boardRequestDto) {
		if (!isBoardByUser(id, boardId)) {
			throw new IllegalArgumentException("해당 게시물에 접근할 권한이 없습니다.");
		}

		Board board = findById(boardId);
		board.updateBoard(boardRequestDto);
		boardRepository.save(board);

		return new BoardResponseDto(board);
	}

	public void deleteBoard(Long id, Long boardId) {
		if (!isBoardByUser(id, boardId)) {
			throw new IllegalArgumentException("해당 게시물에 접근할 권한이 없습니다.");
		}

		Board board = findById(boardId);
		boardRepository.delete(board);
	}

	private Boolean isBoardByUser(Long userId, Long boardId) {
		return boardRepository.existsByIdAndUserId(boardId, userId);
	}

	private Board findById(Long boardId) {
		return boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다."));
	}
}
