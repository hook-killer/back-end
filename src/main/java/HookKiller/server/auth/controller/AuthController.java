package HookKiller.server.auth.controller;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.service.AuthService;
import HookKiller.server.common.service.MailHelper;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final MailHelper mailHelper;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid SingUpRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyMail(@RequestParam String verificationToken) {
        mailHelper.verifyEmail(verificationToken);
        return ResponseEntity.ok("이메일 인증 완료");
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest body) {
        return authService.loginExecute(body);
    }
    
    @PostMapping("/logout")
    public boolean logout() {
        return true;
    }

    @GetMapping("/oauth/kakao")
    public OAuthResponse registerUserForKakao(@RequestParam String code) {
        log.info("code >>> {}", code);
        return authService.registerUserKakaoCode(code);
    }

    @GetMapping("/oauth/google")
    public OAuthResponse registerUserForGoogle(@RequestParam String code) {
        return authService.registerGoogleUser(code);
    }

}
