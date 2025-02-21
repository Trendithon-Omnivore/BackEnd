package com.likelion.trendithon.domain.card.dto;

import com.likelion.trendithon.domain.card.entity.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardListResponse {

  @Schema(description = "ī�� ���� ���", example = "true")
  private boolean success;

  @Schema(description = "���� �޼���", example = "���������� ī�尡 �����Ǿ����ϴ�.")
  private String message;

  @Schema(description = "������ ī�� ����Ʈ")
  private List<CardListSummaryDto> cardList;

}
