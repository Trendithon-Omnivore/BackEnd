package com.likelion.trendithon.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserStateResponse {
  @Schema(description = "사용자 상태 조회 결과", example = "true")
  private boolean success;

  @Schema(description = "응답 메세지", example = "사용자 상태 조회에 성공하였습니다.")
  private String message;

  @Schema(description = "사용자 상태", example = "true")
  private Boolean state;
}
