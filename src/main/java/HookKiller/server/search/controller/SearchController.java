package HookKiller.server.search.controller;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.search.dto.KeyDownArticleDto;
import HookKiller.server.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
  private final SearchService searchService;

  @GetMapping("/keydown/{word}")
  public List<KeyDownArticleDto> searchKeyDown(@PathVariable String word) {
    return searchService.getKeyDownArticles(word, PageRequest.of(0, 7));
  }
  
  @GetMapping("/{word}")
  public List<ArticleRequestDto> searchArticles(
          @PathVariable String word,
          @RequestParam int offset,
          @RequestParam int limit) {
    return searchService.getSearchResult(word, PageRequest.of(offset, limit));
  }
}
