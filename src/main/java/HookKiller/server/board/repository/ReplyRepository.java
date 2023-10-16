package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Reply;
import HookKiller.server.board.type.ReplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  List<Reply> findAllByArticleAndReplyStatus(Article article, ReplyStatus replyStatus);
}
