package HookKiller.server.common.util;


import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.exception.SecurityContextNotFoundException;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.UserNotActiveException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserUtils {

    private final UserRepository userRepository;

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw SecurityContextNotFoundException.EXCEPTION;
        }
        log.info("principal : {}", authentication.getPrincipal().toString());
        log.info("authentication.details : {}", authentication.getDetails().toString());
        return Long.valueOf(authentication.getName());
    }
    public User getUser() {
        return userRepository.findById(getCurrentUserId()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

    /**
     * 요청자가 ACTIVE 상태의 사용자가 아닌 경우 `UserNotActiveException`이 발생하며,
     * 요청자가 ACTIVE 상태인 사용자인 경우에는 User객체를 반환한다.
     * @return
     */
    public User getUserIsStatusActive() {
        User user = this.getUser();
        if(user.getStatus().equals(Status.ACTIVE)) {
            return user;
        }
        throw UserNotActiveException.EXCEPTION;
    }

}
