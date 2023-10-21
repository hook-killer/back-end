package HookKiller.server.board.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReplyStatus {
  USABLE("공개상태"),
  DELETED("삭제처리");

  private final String typeName;
}
