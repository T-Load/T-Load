package com.sungkyunkwan.tload.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sungkyunkwan.tload.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
