package com.likelion.trendithon.domain.card;

import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/cards")
public class CardController {

  private CardService cardService;

  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> createCard(@RequestBody Card card) {
    try {
      Card result = cardService.createCard(card);

      Map<String, Object> response = new HashMap<>();
      response.put("message", "Card 생성 성공");
      response.put("card", result);

      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {

      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("message", "Error 발생");
      errorResponse.put("error", e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getCardById(@PathVariable Long id) {
    try {
      Card card = cardService.getCardById(id);
      if (card == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 Card가 존재하지 않음");
      }
      return ResponseEntity.ok(card);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error 발생");
    }
  }

  @GetMapping()
  public ResponseEntity<?> getAllCards() {
    try {
      List<CardResponseDto> cards = cardService.getAllCards();
      if (cards.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("카드가 존재하지 않음");
      }
      return ResponseEntity.ok(cards);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error 발생");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> deleteCard(@PathVariable Long id) {
    cardService.deleteCard(id);

    Map<String, String> response = new HashMap<>();
    response.put("message", "Card 삭제 완료");

    return ResponseEntity.ok(response);
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<?> updateCard(@PathVariable Long id, @RequestBody Card updatedCard) {
    Card newCard = cardService.updateCard(id, updatedCard);

    if (newCard == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("카드가 존재하지 않음");
    } else {
      return ResponseEntity.ok(newCard);
    }
  }




}
