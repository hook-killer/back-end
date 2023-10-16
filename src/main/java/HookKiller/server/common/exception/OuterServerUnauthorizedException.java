package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.OUTER_SERVER_UNAUTHORIZED_EXCEPTION;

public class OuterServerUnauthorizedException extends BaseException {

    public static final OuterServerUnauthorizedException EXCEPTION = new OuterServerUnauthorizedException();

    public OuterServerUnauthorizedException() {
        super(OUTER_SERVER_UNAUTHORIZED_EXCEPTION);
    }
}
