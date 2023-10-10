package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.type.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleContentRepository extends JpaRepository<ArticleContent, Long> {
  public Optional<ArticleContent> findByArticleAndLanguage(Article article, BoardType language);

}
