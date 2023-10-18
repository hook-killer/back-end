package HookKiller.server.admin.controller;

import HookKiller.server.admin.dto.AccountSearchResponse;
import HookKiller.server.admin.dto.AccountStatusRequestDto;
import HookKiller.server.admin.service.AdminService;
import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.ReplyResponseDto;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.dto.UserDto;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * 관리자 계정 등록
     */
    @PostMapping("/register")
    public void registerAdminAccount(@RequestBody @Valid SingUpRequest registerRequest) {
        log.info("관리자 계정 등록 >>> {}", registerRequest);
        adminService.regAdmin(registerRequest);
    }

    /**
     * 계정 리스트 조회
     */
    @GetMapping("/account/list/{role}")
    public ResponseEntity<List<UserDto>> accountList(
            @PathVariable UserRole role,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(defaultValue = "ACTIVE", required = false) Status userStatus
    ) {
        return ResponseEntity.ok(adminService.acctListByRole(page, limit, userStatus, role));
    }

    @GetMapping("/account/{userId}")
    public ResponseEntity<AccountSearchResponse> getAccountInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getAccountDetails(userId));
    }

    /**
     * 계정 상태 변경
     */
    @PutMapping("/account/status")
    public void modifyAccountStatus(@RequestBody @Valid AccountStatusRequestDto request) {
        log.info("계정 ID {} >>> {}", request.getUserId(), request.getAccountStatus());
        adminService.modifyAcctStat(request.getUserId(), request.getAccountStatus());
    }

    /**
     * 게시물의 상태와 관련없이 해당 사용자가 작성한 게시물을 모두 List로 반환한다.
     *
     * @param userId     조회를 희망하는 사용자 ID
     * @param page       PageNumber
     * @param limit      검색 희망 갯수
     * @param request
     * @return
     */
    @GetMapping("/account/article/{userId}")
    public ResponseEntity<List<ArticleRequestDto>> createArticleListByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(
                adminService.getArticleListByUserId(
                        page, limit, LanguageType.findTypeByRequest(request), userId
                )
        );
    }


    /**
     * 댓 상태와 관련없이 해당 사용자가 작성한 게시물을 모두 List로 반환한다.
     *
     * @param userId     조회를 희망하는 사용자 ID
     * @param page       PageNumber
     * @param limit      검색 희망 갯수
     * @param request
     * @return
     */
    @GetMapping("/account/reply/{userId}")
    public ResponseEntity<List<ReplyResponseDto>> createReplyByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(
                adminService.getReplyListByUserId(
                        page, limit, LanguageType.findTypeByRequest(request), userId
                )
        );
    }
}
