package HookKiller.server.notice.repository;

import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.notice.entity.NoticeArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeArticleRepository extends JpaRepository<NoticeArticle, Long> {

    List<NoticeArticle> findAllByStatusOrderByCreateAtDesc(ArticleStatus status);

    Optional<NoticeArticle> findByIdAndStatus(Long id, ArticleStatus status);

    Page<NoticeArticle> findAllByStatusOrderByCreateAtDesc(ArticleStatus status, Pageable pageable);
}
