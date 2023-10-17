package HookKiller.server.search.service;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.search.dto.KeyDownArticleDto;
import HookKiller.server.search.exception.SearchArticleContentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
  
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;
  
  public List<KeyDownArticleDto> getKeyDownArticles(String word, PageRequest pageRequest) {
    List<Article> articleList = articleContentRepository.findDistinctByTitleContainsOrContentContains(word, word, pageRequest).stream()
            .map(articleContent -> articleRepository.findByArticleContentExistsOrderByUpdateAt(articleContent).orElseThrow(() -> SearchArticleContentNotFoundException.EXCEPTION))
            .toList();
    
    return articleContentRepository.findDistinctByTitleContainsOrContentContains(word, word, pageRequest).stream()
            .map(articleContent -> articleRepository.findByArticleContentExistsOrderByUpdateAt(articleContent).orElseThrow(() -> SearchArticleContentNotFoundException.EXCEPTION))
            .toList()
            .stream()
            .map(article ->
                    KeyDownArticleDto.of(article,
                            articleContentRepository.findByArticleAndContentContains(article, word).orElseThrow(() -> SearchArticleContentNotFoundException.EXCEPTION)))
            .toList();
  }
  
  public List<ArticleRequestDto> getSearchResult(String word, PageRequest pageRequest) {
    List<Article> articleList = articleContentRepository.findDistinctByTitleContainsOrContentContains(word, word, pageRequest).stream()
            .map(articleContent -> articleRepository.findByArticleContentExistsOrderByUpdateAt(articleContent).orElseThrow(() -> SearchArticleContentNotFoundException.EXCEPTION))
            .toList();
    
    return articleList.stream()
            .map(article ->
              ArticleRequestDto.of(article,
                      articleContentRepository.findByArticleAndContentContains(article, word)
                              .orElseThrow(() -> SearchArticleContentNotFoundException.EXCEPTION)))
            .toList();
  }
}

