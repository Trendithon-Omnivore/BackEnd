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

  @Schema(description = "ī�� ����", example = "�����̻��� �Ǳ�")
  private String title;

  @Schema(description = "ī�� ����", example = "���� ���� ������ ���ڰ� �Ǵ�.")
  private String content;

  @Schema(description = "�̸��� Url")
  private String imgUrl;

  @Schema(description = "�±� ���", example = "[{\"title\": \"��\", \"content\": \"������ �ϱ�\"}]")
  private List<Tag> TagItems;

}
