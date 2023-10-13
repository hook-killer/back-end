package HookKiller.server.board.exception;


import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum BoardException implements BaseErrorCode {

  BOARD_NOT_FOUND_ERROR(NOT_FOUND.value(), "Board_404_1", "해당 게시판을 찾을 수 없습니다."),
  ARTICLE_CONTENT_NOT_FOUND_ERROR(NOT_FOUND.value(), "ArticleContent_404_1", "해당 게시글을 찾을 수 없습니다.");

  private final Integer statusCode;
  private final String errorCode;
  private final String reason;

  @Override
  public ErrorDetail getErrorDetail() {
    return ErrorDetail.of(statusCode, errorCode, reason);
  }
}
