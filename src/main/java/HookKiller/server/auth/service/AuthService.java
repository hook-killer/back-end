package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  
  public ResponseEntity<AuthResponse> login(AuthRequest body) {
    User user  = userRepository.findByEmailAndPassword(body.getEmail(), body.getPassword()).orElseThrow(()-> UserNotFoundException.EXCEPTION );
    AuthResponse res = AuthResponse.builder()
            .token(jwtTokenProvider.generateToken(user.getId(), user.getEmail(),user.getNickName(), user.getRole()))
            .build();
    return ResponseEntity.ok(res);
  }
}
