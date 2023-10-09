package HookKiller.server.auth.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRequest {
  
  private String email;
  private String password;
}
