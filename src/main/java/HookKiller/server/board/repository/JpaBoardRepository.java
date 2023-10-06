package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;

public interface JpaBoardRepository extends JpaRepository<Board, Long>, BoardRepository {

    @Override
    default Board save(Board board) {
        return null;
    }

    @Override
    List<Board> findAll();
}
