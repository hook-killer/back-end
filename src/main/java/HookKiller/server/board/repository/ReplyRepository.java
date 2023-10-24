package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Reply;
import HookKiller.server.board.type.ReplyStatus;
import HookKiller.server.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  List<Reply> findAllByArticleAndReplyStatus(Article article, ReplyStatus replyStatus);

  Page<Reply> findAllByCreatedUserOrderByCreateAtDesc(User user, Pageable pageable);

  Page<Reply> findAllByCreatedUserAndReplyStatusOrderByCreateAtDesc(User user, ReplyStatus replyStatus, Pageable pageable);
}
