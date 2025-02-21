package com.likelion.trendithon.domain.card.service;

import com.likelion.trendithon.domain.card.dto.CardListResponse;
import com.likelion.trendithon.domain.card.dto.CardResponse;
import com.likelion.trendithon.domain.card.dto.CardUpdateDto;
import com.likelion.trendithon.domain.card.entity.Card;
import com.likelion.trendithon.domain.card.repository.CardRepository;
import com.likelion.trendithon.domain.card.dto.CardListSummaryDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.likelion.trendithon.domain.tag.entity.Tag;
import com.likelion.trendithon.domain.tag.Service.TagService;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CardService {

  private CardRepository cardRepository;
  private TagService tagService;

  // 카드 생성 - tag도 저장함
  @Transactional
  public ResponseEntity<CardResponse> createCard(Card card) {
    try {
      List<Tag> tags = card.getTagItems();
      tagService.createTag(tags);
      cardRepository.save(card);
      return ResponseEntity.ok(CardResponse.builder().success(true).message("카드가 생성 성공하였습니다.").card(card).build());
    } catch (Exception e){
      return ResponseEntity.ok(CardResponse.builder().success(false).message("카드 생성 실패하였습니다.").build());
    }

  }

  // 카드 한장 조회
  @Transactional
  public ResponseEntity<CardResponse> getCardById(Long id) {

    try {
      Optional<Card> optionalCard = cardRepository.findById(id);
      Card card;

      if (optionalCard.isPresent()) {
        card = optionalCard.get();
        card.setTagItems(tagService.getTags(card));

        return ResponseEntity.ok(CardResponse.builder().success(true).message("카드 조회 성공하였습니다.").card(card).build());
      } else return ResponseEntity.ok(CardResponse.builder().success(false).message("카드가 존재하지 않습니다.").build());

    } catch (Exception e){
        return ResponseEntity.ok(CardResponse.builder().success(false).message("카드 조회 중 오류가 발생하였습니다.").build());
    }

  }

  // 사용자 아이디로 모든 카드 목록 조회
  @Transactional
  public ResponseEntity<CardListResponse> getAllCards(Long userId) {
    try{

      List<Card> cardList = cardRepository.findByUserId(userId);
      List<CardListSummaryDto> cardDtos = new ArrayList<>();

      for (Card card : cardList) {

        // 제목이랑 카드 아이디만 추출
        cardDtos.add(CardListSummaryDto.builder().cardId(card.getCardId()).title(card.getTitle()).build());
      }

      // 카드가 존재하는지 안하는지 검사
      if (!cardDtos.isEmpty()) return ResponseEntity.ok(CardListResponse.builder().success(true).message("전체 카드가 조회 되었습니다.").cardList(cardDtos).build());
      else return ResponseEntity.ok(CardListResponse.builder().success(false).message("카드가 존재하지 않습니다.").cardList(cardDtos).build());

    } catch (Exception e){
      return ResponseEntity.ok(CardListResponse.builder().success(false).message("사용자의 카드 목록을 조회 중 에러가 발생했습니다.").build());
    }
  }


  // 카드 삭제
  @Transactional
  public ResponseEntity<CardResponse> deleteCard(Long id) {

    try{
      Optional<Card> optionalCard = cardRepository.findById(id);
      if (optionalCard.isPresent()){

        Card card= optionalCard.get();
        cardRepository.delete(card);
        return ResponseEntity.ok(CardResponse.builder().success(true).message("카드 삭제 성공하였습니다.").card(card).build());

      } else return ResponseEntity.ok(CardResponse.builder().success(false).message("해당 카드가 존재하지 않습니다.").build());

    }catch (Exception e){
      return ResponseEntity.ok(CardResponse.builder().success(false).message("카드를 삭제 중 에러가 발생했습니다.").build());
    }

  }

  // 카드 수정- 수정 시 기존 태그 리스트와 비교하여 태그 삭제 수정 추가
  @Transactional
  public ResponseEntity<CardResponse> updateCard(Long id, CardUpdateDto updatedCard) {

    try{

      Optional<Card> optionalCard = cardRepository.findById(id);
      Card newCard;

      if (optionalCard.isPresent()) {
        newCard = optionalCard.get();
        List<Tag> newTags = tagService.updateTags(newCard);

        newCard.setTagItems(newTags);
        newCard.setContent(updatedCard.getContent());
        newCard.setTitle(updatedCard.getTitle());
        newCard.setImgUrl(updatedCard.getImgUrl());

        return ResponseEntity.ok(CardResponse.builder().success(true).message("카드 수정 성공하였습니다.").card(newCard).build());
      } else return ResponseEntity.ok(CardResponse.builder().success(false).message("해당 카드가 존재하지 않습니다.").build());

    } catch (Exception e){
      return ResponseEntity.ok(CardResponse.builder().success(false).message("카드를 수정 중 에러가 발생했습니다.").build());

    }


  }
}
