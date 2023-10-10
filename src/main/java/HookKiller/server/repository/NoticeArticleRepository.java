package HookKiller.server.repository;

import HookKiller.server.notice.entity.NoticeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeArticleRepository extends JpaRepository<NoticeArticle, Long> {
    NoticeArticle findAllById(NoticeArticle noticeArticle);

}
