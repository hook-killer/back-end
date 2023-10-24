package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;

import HookKiller.server.board.repository.ArticleInterface;
import HookKiller.server.board.type.BoardType;
import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleRequestDto extends AbstractTimeStamp{

    private Long boardId;

    private Long articleId;
    
    private LanguageType orgArticleLanguage;

    private ArticleStatus status;

    private Integer likeCount;

    private LanguageType contentLanguage;

    private String title;

    private String content;

    private User createdUser;

    private User updatedUser;

    private BoardType boardType;
    
    public static ArticleRequestDto of(ArticleInterface articleInterface, User user) {
        return ArticleRequestDto.builder()
                .articleId(articleInterface.getId())
                .likeCount(articleInterface.getLikeCount())
                .title(articleInterface.getTitle())
                .content(articleInterface.getContent())
                .createdUser(user)
                .updatedUser(user)
                .build();
    }
    
    public static ArticleRequestDto of(Article article, ArticleContent articleContent) {
        ArticleRequestDto result = ArticleRequestDto.builder()
                .boardId(article.getBoard().getId())
                .articleId(article.getId())
                .title(articleContent.getTitle())
                .content(articleContent.getContent())
                .likeCount(article.getLikeCount())
                .status(article.getArticleStatus())
                .orgArticleLanguage(article.getOrgArticleLanguage())
                .contentLanguage(articleContent.getLanguage())
                .createdUser(article.getCreatedUser())
                .updatedUser(article.getUpdatedUser())
                .likeCount(article.getLikeCount())
                .boardType(article.getBoard().getBoardType())
                .build();
        result.setCreateAt(article.getCreateAt());
        result.setUpdateAt(article.getUpdateAt());

        return result;
    }

}
