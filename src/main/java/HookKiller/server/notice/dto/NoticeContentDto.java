package HookKiller.server.notice.dto;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeContentDto {

    private Long id;
    private LanguageType language;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    public static NoticeContentDto from(NoticeContent noticeContent) {

        return NoticeContentDto.builder()
                .id(noticeContent.getId())
                .language(noticeContent.getLanguage())
                .title(noticeContent.getTitle())
                .content(noticeContent.getContent())
                .build();
    }

    public NoticeContentDto of(NoticeArticle noticeArticle, NoticeContent noticeContent) {

        return NoticeContentDto.builder()
                .id(noticeContent.getId())
                .language(noticeContent.getLanguage())
                .title(noticeContent.getTitle())
                .content(noticeContent.getContent())
                .build();
    }

}
