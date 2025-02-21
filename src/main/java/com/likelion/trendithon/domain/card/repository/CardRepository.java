package com.likelion.trendithon.domain.card.repository;

import com.likelion.trendithon.domain.card.entity.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
  List<Card> findByUserId(Long id);
}
