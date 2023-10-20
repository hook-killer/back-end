package HookKiller.server.notice.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.notice.exception.NoticeException.NOTICE_NOT_AUTH;

public class NoticeNotAdminForbiddenException extends BaseException {
    public static final BaseException EXCEPTION = new NoticeNotAdminForbiddenException();

    private NoticeNotAdminForbiddenException() {
        super(NOTICE_NOT_AUTH);
    }
}
