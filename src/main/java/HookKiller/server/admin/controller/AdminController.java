package HookKiller.server.admin.controller;

import HookKiller.server.admin.service.AdminService;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.UserDto;
import HookKiller.server.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 관리자 계정 리스트
     */
    @GetMapping("/list")
    public List<UserDto> adminAccountList() {
        log.info("관리자 계정 리스트");
        return adminService.adminList();
    }

    /**
     * 사용자 계정 리스트
     */
    @GetMapping
    public List<UserDto> userAccountList() {
        log.info("사용자 계정 리스트");
        return adminService.userList();
    }

    /**
     * 관리자 계정 상태 변경
     */
//    @DeleteMapping
//    public void deleteAdminAccount() {
//
//    }

    /**
     * 사용자 계정 상태 변경
     */
//    @DeleteMapping
//    public void deleteUser() {
//
//    }
}
