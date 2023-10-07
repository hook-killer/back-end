package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import lombok.Builder;
import lombok.Getter;
import org.joda.time.DateTime;

@Getter
@Builder
public class ArticleDto {
  private Long id;
  private String title;
  private Long createdUser;
  private DateTime createdAt;
  private int likeCount;

public static ArticleDto of(Article article, ArticleContent articleContent) {
    return ArticleDto.builder()
            .id(article.getId())
            .title(articleContent.getTitle())
            .createdUser(article.getCreatedUser())
            .createdAt(article.getCreatedAt())
            .likeCount(article.getLikeCount())
            .build();
  }




}
