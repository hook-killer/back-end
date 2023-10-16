package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.OUTER_SERVER_FORBIDDEN_EXCEPTION;

public class OuterServerForbiddenException extends BaseException {

    public static final OuterServerForbiddenException EXCEPTION = new OuterServerForbiddenException();

    public OuterServerForbiddenException() {
        super(OUTER_SERVER_FORBIDDEN_EXCEPTION);
    }
}
