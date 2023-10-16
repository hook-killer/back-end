package HookKiller.server.board.exception;

import HookKiller.server.common.exception.BaseException;

public class ReplyContentNotFoundException extends BaseException {

  public static final BaseException EXCEPTION = new ReplyContentNotFoundException();

  private ReplyContentNotFoundException() {
    super(BoardException.REPLY_CONTENT_NOT_FOUND_ERROR);
  }

}
