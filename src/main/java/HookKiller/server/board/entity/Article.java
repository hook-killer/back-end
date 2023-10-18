package HookKiller.server.board.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import jakarta.persistence.CascadeType;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * id : PK 키
 * orgArticleLanguage : 원본으로 작성된 언어 타입. KOR:한국어, ENG:영어, CHI:중국어, JPN:일본어
 * status: 게시물 상태. PUBLIC:공개, DELETE:삭제처리
 * likeCount : 좋아요 갯수.
 * isDeleted : 게시글 삭제 여부
 * createdAt : 게시글 생성일
 * createdUser : 게시글 작성 사용자 ID입력
 * updatedUser : 마지막에 수정한 사용자 ID입력
 */

@Entity
@Getter
@Setter
@Table(name = "tbl_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends AbstractTimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<ArticleLike> ArticleLike = new ArrayList<>();

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<ArticleContent> articleContent = new ArrayList<>();

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Reply> reply = new ArrayList<>();

  @NotNull
  @Enumerated(EnumType.STRING)
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

  public void updateStatus(ArticleStatus status) {
    articleStatus = status;
  }

  public void addLikeCount() {
    this.likeCount++;
  }
  public void minusLikeCount() {
    if(this.likeCount > 0)
      this.likeCount--;
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
