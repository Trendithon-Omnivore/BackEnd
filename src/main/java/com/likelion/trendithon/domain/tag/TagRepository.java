package com.likelion.trendithon.domain.tag;

import com.likelion.trendithon.domain.card.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

  List<Tag> findByCard(Card card);
}
