package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.type.BoardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class ArticleDto {

  private Long articleId;
  private String title;

  @Lob
  private String content;
  private Timestamp createdAt;
  private Long createdUserId;
  private int likeCount;

  private Long boardId;

  @Enumerated(EnumType.STRING)
  private BoardType boardType;

  public static ArticleDto of(Article article, ArticleContent articleContent) {
    return ArticleDto.builder()
            .articleId(article.getId())
            .title(articleContent.getTitle())
            .content(articleContent.getContent())
            .createdAt(article.getCreateAt())
            .createdUserId(article.getCreatedUserId())
            .likeCount(article.getLikeCount())
            .build();
  }

}
