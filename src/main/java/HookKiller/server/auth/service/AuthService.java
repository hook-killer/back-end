package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  
  public ResponseEntity<AuthResponse> login(AuthRequest body) {
    AuthResponse res = AuthResponse.builder()
            .token(jwtTokenProvider.generateToken(body.getEmail(), body.getPassword(),"USER"))
            .build();
    return ResponseEntity.ok(res);
  }
}
