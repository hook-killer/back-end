package HookKiller.server.admin.controller;

import HookKiller.server.admin.service.AdminService;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.user.dto.UserDto;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void registerAdminAccount(@RequestBody @Valid RegisterRequest registerRequest) {
        log.info("관리자 계정 등록 >>> {}", registerRequest);
        adminService.regAdmin(registerRequest);
    }

    /**
     * 계정 리스트 조회
     * TODO : 페이지네이션
     */
    @GetMapping("/list/{role}")
    public List<UserDto> accountList(@PathVariable UserRole role) {
        log.info("계정 리스트 >>> {}", role);
        return adminService.acctListByRole(role);
    }

    /**
     * 계정 상태 변경
     */
    @PostMapping("/{userId}/{accountStatus}")
    public void modifyAccountStatus(@PathVariable Long userId, @PathVariable Status accountStatus) {
        log.info("계정 ID {} >>> {}", userId, accountStatus);
        adminService.modifyAcctStat(userId, accountStatus);
    }

}
