package HookKiller.server.user.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum UserException implements BaseErrorCode {
  ALREADY_EXIST_USER_ERROR(BAD_REQUEST.value(), "User_400_1", "이미 회원인 유저입니다."),
  USER_NOT_FOUND_ERROR(NOT_FOUND.value(), "User_404_1", "유저를 찾을 수 없습니다."),
  ALREADY_REGISTER_USER_ID_ERROR(BAD_REQUEST.value(), "User_400_2", "이미 등록된 유저 아이디입니다."),
  USER_ACCOUNT_NOT_ACTIVE(UNAUTHORIZED.value(), "User_401_1", "유저의 계정이 활성 상태가 아닙니다."),
  USER_UPDATE_UNAUTHORIZED(UNAUTHORIZED.value(), "User_401_2", "권한이 존재하지 않습니다."),
  ;
  
  private final Integer statusCode;
  private final String errorCode;
  private final String reason;
  
  @Override
  public ErrorDetail getErrorDetail() {
    return ErrorDetail.of(statusCode, errorCode, reason);
  }
}
