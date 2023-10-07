package HookKiller.server.board.controller;

import HookKiller.server.board.dto.ArticleDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("/{boardId}")
  public List<ArticleDto> getArticleList(@PathVariable Long boardId) {
    // TODO: 추후 RequestHeader에서 User가 선택한 언어를 뽑아오는 작업으로 수정할 예정
    String language = "KOR";
    List<ArticleDto> dto = articleService.getArticleList(boardId, language);
    return dto;
  }

}
