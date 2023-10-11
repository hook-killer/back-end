package HookKiller.server.repository;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.notice.entity.NoticeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeArticleRepository extends JpaRepository<NoticeArticle, Long> {

    Optional<NoticeArticle> findByLanguage(LanguageType languageType);
    List<NoticeArticle> findAllByStatus(ArticleStatus status);

}
