package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.type.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findAllByBoardAndArticleStatus(Board board, ArticleStatus status);

}
