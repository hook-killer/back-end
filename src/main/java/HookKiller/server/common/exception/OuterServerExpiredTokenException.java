package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.OUTER_SERVER_EXPIRED_TOKEN_EXCEPTION;

public class OuterServerExpiredTokenException extends BaseException {

    public static final OuterServerExpiredTokenException EXCEPTION = new OuterServerExpiredTokenException();

    public OuterServerExpiredTokenException() {
        super(OUTER_SERVER_EXPIRED_TOKEN_EXCEPTION);
    }
}
