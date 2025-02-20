package com.likelion.trendithon.domain.tag;

import com.likelion.trendithon.domain.card.Card;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {

  private TagRepository tagRepository;

  public void createTag(List<Tag> tags){
    tagRepository.saveAll(tags);
  }

  public List<Tag> getTags(Card card){
    return tagRepository.findByCard(card);
  }

  public List<Tag> updateTags(Card card) {

    List<Tag> tags = tagRepository.findByCard(card);
    List<Tag> newTags = card.getTagItems();

    // tags�� �����ϴµ� newTags�� �������� ������ ���� ������ ����
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

    // newTags�� �����ϴµ� tags�� �������� ������ �߰�
    for (Tag newTag : newTags) {
      for (Tag tag : tags){
        if (Objects.equals(tag.getTagId(), newTag.getTagId())) break;
      }

      tags.add(newTag);
      tagRepository.save(newTag);
    }

    return tags;
  }
}