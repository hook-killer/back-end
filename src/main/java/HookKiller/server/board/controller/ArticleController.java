package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.service.ArticleService;
import HookKiller.server.board.type.BoardType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  /**
   * 게시글 조회
   */

  @GetMapping("/{boardId}")
  public List<ArticleRequestDto> getArticleList(@PathVariable Long boardId, HttpServletRequest request) {
    BoardType language = BoardType.valueOf(request.getHeader("language"));
    return articleService.getArticleList(boardId, language);
  }

  /**
   * 게시글 등록
   */
  @PostMapping
  public ArticleRequestDto createArticle(@RequestBody PostArticleRequestDto articleDto) {
    return articleService.createArticle(articleDto);
  }

}
