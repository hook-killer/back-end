package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleDto;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.service.ArticleService;
import HookKiller.server.board.type.BoardType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;


  @GetMapping("/{boardId}")
  public List<ArticleDto> getArticleList(@PathVariable Long boardId, HttpServletRequest request) {
    BoardType language = BoardType.valueOf(request.getHeader("language"));
    return articleService.getArticleList(boardId, language);
  }


}
