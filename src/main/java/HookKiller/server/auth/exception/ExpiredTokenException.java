package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;
import HookKiller.server.common.exception.GlobalException;

public class ExpiredTokenException extends BaseException {

    public static final BaseException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(GlobalException.EXPIRED_TOKEN_EXCEPTION);
    }
}
