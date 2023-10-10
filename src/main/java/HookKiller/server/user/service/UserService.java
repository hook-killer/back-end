package HookKiller.server.user.service;

import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    
    @Transactional
    public ResponseEntity<AuthResponse> registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw AlreadyExistUserException.EXCEPTION;
        }
        
        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickName(request.getNickName())
                .thumbnail(request.getThumbnail())
                .build());
        
        AuthResponse res = AuthResponse.builder()
                .token(jwtTokenProvider.generateToken(user.getId(), user.getEmail(),user.getNickName(), user.getRole()))
                .build();
        
        return ResponseEntity.ok(res);
    }
}
