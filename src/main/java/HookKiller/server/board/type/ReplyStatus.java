package HookKiller.server.board.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReplyStatus {
  FALSE("공개상태"),
  TURE("삭제처리");

  private final String typeName;
}
