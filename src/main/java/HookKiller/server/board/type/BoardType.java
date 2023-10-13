package HookKiller.server.board.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardType {
    KOREA("한국 게시판"),
    JAPAN("일본 게시판"),
    CHINA("중국 게시판");

    private final String typeName;
}
