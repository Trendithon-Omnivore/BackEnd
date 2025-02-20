package com.likelion.trendithon.domain.card;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.likelion.trendithon.domain.tag.Tag;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  private String title;

  private String content;

  private String imgUrl;

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tag> TagItems = new ArrayList<>();
}
