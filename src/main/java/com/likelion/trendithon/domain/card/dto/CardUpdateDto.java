package com.likelion.trendithon.domain.card.dto;

import com.likelion.trendithon.domain.tag.entity.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class CardUpdateDto {

  @Schema(description = "카드 제목", example = "멋쟁이사자 되기")
  private String title;

  @Schema(description = "카드 내용", example = "나는 오늘 멋쟁이 사자가 되다.")
  private String content;

  @Schema(description = "이모지 Url")
  private String imgUrl;

  @Schema(description = "태그 목록", example = "[{\"title\": \"팁\", \"content\": \"열심히 하기\"}]")
  private List<Tag> TagItems;

}
