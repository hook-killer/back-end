package HookKiller.server.search.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KeyDownArticleDto {
  
  private Long id;
  
  private User createdUser;
  
  private ArticleContent content;
  
  private int likeCount;
  
  public static KeyDownArticleDto of(Article article, ArticleContent articleContent) {
    return KeyDownArticleDto.builder()
            .id(article.getId())
            .createdUser(article.getCreatedUser())
            .content(articleContent)
            .likeCount(article.getLikeCount())
            .build();
  }
}
