package HookKiller.server.search.controller;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.search.dto.SimpleArticleVo;
import HookKiller.server.search.service.SearchService;
import jakarta.servlet.http.HttpServletRequest;
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
  
  @GetMapping("/{word}")
  public List<SimpleArticleVo> searchArticles(
          HttpServletRequest request,
          @PathVariable String word,
          @RequestParam int offset,
          @RequestParam int limit) {
    return searchService.getSearchResult(LanguageType.findTypeByRequest(request), word, PageRequest.of(offset, limit));
  }
  
  @GetMapping("/all/{word}")
  public List<SimpleArticleVo> searchAllArticlesByWord(
          HttpServletRequest request,
          @PathVariable String word) {
    return searchService.getAllSearchResultByWord(LanguageType.findTypeByRequest(request), word);
  }
}
