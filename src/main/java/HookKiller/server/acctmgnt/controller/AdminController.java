package HookKiller.server.acctmgnt.controller;

import HookKiller.server.acctmgnt.exception.UserNotAdminException;
import HookKiller.server.acctmgnt.service.AdminService;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private void isAdmin(UserRole userRole) {
        User loginUser = userUtils.getUser();
        if (loginUser == null || !loginUser.getRole().equals(ADMIN)) {
            throw UserNotAdminException.EXCEPTION;
        }
    }
}
