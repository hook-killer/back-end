package HookKiller.server.notice.dto;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeContentDto {

    private Long id;
    private LanguageType language;
    private String title;
    private String content;

    public NoticeContentDto of(NoticeArticle noticeArticle, NoticeContent noticeContent) {

        return NoticeContentDto.builder()
                .id(noticeContent.getId())
                .language(noticeContent.getLanguage())
                .title(noticeContent.getTitle())
                .content(noticeContent.getContent())
                .build();
    }
}
