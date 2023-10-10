package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;

public class UserNotFoundException extends BaseException {
  
  public static final BaseException EXCEPTION = new UserNotFoundException();
  
  private UserNotFoundException() {
    super(AuthException.USER_NOT_FOUND_ERROR);
  }
}
