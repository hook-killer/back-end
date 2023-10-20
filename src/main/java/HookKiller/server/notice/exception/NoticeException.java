package HookKiller.server.notice.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum NoticeException implements BaseErrorCode {
    NOTICE_NOT_FOUND(NOT_FOUND.value(), "404-1", "요청한 공지사항 게시물이 존재하지 않습니다."),
    NOTICE_NOT_AUTH(FORBIDDEN.value(), "404-2", "권한이 없습니다.");

    private final Integer statusCode;
    private final String errorCode;
    private final String reason;

    @Override
    public ErrorDetail getErrorDetail() {
        return ErrorDetail.of(statusCode, errorCode, reason);
    }
}
