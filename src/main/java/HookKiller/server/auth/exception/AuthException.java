package HookKiller.server.auth.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum AuthException implements BaseErrorCode {
  TOKEN_ERROR(UNAUTHORIZED.value(), "Token_403_1", "토큰 관련 에러입니다. 에러에 대한 구체적인 정의가 필요합니다."),
  INVALID_TOKEN_ERROR(UNAUTHORIZED.value(), "Token_403_2", "토큰이 만료되었거나 형식에 맞지않은 토큰입니다."),
  TOKEN_NOT_FOUND_ERROR(NOT_FOUND.value(), "Token_404_1", "토큰을 찾을 수 없습니다."),
  USER_NOT_FOUND_ERROR(NOT_FOUND.value(), "User_404_1", "유저를 찾을 수 없습니다."),
  PASSWORD_INCORRECT_ERROR(NOT_FOUND.value(), "User_404_2", "비밀번호가 다릅니다 다시 입력해 주세요."),
  STATUS_NOT_VERIFICATION_ERROR(UNAUTHORIZED.value(), "User_404_3", "이메일 인증을 완료해 주세요.")
  ;
  
  private final Integer statusCode;
  private final String errorCode;
  private final String reason;
  
  @Override
  public ErrorDetail getErrorDetail() {
    return ErrorDetail.of(statusCode, errorCode, reason);
  }
}
