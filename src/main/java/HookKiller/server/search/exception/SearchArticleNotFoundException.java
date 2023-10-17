package HookKiller.server.search.exception;

import HookKiller.server.board.exception.ArticleDeleteUnauthorizedUserException;
import HookKiller.server.board.exception.BoardException;
import HookKiller.server.common.exception.BaseException;

public class SearchArticleNotFoundException extends BaseException {
  
  public static final BaseException EXCEPTION = new SearchArticleNotFoundException();
  
  private SearchArticleNotFoundException() {
    super(SearchException.SEARCH_ARTICLE_NOT_FOUND_ERROR);
  }
  
}

