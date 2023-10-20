package HookKiller.server.board.exception;

import HookKiller.server.common.exception.BaseException;

public class ArticleDeleteUnauthorizedUserException extends BaseException {

  public static final BaseException EXCEPTION = new ArticleDeleteUnauthorizedUserException();

  private ArticleDeleteUnauthorizedUserException() {
    super(BoardException.ARTICLE_DELETE_UNAUTHORIZED_USER_ERROR);
  }

}
