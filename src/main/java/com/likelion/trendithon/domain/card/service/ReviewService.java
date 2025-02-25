package com.likelion.trendithon.domain.card.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.likelion.trendithon.domain.card.dto.request.CreateReviewRequest;
import com.likelion.trendithon.domain.card.dto.response.CreateReviewResponse;
import com.likelion.trendithon.domain.card.entity.Experience;
import com.likelion.trendithon.domain.card.entity.Review;
import com.likelion.trendithon.domain.card.entity.ReviewImage;
import com.likelion.trendithon.domain.card.repository.CardRepository;
import com.likelion.trendithon.domain.card.repository.ExperienceRepository;
import com.likelion.trendithon.domain.card.repository.ReviewRepository;
import com.likelion.trendithon.domain.user.repository.UserRepository;
import com.likelion.trendithon.global.auth.JwtUtil;
import com.likelion.trendithon.global.s3.service.S3Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewService {

  private final ExperienceRepository experienceRepository;
  private final CardRepository cardRepository;
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final S3Service s3Service;
  private final ReviewRepository reviewRepository;

  // 경험 생성
  @Transactional
  public ResponseEntity<CreateReviewResponse> createReview(
      CreateReviewRequest createReviewRequest, List<MultipartFile> images) {

    try {

      Experience experience =
          experienceRepository
              .findById(createReviewRequest.getExperienceId())
              .orElseThrow(() -> new IllegalArgumentException("경험을 찾을 수 없습니다."));

      Review review =
          Review.builder()
              .experience(experience)
              .score(createReviewRequest.getScore())
              .endDate(createReviewRequest.getEndDate())
              .content(createReviewRequest.getContent())
              .build();

      List<ReviewImage> reviewImageList = new ArrayList<>();

      for (MultipartFile image : images) {
        ReviewImage reviewImage =
            ReviewImage.builder().review(review).imageUrl(s3Service.uploadFile(image)).build();

        reviewImageList.add(reviewImage);
      }

      review.setReviewImageList(reviewImageList);
      reviewRepository.save(review);

      log.info(
          "[POST /api/reviews] 리뷰 생성 성공 - 생성한 사용자 ID: {}, 경험 ID: {}, 리뷰 ID: {}",
          experience.getUser().getLoginId(),
          experience.getExperienceId(),
          review.getReviewId());
      return ResponseEntity.ok(
          CreateReviewResponse.builder()
              .success(true)
              .message("리뷰 생성에 성공하였습니다.")
              .reviewId(review.getReviewId())
              .build());
    } catch (Exception e) {
      log.error("[POST /api/reviews] 리뷰 생성 실패 - 에러: {}", e.getMessage());
      return ResponseEntity.ok(
          CreateReviewResponse.builder().success(false).message("리뷰 생성에 실패하였습니다.").build());
    }
  }
}
