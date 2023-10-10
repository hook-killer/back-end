package HookKiller.server.notice.dto;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.type.NoticeArticleStatus;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class NoticeArticleDto {

    private Long id;
    private LanguageType language;
    private NoticeArticleStatus status;
    private Timestamp createdAt;
    private Long createdUser;
    private Timestamp updatedAt;
    private Long updatedUser;

    public static NoticeArticleDto of(NoticeArticle noticeArticle, NoticeContent noticeContent) {
        return NoticeArticleDto.builder()
                .id(noticeArticle.getId())
                .language(noticeArticle.getLanguage())
                .status(noticeArticle.getStatus())
                .createdAt(noticeArticle.getCreatedAt())
                .createdUser(noticeArticle.getCreatedUser())
                .updatedAt(noticeArticle.getUpdatedAt())
                .updatedUser(noticeArticle.getUpdatedUser())
                .build();
    }
}
