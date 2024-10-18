package com.sungkyunkwan.tload.domain.board.dto;

import com.sungkyunkwan.tload.domain.board.entity.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {

	private final Long id;
	private final String title;
	private final String contents;

	public BoardResponseDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.contents = board.getContents();
	}
}
