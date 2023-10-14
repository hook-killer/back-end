package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.type.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findAllByBoardAndArticleStatus(Board board, ArticleStatus status);

  Optional<Article> findByIdAndArticleStatus(Long id, ArticleStatus status);


}
