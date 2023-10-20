package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;

public class TokenException extends BaseException {
  
  public static final BaseException EXCEPTION = new TokenException();
  
  private TokenException() {
    super(AuthException.TOKEN_NOT_FOUND_ERROR);
  }
}
