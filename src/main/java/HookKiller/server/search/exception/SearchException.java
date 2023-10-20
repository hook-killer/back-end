package HookKiller.server.search.exception;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum SearchException implements BaseErrorCode {
  
  SEARCH_ARTICLE_NOT_FOUND_ERROR(NOT_FOUND.value(), "404_1", "해당 조건의 게시글이 없습니다."),
  SEARCH_ARTICLE_CONTENT_NOT_FOUND_ERROR(NOT_FOUND.value(), "404_2", "해당 조건의 게시글 내용이 없습니다"),
  ;
  
  private final Integer statusCode;
  private final String errorCode;
  private final String reason;
  
  @Override
  public ErrorDetail getErrorDetail() {
    return ErrorDetail.of(statusCode, errorCode, reason);
  }
}
