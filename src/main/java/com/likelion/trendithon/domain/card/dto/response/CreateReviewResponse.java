package com.likelion.trendithon.domain.card.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateReviewResponse {

  @Schema(description = "리뷰 생성 결과", example = "true")
  private boolean success;

  @Schema(description = "응답 메세지", example = "리뷰 생성에 성공하였습니다.")
  private String message;

  @Schema(description = "생성된 리뷰 ID", example = "1")
  private Long reviewId;
}
