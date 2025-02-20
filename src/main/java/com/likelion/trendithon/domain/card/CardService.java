package com.likelion.trendithon.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.likelion.trendithon.domain.tag.Tag;
import com.likelion.trendithon.domain.tag.TagService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardService {

  private CardRepository cardRepository;
  private TagService tagService;

  public Card createCard(Card card) {
    List<Tag> tags = card.getTagItems();
    tagService.createTag(tags);
    return cardRepository.save(card);
  }

  public Card getCardById(Long id) {
    Optional<Card> optionalCard = cardRepository.findById(id);
    Card card;
    if (optionalCard.isPresent()) {
      card = optionalCard.get();
      card.setTagItems(tagService.getTags(card));
      return card;
    }

    return null;
  }

  public List<CardResponseDto> getAllCards() {
    List<Card> cardList = cardRepository.findAll();
    List<CardResponseDto> cardDtos = new ArrayList<>();

    for (Card card : cardList) {
      cardDtos.add(new CardResponseDto(card.getCardId(), card.getTitle()));
    }

    return cardDtos;
  }

  public void deleteCard(Long id) {
    Optional<Card> optionalCard = cardRepository.findById(id);
    optionalCard.ifPresent(card -> cardRepository.delete(card));
  }

  public Card updateCard(Long id, Card updatedCard) {
    Optional<Card> optionalCard = cardRepository.findById(id);
    Card newCard;
    if (optionalCard.isPresent()) {
      newCard = optionalCard.get();
      List<Tag> newTags = tagService.updateTags(newCard);

      newCard.setTagItems(newTags);
      newCard.setContent(updatedCard.getContent());
      newCard.setTitle(updatedCard.getTitle());
      newCard.setImgUrl(updatedCard.getImgUrl());

      return cardRepository.save(newCard);
    } else return null;
  }
}
