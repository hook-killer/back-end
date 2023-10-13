package HookKiller.server.board.exception;

import HookKiller.server.common.exception.BaseException;

public class ArticleContentNotFoundException extends BaseException {

  public static final BaseException EXCEPTION = new ArticleContentNotFoundException();

  private ArticleContentNotFoundException() {
    super(BoardException.ARTICLE_CONTENT_NOT_FOUND_ERROR);
  }

}
