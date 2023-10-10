package HookKiller.server.board.entity;


import HookKiller.server.common.AbstractTimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

/**
 * id : PK
 * article : 게시물 정보
 * userId : 좋아요를 누른 사용자의 userId
 * createdAt : 게시물 좋아요를 클릭한 일자
 */

@Entity
@Getter
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
