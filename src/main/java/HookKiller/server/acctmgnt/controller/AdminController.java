package HookKiller.server.acctmgnt.controller;

import HookKiller.server.acctmgnt.exception.UserNotAdminException;
import HookKiller.server.acctmgnt.service.AdminService;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.UserAccountNotActiveException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.service.UserService;
import HookKiller.server.user.type.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static HookKiller.server.user.type.UserRole.ADMIN;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserUtils userUtils;
    private final AdminService adminService;
    private final UserRepository userRepository;

    /**
     * 권한 검증
     */
    private void isAdmin(User loginUser) {
        loginUser = userUtils.getUser();
        if (!loginUser.getRole().equals(ADMIN)) {
            throw UserNotAdminException.EXCEPTION;
        }
        if (!loginUser.getStatus().equals(Status.ACTIVE)) {
            throw UserAccountNotActiveException.EXCEPTION;
        }
    }

    /**
     * 관리자 계정 등록
     */
    @PostMapping
    public void regAdmin() {

    }

    /**
     * 관리자 계정 리스트
     */
    @GetMapping
    public void adminList() {

    }

    /**
     * 관리자 계정 삭제
     */
    @DeleteMapping
    public void deleteAdmin() {

    }

    /**
     * 사용자 계정 등록
     */
    @PostMapping("/user/register")
    public ResponseEntity<AuthResponse> regUser(@RequestBody @Valid RegisterRequest request, UserService userService) {
        return userService.registerUser(request);
    }

    /**
     * 사용자 계정 리스트
     */
    @GetMapping
    public void userList() {

    }

    /**
     * 사용자 계정 삭제
     */
    @DeleteMapping
    public void deleteUser() {

    }
}
