package HookKiller.server.repository;

import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.notice.entity.NoticeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeArticleRepository extends JpaRepository<NoticeArticle, Long> {

    List<NoticeArticle> findAllByStatus(ArticleStatus status);

}
