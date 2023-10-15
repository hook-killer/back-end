package HookKiller.server.common.util;


import HookKiller.server.admin.exception.UserNotAdminException;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.exception.SecurityContextNotFoundException;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static HookKiller.server.user.type.UserRole.ADMIN;


@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UserRepository userRepository;

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw SecurityContextNotFoundException.EXCEPTION;
        }
        return Long.valueOf(authentication.getName());
    }

    public User getUser() {
        return userRepository.findById(getCurrentUserId()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

    /**
     * 사용자 정보에 대한 반환은 하지 않으며, 현재 요청을 보낸 사용자가 `관리자`인지를 확인한다.
     */
    public void verificationiRequestUserAdmin() {
        this.verificationRequestUserAdminAndGetUser();
    }

    /**
     * 사용자 정보에 대해 요청을 보낸 사용자가 `관리자` 인지를 확인하며, `관리자`인 경우 User정보를 반환한다.
     * @return
     */
    public User verificationRequestUserAdminAndGetUser() {
        User user = this.getUser();
        if (user.getRole().equals(ADMIN)) {
            throw UserNotAdminException.EXCEPTION;
        }
        return user;
    }
}
