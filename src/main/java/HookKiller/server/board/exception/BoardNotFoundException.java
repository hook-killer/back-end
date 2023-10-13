package HookKiller.server.board.exception;

import HookKiller.server.common.exception.BaseException;

public class BoardNotFoundException extends BaseException {

  public static final BaseException EXCEPTION = new BoardNotFoundException();

  private BoardNotFoundException() {
    super(BoardException.BOARD_NOT_FOUND_ERROR);
  }

}
