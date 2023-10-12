package HookKiller.server.board.entity;

import HookKiller.server.board.type.ArticleStatus;
import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
@Table(name = "tbl_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends AbstractTimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
  private List<ArticleLike> ArticleLike = new ArrayList<>();

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
  private List<ArticleContent> articleContent = new ArrayList<>();

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
  private List<Reply> reply = new ArrayList<>();

  @NotNull
  private LanguageType orgArticleLanguage;

  @NotNull
  @Enumerated(EnumType.STRING)
  private ArticleStatus articleStatus;

  private int likeCount;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private User createdUser;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private User updatedUser;

  public void updateStatus(String status) {
    articleStatus = ArticleStatus.valueOf(status);
  }

  @Builder
  public Article(Board board, Long id, LanguageType orgArticleLanguage, ArticleStatus articleStatus, User createdUser, User updatedUser) {
    this.board = board;
    this.id = id;
    this.articleStatus = articleStatus;
    this.orgArticleLanguage = orgArticleLanguage;
    this.createdUser = createdUser;
    this.updatedUser = updatedUser;
  }
}
