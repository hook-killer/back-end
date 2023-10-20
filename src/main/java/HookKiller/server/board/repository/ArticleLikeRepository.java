package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleLike;
import HookKiller.server.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    Optional<ArticleLike> findByArticleAndUser(Article article, User user);

    Optional<ArticleLike> deleteByArticleAndUser(Article article, User user);

    Page<ArticleLike> findAllByUserOrderByCreateAtDesc(User user, Pageable pageable);
}
