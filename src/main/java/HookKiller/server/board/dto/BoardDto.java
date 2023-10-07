package HookKiller.server.board.dto;

import HookKiller.server.board.entity.Board;
import HookKiller.server.board.repository.BoardRepository;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Builder
public class BoardDto {

  private Long id;
  private String name;
  private String boardType;
  private String description;

  public BoardDto from(Board board) {
    return BoardDto.builder()
            .id(board.getId())
            .name(board.getName())
            .description(board.getDescription())
            .build();
  }

}
