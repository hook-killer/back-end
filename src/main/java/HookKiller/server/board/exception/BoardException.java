package HookKiller.server.board.exception;


import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum BoardException implements BaseErrorCode {

  BOARD_NOT_FOUND_ERROR(NOT_FOUND.value(), "Board_404_1", "해당 게시판을 찾을 수 없습니다."),
  ARTICLE_CONTENT_NOT_FOUND_ERROR(NOT_FOUND.value(), "ArticleContent_404_1", "해당 게시글을 찾을 수 없습니다."),
  ARTICLE_UPDATE_UNAUTHORIZED_USER_ERROR(UNAUTHORIZED.value(), "Article_401_1", "게시물 수정에 대한 권한이 없습니다."),
  ARTICLE_DELETE_UNAUTHORIZED_USER_ERROR(UNAUTHORIZED.value(), "Article_401_2", "게시물 삭제에 대한 권한이 없습니다."),
  REPLY_CONTENT_NOT_FOUND_ERROR(NOT_FOUND.value(), "ReplyContent_404_1", "해당 댓글을 찾을 수 없습니다."),
  REPLY_DELETE_UNAUTHORIZED_USER_ERROR(UNAUTHORIZED.value(), "Reply_404_1", "댓글 삭제에 대한 권한이 없습니다."),
  ;

  private final Integer statusCode;
  private final String errorCode;
  private final String reason;

  @Override
  public ErrorDetail getErrorDetail() {
    return ErrorDetail.of(statusCode, errorCode, reason);
  }
}
