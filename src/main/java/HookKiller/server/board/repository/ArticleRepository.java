package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Board;

import HookKiller.server.common.type.ArticleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ArticleRepository extends JpaRepository<Article, Long> {

  Optional<Article> findByIdAndArticleStatus(Long id, ArticleStatus status);

  Page<Article> findAllByBoardAndArticleStatusOrderByCreateAtDesc(Board board, ArticleStatus status, Pageable pageable);


}
