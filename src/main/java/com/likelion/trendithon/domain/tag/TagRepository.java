package com.likelion.trendithon.domain.tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.trendithon.domain.card.Card;

public interface TagRepository extends JpaRepository<Tag, Long> {

  List<Tag> findByCard(Card card);
}
