package HookKiller.server.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MailRequest {

    private String email;
    private String verificationToken;
}
