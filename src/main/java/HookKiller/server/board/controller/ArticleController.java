package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("{/boardId}")
  public List<ArticleDto> getArticleList(@PathVariable Long boardId) {
    String language = "KOR";
    return articleService.getArticleList(boardId, language);
  }
}
