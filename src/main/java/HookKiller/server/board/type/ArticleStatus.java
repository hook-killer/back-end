package HookKiller.server.board.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArticleStatus {
  PUBLIC("공개상태"),
  DELETE("삭제처리");

  private final String typeName;
}
