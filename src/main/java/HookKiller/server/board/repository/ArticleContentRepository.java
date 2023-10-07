package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleContentRepository extends JpaRepository<ArticleContent, Long> {
  public Optional<ArticleContent> findByArticleAndLanguage(Article article, String language);

}
