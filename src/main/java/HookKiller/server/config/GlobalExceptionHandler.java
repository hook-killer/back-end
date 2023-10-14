package HookKiller.server.config;


import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.dto.ErrorResponse;
import HookKiller.server.common.exception.BaseErrorCode;
import HookKiller.server.common.exception.BaseException;
import HookKiller.server.common.exception.GlobalException;
import HookKiller.server.common.exception.OuterServerException;
import HookKiller.server.common.util.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import static HookKiller.server.common.exception.GlobalException.METHOD_ARGUMENT_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> internalServerExceptionHandle(
            Exception e, HttpServletRequest req) throws Exception {
//        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) req;
//        final Long userId = UserUtils.getCurrentUserId();
        String url =
                UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(req))
                        .build()
                        .toUriString();
        log.error("ExceptionHandler : {}", String.valueOf(e));
        e.printStackTrace();
        GlobalException internalServerError = GlobalException.INTERNAL_SERVER_ERRORS;
        ErrorResponse errorResponse = new ErrorResponse(internalServerError.getErrorDetail());

        return ResponseEntity.status(HttpStatus.valueOf(internalServerError.getStatusCode()))
                .body(errorResponse);
    }

    @ExceptionHandler(OuterServerException.class)
    protected ResponseEntity<ErrorResponse> outerServerExceptionHandle(OuterServerException e) {
      log.error("GlobalExceptionHanlder의 OuterServerErrorHandler");
        ErrorDetail errorDetail =
                ErrorDetail.of(e.getStatusCode(), e.getErrorCode(), e.getReason());
        log.error("StatusCode >>> {} , ErrorCode >>> {} , Reason >>> {} , cause >>> {}", e.getStatusCode(), e.getErrorCode(), e.getReason(), e.getCause());
        ErrorResponse errorResponse = new ErrorResponse(errorDetail);
        return ResponseEntity.status(HttpStatus.valueOf(errorDetail.getStatusCode()))
                .body(errorResponse);
    }

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> baseExceptionHandle(
            BaseException e, HttpServletRequest req) {
        BaseErrorCode code = e.getErrorCode();
        ErrorDetail errorDetail = code.getErrorDetail();
        ErrorResponse errorResponse = new ErrorResponse(errorDetail);
        return ResponseEntity.status(HttpStatus.valueOf(errorDetail.getStatusCode()))
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> ArgumentNotValidHandle(
            MethodArgumentNotValidException exception, HttpServletRequest req) {
        ErrorDetail reason =
                ErrorDetail.builder()
                        .statusCode(METHOD_ARGUMENT_ERROR.getStatusCode())
                        .errorCode(METHOD_ARGUMENT_ERROR.getErrorCode())
                        .reason(
                                exception
                                        .getBindingResult()
                                        .getAllErrors()
                                        .get(0)
                                        .getDefaultMessage())
                        .build();
        ErrorResponse errorResponse = new ErrorResponse(reason);
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }
}
