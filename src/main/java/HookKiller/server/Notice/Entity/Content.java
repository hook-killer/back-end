package HookKiller.server.Notice.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * language : title, content가 적용된 언어
 * title : 공지사항 제목
 * content : 공지사항 내용
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Article article;

    private String language;
    private String title;

    @Column
    @Lob
    private String content;
}
