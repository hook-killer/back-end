package HookKiller.server.board.exception;

import HookKiller.server.common.exception.BaseException;

public class ArticleUpdateUnauthorizedUserException extends BaseException {

  public static final BaseException EXCEPTION = new ArticleUpdateUnauthorizedUserException();

  private ArticleUpdateUnauthorizedUserException() {
    super(BoardException.ARTICLE_UPDATE_UNAUTHORIZED_USER_ERROR);
  }

}
