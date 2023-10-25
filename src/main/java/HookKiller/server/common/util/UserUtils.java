package HookKiller.server.common.util;


import HookKiller.server.admin.exception.UserNotAdminException;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.exception.SecurityContextNotFoundException;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.UserAccountNotActiveException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static HookKiller.server.user.type.UserRole.ADMIN;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserUtils {

    private final UserRepository userRepository;

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.error("들어와요? >>> {}",authentication);
        if (authentication == null) {
            throw SecurityContextNotFoundException.EXCEPTION;
        }
        return Long.valueOf(authentication.getName());
    }
    public User getUser() {
        return userRepository.findById(getCurrentUserId()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

    public void verificationRequestUserAdmin() {
        this.verificationRequestUserAdminAndGetUser();
    }

    /**
     * 사용자 정보에 대해 요청을 보낸 사용자가 `관리자` 인지를 확인하며, `관리자`인 경우 User정보를 반환한다.
     * @return
     */
    public User verificationRequestUserAdminAndGetUser() {
        User user = this.getUser();
        if (!user.getRole().equals(ADMIN)) {
            throw UserNotAdminException.EXCEPTION;
        }
        return user;
    }
    /**
     * 사용자 정보에 대한 반환은 하지 않으며, 현재 요청을 보낸 사용자가 `관리자`인지를 확인한다. <br />
     * 요청자가 ACTIVE 상태의 사용자가 아닌 경우 `UserNotActiveException`이 발생하며, <br />
     * 요청자가 ACTIVE 상태인 사용자인 경우에는 User객체를 반환한다.<br />
     * @return
     */
    public User getUserIsStatusActive() {
        User user = this.getUser();
        if(user.getStatus().equals(Status.ACTIVE)) {
            return user;
        }
        throw UserAccountNotActiveException.EXCEPTION;
    }
}
