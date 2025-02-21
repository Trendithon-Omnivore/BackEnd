package com.likelion.trendithon.domain.card.dto;

import com.likelion.trendithon.domain.card.entity.Card;
import com.likelion.trendithon.domain.tag.entity.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardResponse {

  @Schema(description = "ī�� ���� ���", example = "true")
  private boolean success;

  @Schema(description = "���� �޼���", example = "ī�尡 ���� �����Ͽ����ϴ�.")
  private String message;

  @Schema(description = "ī��")
  private Card card;

}
