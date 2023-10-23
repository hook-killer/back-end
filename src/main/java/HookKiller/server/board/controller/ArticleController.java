package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PopularArticleResponse;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.service.ArticleService;
import HookKiller.server.board.type.ArticleConstants;
import HookKiller.server.common.dto.CommonBooleanResultResponse;
import HookKiller.server.common.type.LanguageType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  @GetMapping("/list/{boardId}")
  public ResponseEntity<Object> getArticleList(
          @RequestParam(defaultValue = "0", required = false) int page,
          @RequestParam(defaultValue = "10", required = false) int articleLimit,
          @PathVariable Long boardId,
          HttpServletRequest request) {
    log.info("게시판 리스트 조회");
    return ResponseEntity.ok(articleService.getArticleList(page, articleLimit, boardId, LanguageType.findTypeByRequest(request)));
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
  public ResponseEntity<String> createArticle(@RequestBody @Valid PostArticleRequestDto requestDto) {
    articleService.createArticle(requestDto);
    return ResponseEntity.ok(ArticleConstants.ARTICLE_CREATE_SUCCESS_MSG);
  }

  /**
   * 게시물 수정
   */
  @PutMapping
  public ResponseEntity<String> updateArticle(@RequestBody @Valid  PostArticleRequestDto requestDto) {
    articleService.updateArticle(requestDto);
    return ResponseEntity.ok(ArticleConstants.ARTICLE_UPDATE_SUCCESS_MSG);
  }

  /**
   * 게시물 삭제
   */
  @DeleteMapping("/{articleId}")
  public ResponseEntity<CommonBooleanResultResponse> deleteArticle(@PathVariable Long articleId) {
    return ResponseEntity.ok(articleService.deleteArticle(articleId));
  }

  /**
   * 최근 7일간의 추천이 많았던 게시물에 대해서 확인이 가능하다.
   * @param boardId 조회를 희망하는 BoardId
   * @param page
   * @param limit
   * @param request
   * @return
   */
  @GetMapping("/popular/{boardId}")
  public ResponseEntity<List<PopularArticleResponse>> getPopularArticlesByBoardId(
          @PathVariable Long boardId,
          @RequestParam(defaultValue = "0", required = false) int page,
          @RequestParam(defaultValue = "10", required = false) int limit,
          HttpServletRequest request
  ) {
    return ResponseEntity.ok(
            articleService.getPopularArticlesByBoardId(page, limit, boardId, LanguageType.findTypeByRequest(request))
    );
  }

}
