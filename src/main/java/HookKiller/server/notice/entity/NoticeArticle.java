package HookKiller.server.notice.entity;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.type.NoticeArticleStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * orgArticleLanguage : 원본으로 작성된 언어 타입. KOR:한국어, ENG:영어, CHI:중국어, JPN:일본어
 * status : 공지글 상태. PUBLIC:공개상태, DELETE:삭제처리
 * createdAt : 공지글 생성일
 * createdUser : 공지글 작성 사용자 ID입력.
 * updatedAt : 공지글 정보 업데이트 일자
 * updatedUser : 마지막에 수정한 사용자 ID입력.
 */

@Entity
@Getter
@Table(name = "tbl_notice_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<NoticeContent> content;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @Enumerated(EnumType.STRING)
    private NoticeArticleStatus status;

    private Timestamp createdAt;
    private Long createdUser;
    private Timestamp updatedAt;
    private Long updatedUser;

    @Builder
    public NoticeArticle(Long id, List<NoticeContent> content, LanguageType language,
                         NoticeArticleStatus noticeArticleStatus, Timestamp createdAt, Long createdUser, Timestamp updatedAt, Long updatedUser) {
        this.id = id;
        this.content = content;
        this.language = language;
        this.status = noticeArticleStatus;
        this.createdAt = createdAt;
        this.createdUser = createdUser;
        this.updatedAt = updatedAt;
        this.updatedUser = updatedUser;
    }
}
