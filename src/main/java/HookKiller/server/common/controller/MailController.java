package HookKiller.server.common.controller;

import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.util.SendMailUtil;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
@RestController
public class MailController {
    private final SendMailUtil sendMailUtil;
    private final UserRepository userRepository;

    /**
     * @param email 메일을 파라미터로 필요 id@mailHost
     * @return
     */
//    @GetMapping("/test/{mail}")
//    public void testMail(@PathVariable String mail) {
//        sendMailUtil.sendMail(
//                mail,
//                "테스트메일입니다.",
//                "mail/sample",
//                new HashMap<>() {{
//                    put("a", "A변수 데이터 입니다.");
//                    put("b", "B변수 데이터 입니다.");
//                }});
//    }

    @GetMapping("verify/{email}")
    public void sendVerificationMail(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        sendMailUtil.sendMail(
                email,
                "이메일 인증 링크를 클릭하여 이메일 인증을 완료하세요",
                "mail/sample",
                Map.of("verificationLink", sendMailUtil.generateVerificationLink(user))
        );
    }
}
