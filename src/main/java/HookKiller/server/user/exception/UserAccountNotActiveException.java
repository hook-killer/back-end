package HookKiller.server.user.exception;


import HookKiller.server.common.exception.BaseException;

public class UserAccountNotActiveException extends BaseException {

    public static final BaseException EXCEPTION = new UserAccountNotActiveException();

    private UserAccountNotActiveException() {
        super(UserException.USER_ACCOUNT_NOT_ACTIVE);
    }
}
