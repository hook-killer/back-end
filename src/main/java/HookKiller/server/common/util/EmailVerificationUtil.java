    package HookKiller.server.common.util;

    import HookKiller.server.properties.HookMailProperties;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    public class EmailVerificationUtil {

        private final HookMailProperties hookMailProperties;

        public String generateVerificationLink(String verificationToken) {

            return String.format("http://%s/verifyEmail?verificationToken=" + verificationToken, hookMailProperties.getRegisterDomain());
        }

    }
