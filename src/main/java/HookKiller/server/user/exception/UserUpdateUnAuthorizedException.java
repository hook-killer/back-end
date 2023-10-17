package HookKiller.server.user.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.user.exception.UserException.USER_UPDATE_UNAUTHORIZED;

public class UserUpdateUnAuthorizedException extends BaseException {

    public static final BaseException EXCEPTION = new UserUpdateUnAuthorizedException();

    private UserUpdateUnAuthorizedException() {
        super(USER_UPDATE_UNAUTHORIZED);
    }
}

