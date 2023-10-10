package HookKiller.server.common.controller;

import HookKiller.server.common.util.SendMailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
@RestController
public class MailController {
    private final SendMailUtil sendMailUtil;

    /**
     * @param mail 메일을 파라미터로 필요 id@mailHost
     * @return
     */
    @GetMapping("/test/{mail}")
    public void testMail(@PathVariable String mail) {
        sendMailUtil.sendMail(
                mail,
                "테스트메일입니다.",
                "mail/sample",
                new HashMap<>() {{
                    put("a", "A변수 데이터 입니다.");
                    put("b", "B변수 데이터 입니다.");
                }});
    }
}
