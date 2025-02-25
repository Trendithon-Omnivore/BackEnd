package com.likelion.trendithon.domain.card.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.likelion.trendithon.domain.card.dto.request.CreateReviewRequest;
import com.likelion.trendithon.domain.card.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "Review 관리 API")
public class ReviewController {

  private final ReviewService reviewService;

  @Operation(summary = "[ 토큰 O | 경험 리뷰 등록 ]", description = "경험을 완료한 카드 리뷰 등록")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> createReview(
      @Parameter(
              description = "리뷰 내용",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
          @RequestPart
          CreateReviewRequest createReviewRequest,
      @Parameter(
              description = "리뷰 이미지 리스트",
              content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
          @RequestPart(value = "images")
          List<MultipartFile> images) {
    return reviewService.createReview(createReviewRequest, images);
  }
}
