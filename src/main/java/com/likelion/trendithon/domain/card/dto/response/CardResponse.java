package com.likelion.trendithon.domain.card.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardResponse {

  @Schema(description = "카드 조회 결과", example = "true")
  private boolean success;

  @Schema(description = "응답 메세지", example = "카드 조회에 성공하였습니다.")
  private String message;

  @Schema(description = "조회한 카드 이모지", example = "모바일 키보드 이모지")
  private String emoji;

  @Schema(description = "조회한 카드 제목", example = "멋쟁이사자 되기")
  private String title;

  @Schema(description = "조회한 카드 내용", example = "나는 오늘 멋쟁이 사자가 되었다.")
  private String content;

  @Schema(description = "조회한 카드 표지", example = "#000000")
  private String cover;
}
