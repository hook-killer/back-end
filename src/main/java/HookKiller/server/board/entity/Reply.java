package HookKiller.server.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    @OneToMany
    private List<ReplyContent> replyContent;

    private String orgReplyLanguage;
    private boolean isDeleted;
    private DateTime createdAt;
    private long createdUser;
    private DateTime updatedAt;
    private long updatedUser;
}
