package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.DELETE_FAIL_ERROR;

public class DeleteFailException extends BaseException {

    public static final BaseException EXCEPTION = new DeleteFailException();

    private DeleteFailException() {
        super(DELETE_FAIL_ERROR);
    }
}
