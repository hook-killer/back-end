package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {

  public List<Article> findAllByBoard(Board board);

  List<Article> findAllByBoardAndStatus(Board board, String status);

}
