package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.service.ArticleService;
import HookKiller.server.common.type.LanguageType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  /**
   * 리스트 조회 조회
   */
  @GetMapping("/{boardId}")
  public List<ArticleRequestDto> getArticleList(
          @RequestParam(defaultValue = "0", required = false) int page,
          @RequestParam(defaultValue = "10", required = false) int articleLimit,
          @PathVariable Long boardId,
          HttpServletRequest request) {
    return articleService.getArticleList(page, articleLimit, boardId, LanguageType.findTypeByRequest(request));
  }

  /**
   * 단건 조회
   */
  @GetMapping("/{articleId}")
  public ArticleRequestDto getArticle(@PathVariable Long articleId, HttpServletRequest request) {
    return articleService.getArticleByArticleId(articleId, LanguageType.findTypeByRequest(request));
  }

  /**
   * 게시글 등록
   */
  @PostMapping
  public void createArticle(@RequestBody @Valid PostArticleRequestDto requestDto, Board board) {
    articleService.createArticle(requestDto, board);
  }

  /**
   * 게시물 수정
   */
  @PutMapping
  public void updateArticle(@RequestBody @Valid  PostArticleRequestDto requestDto) {
    articleService.updateArticle(requestDto);
  }


  /**
   * 게시물 삭제
   */
  @DeleteMapping("/{articleId}")
  public void deleteArticle(@PathVariable ArticleRequestDto requestDto) {
    articleService.deleteArticle(requestDto);
  }

}
