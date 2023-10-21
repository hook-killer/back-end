package HookKiller.server.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthResponse {

    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String nickName;
    private String role;
    private String email;
    private String thumbnail;
}
