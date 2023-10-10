package HookKiller.server.notice.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.notice.exception.NoticeException.NOTICE_ARTICLE_NOT_FOUND_EXCEPTION;
import static HookKiller.server.notice.exception.NoticeException.NOTICE_CONTENT_NOT_FOUND_EXCEPTION;

public class NoticeContentNotFoundException extends BaseException {
    public static final BaseException EXCEPTION = new NoticeContentNotFoundException();

    private NoticeContentNotFoundException() {
        super(NOTICE_CONTENT_NOT_FOUND_EXCEPTION);
    }
}
