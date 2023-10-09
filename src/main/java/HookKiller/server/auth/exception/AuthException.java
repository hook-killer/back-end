package HookKiller.server.auth.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum AuthException implements BaseErrorCode {
  ALREADY_EXIST_USER_ERROR(BAD_REQUEST.value(), "User_400_1", "이미 회원인 유저입니다."),
  NOT_NEIGHBOR_ERROR(FORBIDDEN.value(), "Neighbor_403_1", "해당 유저와 친구가 아닙니다."),
  USER_NOT_FOUND_ERROR(NOT_FOUND.value(), "User_404_1", "유저를 찾을 수 없습니다."),
  USER_FAVOR_NOT_FOUND_ERROR(NOT_FOUND.value(), "UserFavor_404_1", "유저 취향 정보를 찾을 수 없습니다."),
  USER_TAG_NOT_FOUND_ERROR(NOT_FOUND.value(), "UserTag_404_1", "유저 태그 정보를 찾을 수 없습니다."),
  NEIGHBOR_NOT_FOUND_ERROR(NOT_FOUND.value(), "Neighbor_404_1", "친구 정보를 찾을 수 없습니다."),
  ALREADY_REGISTER_USER_ID_ERROR(BAD_REQUEST.value(), "User_400_2", "이미 등록된 유저 아이디입니다."),
  ON_BOARDING_STATE_NOT_FOUND_ERROR(NOT_FOUND.value(), "OnBoardingState_404_1", "온보딩 상태 값을 찾을 수 없습니다."),
  GENDER_NOT_FOUND_ERROR(NOT_FOUND.value(), "Gender_404_1", "성별 정보를 찾을 수 없습니다."),
  ON_BOARDING_STATUS_FILTER_ERROR(
          BAD_REQUEST.value(), "UserOnBoardingStatus_400_1", ", ' '를 제외한 검색어를 입력해주세요."),
  ALREADY_EXIST_NEIGHBOR_RELATIONSHIP_ERROR(BAD_REQUEST.value(), "Neighbor_400_1", "이미 친구인 회원입니다."),
  SELF_NEIGHBOR_ERROR(BAD_REQUEST.value(), "Neighbor_400_2", "스스로에게 친구 신청 할 수 없습니다."),
  ;
  
  private final Integer statusCode;
  private final String errorCode;
  private final String reason;
  
  @Override
  public ErrorDetail getErrorDetail() {
    return ErrorDetail.of(statusCode, errorCode, reason);
  }
}