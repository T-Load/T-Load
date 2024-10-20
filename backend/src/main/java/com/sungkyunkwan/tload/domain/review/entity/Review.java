package com.sungkyunkwan.tload.domain.review.entity;

import com.sungkyunkwan.tload.common.util.Timestamped;
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
@Table(name = "reviews")
public class Review extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String contents;

	@Column(nullable = false)
	private int rating;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;

	@Builder
	public Review(String title, String contents, int rating, User user) {
		this.title = title;
		this.contents = contents;
		this.rating = rating;
		this.user = user;
	}
}
