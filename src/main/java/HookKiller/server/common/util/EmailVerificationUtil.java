    package HookKiller.server.common.util;

    import HookKiller.server.properties.HookMailProperties;
    import HookKiller.server.user.entity.User;
    import HookKiller.server.user.repository.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Component;

    import static HookKiller.server.common.util.TokenGenerator.generateUniqueToken;

    @Component
    @RequiredArgsConstructor
    public class EmailVerificationUtil {

        private final UserRepository userRepository;
        private final HookMailProperties hookMailProperties;


        public String generateVerificationLink(User user) {
            String verificationToken = generateUniqueToken();

            user.activeStatus();
            userRepository.save(user);

            return String.format("http://%s/mail/verify?token=" + verificationToken, hookMailProperties.getRegisterDomain());
        }
    }
