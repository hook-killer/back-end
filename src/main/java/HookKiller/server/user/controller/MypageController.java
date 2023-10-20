package HookKiller.server.user.controller;

import HookKiller.server.common.dto.CommonBooleanResultResponse;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.dto.MyPageUserResponse;
import HookKiller.server.user.dto.MyPageUserUpdateRequest;
import HookKiller.server.user.service.MyPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MyPageService myPageService;

    /**
     * 마이페이지 조회
     * 애초애 Security에서 사용자 정보없으면 Exception발생하기 떄문에 Paramete가 필요가 없다.
     * @return
     */
    @GetMapping
    public ResponseEntity<MyPageUserResponse> getMyPage() {
        return ResponseEntity.ok(myPageService.getMyPage());

    }

    /**
     * 정보 수정, Null로 준 경우 해당 파라미터는 변경하지 않는다.
     * @param requestDto
     */
    @PutMapping
    public ResponseEntity<CommonBooleanResultResponse> updateUserInfo(@RequestBody MyPageUserUpdateRequest requestDto) {
        myPageService.updateUserInfo(requestDto);
        return ResponseEntity.ok(
                CommonBooleanResultResponse.builder().result(true).message("수정이 완료되었습니다.").build()
        );
    }

    /**
     * Thumnail Path변경하고자 하는 경우
     * @return
     */
    @PutMapping("/thumnail")
    public ResponseEntity<CommonBooleanResultResponse> updateUserThumnailPath(@RequestBody MyPageUserUpdateRequest request) {
        return ResponseEntity.ok(myPageService.updateUserThumbnailPath(request));
    }

    /**
     * 마이페이지 내가쓴 댓글, 게시물, 좋아요한 게시물의 리스트를 획득 할 수 있는 API
     * @param searchType 대소문자 구분없이 "reply", "article", "like"
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @GetMapping("/mylist/{searchType}")
    public ResponseEntity<Object> getList(
            @PathVariable String searchType,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(myPageService.getMyCreatedList(page, limit, searchType, LanguageType.findTypeByRequest(request)));
    }

}
