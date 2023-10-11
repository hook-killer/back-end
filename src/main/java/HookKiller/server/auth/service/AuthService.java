package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  
  private static final String BEARER = "Bearer ";
  
  public ResponseEntity<AuthResponse> loginExecute(AuthRequest request) {
    User user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(()-> UserNotFoundException.EXCEPTION );
    AuthResponse res = AuthResponse.builder()
            .token(jwtTokenProvider.generateToken(user.getId(), user.getEmail(),user.getNickName(), user.getRole()))
            .build();
    return ResponseEntity.ok(res);
  }
}
