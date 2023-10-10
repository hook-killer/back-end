package HookKiller.server.auth.controller;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.service.AuthService;
import HookKiller.server.auth.service.CustomUserDetailsService;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Path;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthService authService;
    private final UserService userService;
    
    @PostMapping("/register/{token}")
    public ResponseEntity<AuthResponse> register(@PathVariable String token, @RequestBody @Valid RegisterRequest request) {
        return userService.registerUser(request);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest body) {
        return authService.loginExecute(body);
    }
    
    @PostMapping("/logout")
    public void logout(){
        // React Cookie 삭제로 구현할 것. 따로 로직은 필요없음
    }
    
}
