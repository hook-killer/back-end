package HookKiller.server.auth.exception;

import HookKiller.server.common.exception.BaseException;

public class StatusNotVerificationException extends BaseException {
    public static final BaseException EXCEPTION = new StatusNotVerificationException();

    private StatusNotVerificationException() {
        super(AuthException.STATUS_NOT_VERIFICATION_ERROR);
    }
}
