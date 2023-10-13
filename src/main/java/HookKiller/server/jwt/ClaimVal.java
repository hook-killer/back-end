package HookKiller.server.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClaimVal {
  TOKEN_ID("userID"),
  TOKEN_EMAIL("email"),
  TOKEN_ROLE("role"),
  ACCESS_TOKEN("ACCESS_TOKEN"),
  REFRESH_TOKEN("REFRESH_TOKEN"),
  TYPE("type"),
  ;
  
  private final String value;
}
