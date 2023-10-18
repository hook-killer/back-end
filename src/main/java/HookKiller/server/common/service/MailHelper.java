package HookKiller.server.common.service;

import HookKiller.server.auth.exception.InvalidTokenException;
import HookKiller.server.common.dto.MailRequest;
import HookKiller.server.common.util.EmailVerificationUtil;
import HookKiller.server.common.util.SendMailUtil;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailHelper {

    private final SendMailUtil sendMailUtil;
    private final EmailVerificationUtil emailVerificationUtil;
    private final UserRepository userRepository;

    @Async
    public void sendVerificationMail(MailRequest request) {

        sendMailUtil.sendMail(
                request.getEmail(),
                "이메일 인증 링크를 클릭하여 이메일 인증을 완료하세요",
                "mail/verificationEmail",
                Map.of("verificationLink", emailVerificationUtil.generateVerificationLink(request.getVerificationToken()))
        );
    }

    @Transactional
    public void verifyEmail(String verificationToken) {
        User user = userRepository.findByVerificationToken(verificationToken).orElseThrow(() -> InvalidTokenException.EXCEPTION);

        user.setStatus(Status.ACTIVE);
        user.setVerificationToken(null);

        userRepository.save(user);
    }
}
