package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.OUTER_SERVER_BAD_REQUEST_EXCEPTION;

public class OuterServerBadRequestException extends BaseException {

    public static final BaseException EXCEPTION  = new OuterServerBadRequestException();

    public OuterServerBadRequestException() {
        super(OUTER_SERVER_BAD_REQUEST_EXCEPTION);
    }
}
