package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import lombok.Builder;
import lombok.Getter;
import org.joda.time.DateTime;

import java.sql.Timestamp;

@Getter
@Builder
public class ArticleDto {

  private Long articleId;
  private String title;
  private Timestamp createdAt;
  private Long createdUser;
  private int likeCount;

  public static ArticleDto of(Article article, ArticleContent articleContent) {
    return ArticleDto.builder()
            .articleId(article.getId())
            .title(articleContent.getTitle())
            .createdAt(article.getCreateAt())
            .createdUser(article.getCreatedUser())
            .likeCount(article.getLikeCount())
            .build();
  }

}
