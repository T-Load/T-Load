package com.sungkyunkwan.tload.domain.board.entity;

import com.sungkyunkwan.tload.domain.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "boards")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String contents;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;

	@Builder
	public Board(String title, String contents, User user) {
		this.title = title;
		this.contents = contents;
		this.user = user;
	}

	public void updateBoard(Board board) {
		this.title = board.getTitle();
		this.contents = board.getContents();
	}
}
