package HookKiller.server.user.controller;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.service.ArticleContentService;
import HookKiller.server.user.dto.MyPageRequestDto;
import HookKiller.server.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MyPageService myPageService;
    private final ArticleContentService articleContentService;

    /**
     * 마이페이지 조회
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public MyPageRequestDto getMyPage(Long userId) {
        return myPageService.getMyPage(userId);
    }

    /**
     * 정보 수정
     * @param requestDto
     */
    @PutMapping
    public void updatePasswordAndNickname(@RequestBody MyPageRequestDto requestDto) {
        myPageService.updatePasswordAndNickname(requestDto);
    }

    /**
     * 마이페이지 내가쓴 글
     *
     */
    @GetMapping("/{myList}")
    public List<ArticleRequestDto> getMyList(Long userId) {
        return myPageService.getMyList(userId);
    }

}
