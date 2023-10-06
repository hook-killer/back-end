package HookKiller.server.board.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * id : PK
 * article : 게시글 정보
 * language : 적용된 언어 타입
 * title : 게시글 제목
 * content : 게시글 내용
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    private String language;
    private String title;

    @Column
    @Lob
    private String content;

}
