package HookKiller.server.board.repository;

import HookKiller.server.board.entity.ReplyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyContentRepository extends JpaRepository<ReplyContent, Long> {
}
