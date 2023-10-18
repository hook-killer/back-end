package HookKiller.server.common.service;

import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.dto.MailRequest;
import HookKiller.server.common.util.EmailVerificationUtil;
import HookKiller.server.common.util.SendMailUtil;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailHelper {

    private final SendMailUtil sendMailUtil;
    private final UserRepository userRepository;
    private final EmailVerificationUtil emailVerificationUtil;

    private ResponseEntity<MailRequest> sendVerificationMail(MailRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        sendMailUtil.sendMail(
                request.getEmail(),
                "이메일 인증 링크를 클릭하여 이메일 인증을 완료하세요",
                "mail/verificationEmail",
                Map.of("verificationLink", emailVerificationUtil.generateVerificationLink(user))
        );

        MailRequest res = MailRequest.builder()
                .email(request.getEmail())
                .build();

        return ResponseEntity.ok(res);
    }

    public ResponseEntity<MailRequest> sendMail(MailRequest request) {
        return sendVerificationMail(request);
    }
}
