package HookKiller.server.board.repository;

import HookKiller.server.board.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
}
