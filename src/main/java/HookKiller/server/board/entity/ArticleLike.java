package HookKiller.server.board.entity;


import HookKiller.server.common.AbstractTimeStamp;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * id : PK
 * article : 게시물 정보
 * userId : 좋아요를 누른 사용자의 userId
 * createdAt : 게시물 좋아요를 클릭한 일자
 */

@Entity
@Getter
@Table(name = "tbl_article_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleLike extends AbstractTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    private Long userId;
}
