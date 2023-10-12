package HookKiller.server.auth.controller;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.request.OauthRegisterRequest;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.dto.response.OauthLoginLinkResponse;
import HookKiller.server.auth.dto.response.OauthTokenResponse;
import HookKiller.server.auth.service.AuthService;
import HookKiller.server.auth.service.CustomUserDetailsService;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthService authService;
    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
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

    // kakao Oauth 링크를 프론트에 전달해줌.
    // 전달한 링크에서 클라이언트가 카카오로그인 요청
    @GetMapping("/oauth/kakao/link")
    public OauthLoginLinkResponse getKakaoLoginLink(
            @RequestHeader(required = false) String referer,
            @RequestHeader(required = false) String host) {

        if (referer.contains(host)) {
            log.info("/oauth/kakao" + host);
            String format = String.format("https://%s/", host);
            return authService.getKakaoOauthLink(format);
        }
        return authService.getKakaoOauthLink(referer);
    }

    // 요청한 카카오 로그인 후 받은 링크의 code로 idToken발급
    @GetMapping("/oauth/kakao")
    public OauthTokenResponse getKakaoCredentialInfo(
            @RequestParam String code,
            @RequestHeader(required = false) String referer,
            @RequestHeader(required = false) String host) {

        // dev, production 환경에서
        if (referer.contains(host)) {
            log.info("/oauth/kakao" + host);
            String format = String.format("https://%s/", host);
            return authService.getCredentialFromKaKao(code, format);
        }
        return authService.getCredentialFromKaKao(code, referer);
    }

    @GetMapping("/oauth/kakao/develop")
    public OAuthResponse registerUserByCode(@RequestParam String code) {
        return authService.registerUserByKakaoCode(code);
    }

    // 받은idToken으로 카카오 서버의 사용자 정보를 사용할 수 있는지 OIDC로 인증 및 인가 받고 회원가입 처리
    @PostMapping("/oauth/kakao/register")
    public OAuthResponse registerUser(
            @RequestParam("id_token") String token,
            @Valid @RequestBody RegisterRequest registerRequest) {
        return userService.registerUserByOICDToken(token, registerRequest);
    }



    
}
