package HookKiller.server.common.util;


import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.exception.SecurityContextNotFoundException;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


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
        return userRepository.findById(1L).orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }

}
