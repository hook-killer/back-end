package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleContentRepository extends JpaRepository<ArticleContent, Long> {
  Optional<ArticleContent> findByArticleAndLanguage(Article article, String language);
}
