package HookKiller.server.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyPageUserUpdateRequest {

    private Long userId;
    private String password;
    private String thumbnail;
    private String nickName;
}
