package HookKiller.server.notice.dto;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import HookKiller.server.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeArticleDto extends AbstractTimeStamp {

    private Long id;
    private LanguageType language;
    private ArticleStatus status;
    private User createdUser;
    private User updatedUser;
    private String content;
    private String title;

    // TODO : 생성일자 , 수정일자 필요한지 없는지 생각해보기
    // TODO : NoticeArticle의 status인지 NoticeContent의 language인지 생각해보기
    // TODO : NoticeArticle 한개로도 끝날 수 있는거 아닌지 생각해보기
    public static NoticeArticleDto of(NoticeArticle noticeArticle, NoticeContent noticeContent) {
        return NoticeArticleDto.builder()
                .id(noticeArticle.getId())
                .language(noticeArticle.getLanguage())
                .status(noticeArticle.getStatus())
                .createdUser(noticeArticle.getCreatedUser())
                .updatedUser(noticeArticle.getUpdatedUser())
                .content(noticeContent.getContent())
                .title(noticeContent.getTitle())
                .build();
    }
}
