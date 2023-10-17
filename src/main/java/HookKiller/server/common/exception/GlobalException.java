package HookKiller.server.common.exception;

import HookKiller.server.common.dto.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum GlobalException implements BaseErrorCode {
    EXAMPLE_ERROR(BAD_REQUEST.value(), "400-0", "에러 예시 입니다."),
    FEIGN_SERVER_ERROR(BAD_REQUEST.value(), "400-0", "Feign 요청 시 외부 서버 오류입니다."),
    METHOD_ARGUMENT_ERROR(
            BAD_REQUEST.value(), "400-1", "메서드 인자가 유효하지 않거나 @Valid를 통과하지 못하여 발생하는 예외입니다."),
    EXPIRED_TOKEN_EXCEPTION(UNAUTHORIZED.value(), "401-1", "토큰이 만료되었습니다."),
    INVALID_TOKEN_EXCEPTION(UNAUTHORIZED.value(), "401-2", "올바르지 않은 토큰입니다."),
    OUTER_SERVER_UNAUTHORIZED_EXCEPTION(UNAUTHORIZED.value(), "Feign-401-1", "외부 서버 401 오류입니다."),
    OUTER_SERVER_FORBIDDEN_EXCEPTION(FORBIDDEN.value(), "Feign-403-1", "외부 서버 403 오류입니다."),
    OUTER_SERVER_BAD_REQUEST_EXCEPTION(BAD_REQUEST.value(), "Feign-400-1", "외부 서버 400 오류입니다."),
    OUTER_SERVER_EXPIRED_TOKEN_EXCEPTION(BAD_REQUEST.value(), "Feign-400-2", "외부 서버 토큰 만료 오류입니다."),
    SECURITY_CONTEXT_NOT_FOUND_ERROR(INTERNAL_SERVER_ERROR.value(), "500-2", "Security Context 에러입니다."),
    ILLEGAL_ARGUMENT_ERROR(BAD_REQUEST.value(), "500-00", "인자 값 문제로 인한 오류가 발생하였습니다"),
    INTERNAL_SERVER_ERRORS(INTERNAL_SERVER_ERROR.value(), "500-1", "서버 내부 오류입니다."),
    FILE_UPLOAD_ERROR(INTERNAL_SERVER_ERROR.value(), "500-10", "파일 업로드 중 오류가 발생하였습니다"),
    FILE_DELETE_ERROR(INTERNAL_SERVER_ERROR.value(), "500-11", "파일 삭제 중 오류가 발생하였습니다"),
    FILE_IO_ERROR(INTERNAL_SERVER_ERROR.value(), "500-12", "파일 변환 중 오류가 발생하였습니다"),
    MAIL_SEND_ERROR(INTERNAL_SERVER_ERROR.value(), "500-20", "메일 발송중 오류가 발생하였습니다"),
    NAVER_ERROR(INTERNAL_SERVER_ERROR.value(), "500-30", "네이버 번역 도중 오류가 발생하였습니다"),
    DELETE_FAIL_ERROR(INTERNAL_SERVER_ERROR.value(), "500-02", "삭제처리 도중 오류가 발생하였습니다"),
    TYPE_BAD_REQUEST_ERROR(BAD_REQUEST.value(), "400-1", "잘못된 타입을 요청하였습니다.");
    private final Integer statusCode;
    private final String errorCode;
    private final String reason;

    @Override
    public ErrorDetail getErrorDetail() {
        return ErrorDetail.of(statusCode, errorCode, reason);
    }
}
