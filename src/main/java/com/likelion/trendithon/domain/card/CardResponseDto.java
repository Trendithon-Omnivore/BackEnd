package com.likelion.trendithon.domain.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponseDto {

  private Long cardId;
  private String title;

  public CardResponseDto(Long id, String title) {
    this.cardId = id;
    this.title = title;
  }
}
