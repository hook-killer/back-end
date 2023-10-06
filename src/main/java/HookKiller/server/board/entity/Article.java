package HookKiller.server.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;

/**
 * orgArticleLanguage : 원본으로 작성된 언어 타입. KOR:한국어, ENG:영어, CHI:중국어, JPN:일본어
 * articleType : 게시물 종류. NOTI:공지사항, NORMAL:일반적인 게시물
 * status : 게시물 상태. WRITING:작성중, PUBLIC:공개상태, HIDING:숨김처리, DELETE:삭제처리
 * likeCount : 좋아요 갯수.
 * isDeleted : 게시글 삭제 여부
 * createdAt : 게시글 생성일
 * createdUser : 게시글 작성 사용자 ID입력
 * updatedAt : 게시글 정보 업데이트 일자
 * updatedUser : 마지막에 수정한 사용자 ID입력
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @OneToMany
    private List<ArticleLike> ArticleLike;

    @OneToMany
    private List<ArticleContent> articleContent;

    @OneToMany
    private List<Reply> reply;

    private String orgArticleLanguage;
    private String articleType;
    private String status;
    private int likeCount;
    private boolean isDeleted;
    private DateTime createdAt;
    private long createdUser;
    private DateTime updatedAt;
    private long updatedUser;
}
