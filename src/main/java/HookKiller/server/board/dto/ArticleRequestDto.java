package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.type.ArticleStatus;
import HookKiller.server.common.type.LanguageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class ArticleRequestDto {

  private Long boardId;
  private Long articleId;
  @Enumerated(EnumType.STRING)
  private LanguageType orgArticleLanguage;
  @Enumerated(EnumType.STRING)
  private ArticleStatus status;
  private int likeCount;
  private Timestamp createdAt;
  private Long createdUserId;
  private Timestamp updatedAt;
  private Long updatedUserId;

  private Long contentId;
  @Enumerated(EnumType.STRING)
  private LanguageType contentLanguage;
  private String title;
  @Lob
  private String content;

  public static ArticleRequestDto of(Article article, ArticleContent articleContent) {
    return ArticleRequestDto.builder()
            .articleId(article.getId())
            .title(articleContent.getTitle())
            .content(articleContent.getContent())
            .createdAt(article.getCreateAt())
            .likeCount(article.getLikeCount())
            .build();
  }

}
