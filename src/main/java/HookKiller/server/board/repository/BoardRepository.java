package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Board;

import java.util.List;

public interface BoardRepository {

    Board save(Board board);
    List<Board> findAll();

}
