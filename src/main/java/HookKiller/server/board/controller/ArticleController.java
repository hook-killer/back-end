package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.service.ArticleContentService;
import HookKiller.server.board.service.ArticleService;
import HookKiller.server.common.type.LanguageType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;
  private final ArticleContentService articleContentService;

  /**
   * 게시글 조회
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
  public ResponseEntity<String> createArticle(@RequestBody PostArticleRequestDto requestDto) {
    articleContentService.createContent(
            requestDto, articleService.createArticle(requestDto)
    );
    return ResponseEntity.ok("Article Create Success");
  }

  /**
   * 게시물 수정
   */
  @PutMapping
  public ResponseEntity<String> updateArticle(@RequestBody PostArticleRequestDto requestDto) {
    articleContentService.updateContent(requestDto, articleService.updateArticle(requestDto));
    return ResponseEntity.ok("Article Create Success");
  }


  /**
   * 게시물 삭제
   */
  @DeleteMapping("/{articleId}")
  public ResponseEntity<String> deleteArticle(@PathVariable Long articleId) {
    articleService.deleteArticle(articleId);
    return ResponseEntity.ok("삭제처리가 완료되었습니다.");
  }

}
