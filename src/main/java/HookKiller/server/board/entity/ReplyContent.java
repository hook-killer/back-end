package HookKiller.server.board.entity;

import HookKiller.server.common.type.LanguageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * language : content가 적용된 언어 타입.
 * content : 댓글 내용.
 */

@Entity
@Getter
@Table(name = "tbl_reply_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyContent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reply_id")
    private Reply reply;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @Column(columnDefinition = "TEXT")
    @Lob
    private String content;

    @Builder
    public ReplyContent(Long id, Reply reply, LanguageType language, String content) {
        this.id = id;
        this.reply = reply;
        this.language = language;
        this.content = content;
    }

}
