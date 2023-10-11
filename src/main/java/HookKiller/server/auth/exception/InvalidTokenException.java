package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;

public class InvalidTokenException extends BaseException {

    public static final BaseException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(AuthException.INVALID_TOKEN_ERROR);
    }
}
