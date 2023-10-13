package HookKiller.server.acctmgnt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDto {
    private String nickName;
    private String email;
    private String password;
}
