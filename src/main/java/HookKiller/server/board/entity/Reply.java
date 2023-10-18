package HookKiller.server.board.entity;

import HookKiller.server.board.type.ReplyStatus;
import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * id : PK
 * article : 게시글 정보
 * replyContent : 댓글 내용
 * orgReplyLanguage : 원본으로 작성된 언어 타입
 * isDeleted : 댓글 삭제 여부
 * createdAt : 댓글 생성일
 * createdUser : 댓글 작성 사용자 ID
 * updatedAt : 댓글 정보 업데이트
 * updatedUser : 마지막에 수정한 사용자 ID
 */

@Entity
@Getter
@Table(name = "tbl_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reply extends AbstractTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "reply", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ReplyContent> replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LanguageType orgReplyLanguage;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReplyStatus replyStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User createdUser;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User updatedUser;

    @Builder
    public Reply(Long id, LanguageType orgReplyLanguage, ReplyStatus replyStatus,
                 User createdUser, User updatedUser, Article article) {
        this.id = id;
        this.orgReplyLanguage = orgReplyLanguage;
        this.replyStatus = replyStatus;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.article = article;
    }

    public void updateStatus(ReplyStatus status) {
        replyStatus = status;
    }

}
