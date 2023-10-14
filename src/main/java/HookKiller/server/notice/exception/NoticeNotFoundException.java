package HookKiller.server.notice.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.notice.exception.NoticeException.NOTICE_NOT_FOUND;

public class NoticeNotFoundException extends BaseException {
    public static final BaseException EXCEPTION = new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(NOTICE_NOT_FOUND);
    }
}
