package HookKiller.server.notice.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum NoticeException implements BaseErrorCode {
    NOTICE_ARTICLE_NOT_FOUND_EXCEPTION(NOT_FOUND.value(), "404-1", "공지사항 게시물이 존재하지 않습니다."),
    NOTICE_CONTENT_NOT_FOUND_EXCEPTION(NOT_FOUND.value(), "404-2", "게시글이 존재하지 않습니다.");

    private final Integer statusCode;
    private final String errorCode;
    private final String reason;

    @Override
    public ErrorDetail getErrorDetail() {
        return ErrorDetail.of(statusCode, errorCode, reason);
    }
}
