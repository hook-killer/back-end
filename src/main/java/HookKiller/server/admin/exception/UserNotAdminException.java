package HookKiller.server.admin.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.admin.exception.AdminException.USER_NOT_ADMIN;

public class UserNotAdminException extends BaseException {
    public static final BaseException EXCEPTION = new UserNotAdminException();

    public UserNotAdminException() {
        super(USER_NOT_ADMIN);
    }
}
