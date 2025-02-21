package com.likelion.trendithon.domain.tag.Service;

import com.likelion.trendithon.domain.tag.entity.Tag;
import com.likelion.trendithon.domain.tag.repository.TagRepository;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.likelion.trendithon.domain.card.entity.Card;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TagService {

  private TagRepository tagRepository;

  @Transactional
  public void createTag(List<Tag> tags) {

    tagRepository.saveAll(tags);
  }

  @Transactional
  public List<Tag> getTags(Card card) {

    return tagRepository.findByCard(card);
  }

  @Transactional
  public List<Tag> updateTags(Card card) {

    List<Tag> tags = tagRepository.findByCard(card);
    List<Tag> newTags = card.getTagItems();

    // tags엔 존재하는데 newTags에 존재하지 않으면 삭제 같으면 업뎃
    for (Tag tag : tags) {
      for (Tag newTag : newTags) {
        if (Objects.equals(tag.getTagId(), newTag.getTagId())) {
          tag.setTagTitle(newTag.getTagTitle());
          tag.setTagContent(newTag.getTagContent());
          break;
        }
      }

      tags.remove(tag);
      tagRepository.delete(tag);
    }

    // newTags엔 존재하는데 tags엔 존재하지 않으면 추가
    for (Tag newTag : newTags) {
      for (Tag tag : tags) {
        if (Objects.equals(tag.getTagId(), newTag.getTagId())) break;
      }

      tags.add(newTag);
      tagRepository.save(newTag);
    }

    return tags;
  }
}
