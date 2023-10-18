package HookKiller.server.common.controller;

import HookKiller.server.common.dto.MailRequest;
import HookKiller.server.common.service.MailHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
@RestController
public class MailController {

    private final MailHelper mailHelper;

    /**
     * @param email 메일을 파라미터로 필요 id@mailHost
     * @return
     */

    @GetMapping("/verify/{email}")
    public ResponseEntity<MailRequest> sendMail(@PathVariable String email) {
        return this.mailHelper.sendMail(MailRequest.builder().email(email).build());
    }
}
