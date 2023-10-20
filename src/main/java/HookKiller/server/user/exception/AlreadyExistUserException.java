package HookKiller.server.user.exception;

import HookKiller.server.common.exception.BaseException;

public class AlreadyExistUserException extends BaseException {
  
  public static final BaseException EXCEPTION = new AlreadyExistUserException();
  
  private AlreadyExistUserException() {
    super(UserException.ALREADY_EXIST_USER_ERROR);
  }
}
