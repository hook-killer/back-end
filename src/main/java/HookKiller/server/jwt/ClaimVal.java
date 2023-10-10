package HookKiller.server.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClaimVal {
  TOKEN_ID("userID"),
  TOKEN_EMAIL("email"),
  TOKEN_PASSWORD("password"),
  TOKEN_ROLE("role"),
  TOKEN_NICKNAME("nickname"),
  ;
  
  private final String value;
}