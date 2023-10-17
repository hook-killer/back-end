package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleRequestDto extends AbstractTimeStamp {

    private Long boardId;

    private Long articleId;

    @Enumerated(EnumType.STRING)
    private LanguageType orgArticleLanguage;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    private int likeCount;

    @Enumerated(EnumType.STRING)
    private LanguageType contentLanguage;

    private String title;

    private String content;

    private User createdUser;

    private User updatedUser;

    public static ArticleRequestDto of(Article article, ArticleContent articleContent) {
        return ArticleRequestDto.builder()
                .articleId(article.getId())
                .title(articleContent.getTitle())
                .content(articleContent.getContent())
                .likeCount(article.getLikeCount())
                .build();
    }

}
