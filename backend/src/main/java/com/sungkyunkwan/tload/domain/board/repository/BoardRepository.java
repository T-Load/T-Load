package com.sungkyunkwan.tload.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sungkyunkwan.tload.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
