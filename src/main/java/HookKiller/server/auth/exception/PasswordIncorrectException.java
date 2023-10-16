package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;
import HookKiller.server.common.exception.GlobalException;

public class PasswordIncorrectException extends BaseException {
  
  public static final BaseException EXCEPTION = new PasswordIncorrectException();
  
  private PasswordIncorrectException() {
    super(AuthException.PASSWORD_INCORRECT_ERROR);
  }
}
