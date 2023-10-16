package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Reply;
import HookKiller.server.board.entity.ReplyContent;
import HookKiller.server.common.type.LanguageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyContentRepository extends JpaRepository<ReplyContent, Long> {

  Optional<ReplyContent> findByReplyAndLanguage(Reply reply, LanguageType language);
}
