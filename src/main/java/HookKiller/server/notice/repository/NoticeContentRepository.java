package HookKiller.server.notice.repository;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeContentRepository extends JpaRepository<NoticeContent, Long> {
    Optional<NoticeContent> findByNoticeArticleAndLanguage(NoticeArticle noticeArticle, LanguageType languageType);

    List<NoticeContent> findAllByNoticeArticle(NoticeArticle noticeArticle);
}
