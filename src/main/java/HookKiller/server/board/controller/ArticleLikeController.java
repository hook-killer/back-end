package HookKiller.server.board.controller;

import HookKiller.server.board.service.ArticleLikeService;
import HookKiller.server.common.dto.CommonBooleanResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/article/like")
@RequiredArgsConstructor
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;

    /**
     * 요청을 보낸 사용자가 articleId에 대해서 좋아요를 눌렀는지 확인하기 위한 EndPoint<br />
     * 만약 로그인하지 않은 사용자(GUEST)가 요청을 보낸 경우에는 `SecurityContextNotFoundException`가 발생한다.<br />
     * 로그인을 하였으나, 해당 사용자가 존재하지 않는 경우에는 `UserNotFoundException`가 발생한다.<br />
     *
     * @param articleId
     * @return
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<CommonBooleanResultResponse> isUserLikeYn(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleLikeService.isUserArticleLike(articleId));
    }

    /**
     * 게시물의 좋아요 또는 삭제를 담당한다.<br />
     * 게시물을 좋아요를 누르지 않은 경우 요청이 들어오면, 좋아요 내역을 추가한다.<br />
     * 게시물의 좋아요를 눌렀던 경우 요청이 들어오면, 좋아요 내역을 삭제한다.
     *
     * @param articleId
     * @return
     */
    @PostMapping("/{articleId}")
    public ResponseEntity<String> articleLikeProcess(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleLikeService.articleLikeProcess(articleId));
    }

}
