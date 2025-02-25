package com.likelion.trendithon.domain.card.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateReviewRequest {

  @Schema(description = "리뷰할 경험 ID", example = "1")
  private Long experienceId;

  @Schema(description = "경험 점수", example = "3.5")
  private Double score;

  @Schema(description = "실제 종료 날짜", example = "2025-01-01")
  private LocalDate endDate;

  @Schema(description = "느낀 점", example = "좋았다.")
  private String content;
}
