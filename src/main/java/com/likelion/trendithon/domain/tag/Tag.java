package com.likelion.trendithon.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.likelion.trendithon.domain.card.Card;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tagId;

  @Column private String tagTitle;

  private String tagContent;

  @ManyToOne
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;
}
