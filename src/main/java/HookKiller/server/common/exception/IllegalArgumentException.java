package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.ILLEGAL_ARGUMENT_ERROR;

public class IllegalArgumentException extends BaseException {
    public static final BaseException EXCEPTION = new IllegalArgumentException();

    public IllegalArgumentException() {
        super(ILLEGAL_ARGUMENT_ERROR);
    }
}
