package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.TYPE_BAD_REQUEST_ERROR;

public class BadTypeRequestException extends BaseException {
    public static final BaseException EXCEPTION = new BadTypeRequestException();

    private BadTypeRequestException() {
        super(TYPE_BAD_REQUEST_ERROR);
    }
}

