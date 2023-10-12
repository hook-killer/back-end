package HookKiller.server.board.service;

import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.common.type.LanguageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleContentService {
  private final ArticleContentRepository articleContentRepository;

  public void createContent(PostArticleRequestDto requestDto, Article article) {

    List<LanguageType> targetLanguageList = Arrays.stream(LanguageType.values())
            .filter(languageType -> !languageType.equals(requestDto.getOrgArticleLanguage()))
            .toList();

    List<ArticleContent> articleContentList = new ArrayList<>(
            targetLanguageList.stream().map(
                    languageType -> ArticleContent.builder()
                            .article(article)
                            .language(languageType)
                            .title(getTranslatePapagoTextArticleContent(requestDto.getOrgArticleLanguage().getLanguageCode(), languageType.getLanguageCode(), requestDto.getTitle()))
                            .content(getTranslatePapagoHTMLArticleContent(requestDto.getOrgArticleLanguage().getLanguageCode(), languageType.getLanguageCode(), requestDto.getContent()))
                            .build()
            ).toList()
    );
    articleContentList.add(
            ArticleContent.builder()
                    .article(article)
                    .language(requestDto.getOrgArticleLanguage())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .build()
    );
    articleContentRepository.saveAll(articleContentList);
  }

  public void updateContent(PostArticleRequestDto requestDto, Article article) {
    // 게시물의 모든 내용을 찾습니다.
    List<ArticleContent> existingContents = articleContentRepository.findAllByArticle(article);

    // 원본 언어의 내용을 업데이트합니다.
    existingContents.stream()
            .filter(content -> content.getLanguage().equals(requestDto.getOrgArticleLanguage()))
            .forEach(content -> {
              content.articleUpdate(requestDto.getTitle(), requestDto.getContent());
            });

    // 다른 언어로 번역된 내용들을 업데이트합니다.
    for (LanguageType languageType : LanguageType.values()) {
      if (!languageType.equals(requestDto.getOrgArticleLanguage())) {
        existingContents.stream()
                .filter(content -> content.getLanguage().equals(languageType))
                .forEach(content -> {
                  content.articleUpdate(getTranslatePapagoTextArticleContent(requestDto.getOrgArticleLanguage().getLanguageCode(), languageType.getLanguageCode(), requestDto.getTitle()),
                          getTranslatePapagoHTMLArticleContent(requestDto.getOrgArticleLanguage().getLanguageCode(), languageType.getLanguageCode(), requestDto.getContent()));
                });
      }
    }

    articleContentRepository.saveAll(existingContents);
  }



  protected String getTranslatePapagoTextArticleContent(String source, String target, String content) {
    return null;
  }

  protected String getTranslatePapagoHTMLArticleContent(String source, String target, String content) {
    return null;
  }

}
