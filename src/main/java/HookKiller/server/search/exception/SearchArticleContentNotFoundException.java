package HookKiller.server.search.exception;

import HookKiller.server.common.exception.BaseException;

public class SearchArticleContentNotFoundException extends BaseException {
  
  public static final BaseException EXCEPTION = new SearchArticleContentNotFoundException();
  
  private SearchArticleContentNotFoundException() {
    super(SearchException.SEARCH_ARTICLE_CONTENT_NOT_FOUND_ERROR);
  }
  
}
