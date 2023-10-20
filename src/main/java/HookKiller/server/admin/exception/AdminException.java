package HookKiller.server.admin.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum AdminException implements BaseErrorCode {
    USER_NOT_ADMIN(UNAUTHORIZED.value(), "Admin_401_1", "권한이 없습니다.")
    ;

    private final Integer statusCode;
    private final String errorCode;
    private final String reason;

    @Override
    public ErrorDetail getErrorDetail() {
        return ErrorDetail.of(statusCode, errorCode, reason);
    }
}
