package HookKiller.server.auth.controller;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.dto.response.OauthLoginLinkResponse;
import HookKiller.server.auth.dto.response.OauthTokenResponse;
import HookKiller.server.auth.service.AuthService;
import HookKiller.server.common.service.MailHelper;
import HookKiller.server.properties.KakaoOauthProperties;
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
    private final KakaoOauthProperties kakaoOauthProperties;
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
    
    @GetMapping("/login/test")
    public boolean loginTest(@RequestParam String accessToken) {
        return authService.loginExecuteTest(accessToken);
    }
    
    @PostMapping("/logout")
    public boolean logout() {
        return true;
    }

    // kakao Oauth 링크를 프론트에 전달해줌.
    // 전달한 링크에서 클라이언트가 카카오로그인 요청
    @GetMapping("/oauth/kakao/link")
    public OauthLoginLinkResponse getKakaoLoginLink(
            @RequestHeader(required = false) String referer, // referer는 http://localhost:8080/ 과 같이 제공됨
            @RequestHeader(required = false) String host
            ) {
        log.info("link에서의 referer : {}", referer);
        if (referer != null && referer.contains(host)) {
            String format = String.format("https://%s/", host);
            return authService.getKakaoOauthLink(format); // http://localhost:8080 + /oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code
        }
        return authService.getKakaoOauthLink(referer);
    }

     //요청한 카카오 로그인 후 받은 링크의 code로 idToken발급
//    @GetMapping("/oauth/kakao")
//    public OauthTokenResponse getKakaoCredentialInfo(
//            @RequestParam String code
//            ) {
//        return authService.getCredentialFromKaKao(code, kakaoOauthProperties.getKakaoRedirectUrl());
//        log.info("token에서의 referer : {}", referer);
//        log.info("token에서의 code : {}", code);
//        log.info("token에서의 host : {}", host);
//
//        if (!referer.contains(host)) {
//            log.info("referer가 host를 포함하나? {}", referer.contains(host));
//            String format = String.format("https://%s/", host);
//            return authService.getCredentialFromKaKao(code, format); // http://localhost:8080/ + kakao/callback
//        }
//        return authService.getCredentialFromKaKao(code, referer);
//    }

    @GetMapping("/oauth/kakao")
    public OAuthResponse registerUserForTest(@RequestParam String code) {
        log.error("oauth KaKao Code >>> {}", code);
        return authService.registerUserKakaoCode(code);
    }
    
    // 받은 idToken으로 카카오 서버의 사용자 정보를 사용할 수 있는지 OIDC로 인증 및 인가 받고 회원가입 처리
    @PostMapping("/oauth/kakao/register")
    public OAuthResponse registerUser(
            @RequestParam("id_token") String token) {
        return userService.registerUserByOIDCToken(token);
    }
    
    // 받은 idToken을 이용해서 우리가 쓰는 accessToken 발급받기
    @PostMapping("/oauth/kakao/login")
    public OAuthResponse oauthLogin(@RequestParam String idToken) {
        return authService.loginUserByIdToken(idToken);
    }
}
