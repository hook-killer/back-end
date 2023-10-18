package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Board;

import HookKiller.server.common.type.ArticleStatus;
import HookKiller.server.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ArticleRepository extends JpaRepository<Article, Long> {
  Optional<Article> findByIdAndArticleStatus(Long id, ArticleStatus status);
  
  Page<Article> findAllByBoardAndArticleStatusOrderByCreateAtDesc(Board board, ArticleStatus status, Pageable pageable);
  
  Page<Article> findAllByCreatedUserOrderByCreateAtDesc(User user, Pageable pageable);
  
  @Query(
          value = "select a.id, u.nick_name as nickName, ac.title, ac.content, a.like_count as likeCount  " +
                  "from tbl_article a " +
                  "join tbl_article_content ac " +
                  "on a.id = ac.article_id " +
                  "join tbl_user u " +
                  "on a.created_user_id = u.id " +
                  "where ac.title like concat('%', :word, '%') or ac.content like concat('%', :word, '%')", nativeQuery = true
  )
  Page<ArticleInterface> retrieveArticleListDown(@Param(value = "word") String word, Pageable pageable);
}
