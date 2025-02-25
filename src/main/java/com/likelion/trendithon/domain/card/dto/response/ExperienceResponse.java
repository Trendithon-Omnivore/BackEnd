package com.likelion.trendithon.domain.card.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExperienceResponse {

  @Schema(description = "경험 조회 결과", example = "true")
  private boolean success;

  @Schema(description = "응답 메세지", example = "경험 조회에 성공하였습니다.")
  private String message;

  @Schema(description = "경험 제목", example = "멋쟁이사자 되기")
  private String title;

  @Schema(description = "경험 상태", example = "true")
  private boolean state;

  @Schema(description = "경험 표지", example = "#000000")
  private String cover;

  @Schema(description = "시작 날짜", example = "2025-01-01")
  private LocalDate startDate;

  @Schema(description = "종료 날짜", example = "2025-01-01")
  private LocalDate endDate;
}
