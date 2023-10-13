package HookKiller.server.board.repository;

import HookKiller.server.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * BoardRepository: BoardRepository 인터페이스는 Board 엔터티를 데이터베이스에
 * 저장, 조회, 수정 및 삭제하기 위한 메서드를 제공합니다.
 * Spring Data JPA를 사용하여 기본 CRUD 작업을 자동으로 처리합니다.
 */

public interface BoardRepository extends JpaRepository<Board, Long> {
}
