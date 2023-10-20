package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.common.type.LanguageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleContentRepository extends JpaRepository<ArticleContent, Long> {

    Optional<ArticleContent> findByArticleAndLanguage(Article article, LanguageType language);

    List<ArticleContent> findAllByArticle(Article article);
    
    Page<ArticleContent> findDistinctByTitleContainsOrContentContains(String title, String content, Pageable pageable);
    
    Optional<ArticleContent> findByArticleAndContentContains(Article article, String word);
}
