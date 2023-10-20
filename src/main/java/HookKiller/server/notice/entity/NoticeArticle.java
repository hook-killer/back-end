package HookKiller.server.notice.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tbl_notice_article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeArticle extends AbstractTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "noticeArticle", fetch = FetchType.LAZY)
    private List<NoticeContent> content = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User createdUser;

    @ManyToOne(fetch = FetchType.EAGER)
    private User updatedUser;

    @Builder
    public NoticeArticle(Long id, LanguageType language, ArticleStatus status, User createdUser,
                         User updatedUser) {
        this.id = id;
        this.language = language;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
    }

    public void updateStatus(ArticleStatus status) {
        this.status = status;
    }
}
