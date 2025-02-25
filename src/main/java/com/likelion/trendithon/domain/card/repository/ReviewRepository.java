package com.likelion.trendithon.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.trendithon.domain.card.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {}
