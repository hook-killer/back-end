package HookKiller.server.auth.controller;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.service.AuthService;
import HookKiller.server.auth.service.CustomUserDetailsService;
import HookKiller.server.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginExecute(@RequestBody @Valid AuthRequest body) {
        return authService.login(body);
    }
    
}
