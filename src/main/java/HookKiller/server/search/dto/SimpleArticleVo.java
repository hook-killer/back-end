package HookKiller.server.search.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.repository.ArticleInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class SimpleArticleVo {
  
  private Long articleId;
  
  private String createdUserNickName;
  
  private String title;
  
  private String content;
  
  private Integer likeCount;
  
  public static SimpleArticleVo from(ArticleInterface articleInterface) {
    return SimpleArticleVo.builder()
            .articleId(articleInterface.getId())
            .createdUserNickName(articleInterface.getNickName())
            .title(articleInterface.getTitle())
            .content(articleInterface.getContent())
            .likeCount(articleInterface.getLikeCount())
            .build();
  }
  
  public static SimpleArticleVo of(Article article, ArticleContent articleContent) {
    return SimpleArticleVo.builder()
            .articleId(articleContent.getId())
            .createdUserNickName(article.getCreatedUser().getNickName())
            .content(articleContent.getContent())
            .likeCount(article.getLikeCount())
            .build();
  }
}
