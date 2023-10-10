package HookKiller.server.notice.dto;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.type.NoticeArticleStatus;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeArticleDto extends AbstractTimeStamp {

    private Long id;
    private LanguageType language;
    private NoticeArticleStatus status;
    private Long createdUser;
    private Long updatedUser;
    private String content;
    private String title;

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
