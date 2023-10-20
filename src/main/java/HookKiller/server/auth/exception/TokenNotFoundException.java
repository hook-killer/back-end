package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;

public class TokenNotFoundException extends BaseException {

    public static final BaseException EXCEPTION = new TokenNotFoundException();

    private TokenNotFoundException() {
        super(AuthException.TOKEN_NOT_FOUND_ERROR);
    }
}
