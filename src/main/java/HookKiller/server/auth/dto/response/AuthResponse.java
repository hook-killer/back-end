package HookKiller.server.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
  
  private final String token;
  private final String nickName;
  private final String role;
  private final String thumbNail;
}
