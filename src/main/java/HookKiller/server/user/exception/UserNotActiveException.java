package HookKiller.server.user.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.user.exception.UserException.USER_NOT_ACTIVE_ERROR;

public class UserNotActiveException extends BaseException {

    public static final BaseException EXCEPTION = new UserNotActiveException();

    private UserNotActiveException() {
        super(USER_NOT_ACTIVE_ERROR);
    }
}