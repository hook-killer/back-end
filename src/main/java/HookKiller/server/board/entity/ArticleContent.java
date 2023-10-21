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
import lombok.Setter;

/**
 * id : PK
 * article : 게시글 정보
 * language : 적용된 언어 타입
 * title : 게시글 제목
 * content : 게시글 내용
 */

@Entity
@Getter
@Setter
@Table(name = "tbl_article_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @NotNull
    private String title;

    @Lob
    @NotNull
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Builder
    public ArticleContent(Article article, LanguageType language, String title, String content) {
        this.article = article;
        this.language = language;
        this.title = title;
        this.content = content;
    }
}
