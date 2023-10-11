package HookKiller.server.notice.entity;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.dto.NoticeContentDto;
import jakarta.persistence.*;
import lombok.*;

/**
 * language : title, content가 적용된 언어
 * title : 공지사항 제목
 * content : 공지사항 내용
 */

@Entity
@Getter
@Setter
@Table(name = "tbl_notice_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private NoticeArticle noticeArticle;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    private String title;

    @Lob
    private String content;

    @Builder
    public NoticeContent(Long id, NoticeArticle noticeArticle,
                         LanguageType language, String title, String content) {
        this.noticeArticle = noticeArticle;
        this.language = language;
        this.title = title;
        this.content = content;
    }

    public void update(NoticeContentDto contentDto) {
        this.title = contentDto.getTitle();
        this.content = contentDto.getContent();
    }
}
