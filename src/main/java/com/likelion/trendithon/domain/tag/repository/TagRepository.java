package com.likelion.trendithon.domain.tag.repository;

import com.likelion.trendithon.domain.tag.entity.Tag;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.trendithon.domain.card.entity.Card;

public interface TagRepository extends JpaRepository<Tag, Long> {

  List<Tag> findByCard(Card card);
}
