package HookKiller.server.board.exception;

import HookKiller.server.common.exception.BaseException;

public class ReplyDeleteUnauthorizedUserException extends BaseException {
  
  public static final BaseException EXCEPTION = new ReplyDeleteUnauthorizedUserException();
  
  private ReplyDeleteUnauthorizedUserException() {
    super(BoardException.REPLY_DELETE_UNAUTHORIZED_USER_ERROR);
  }
}
