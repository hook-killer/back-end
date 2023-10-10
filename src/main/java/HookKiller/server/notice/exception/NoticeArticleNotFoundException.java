package HookKiller.server.notice.exception;

import HookKiller.server.common.exception.BaseException;

import static HookKiller.server.notice.exception.NoticeException.NOTICE_ARTICLE_NOT_FOUND_EXCEPTION;

public class NoticeArticleNotFoundException extends BaseException {
    public static final BaseException EXCEPTION = new NoticeArticleNotFoundException();

    private NoticeArticleNotFoundException() {
        super(NOTICE_ARTICLE_NOT_FOUND_EXCEPTION);
    }
}
