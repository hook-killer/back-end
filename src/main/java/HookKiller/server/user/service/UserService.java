package HookKiller.server.user.service;

import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.helper.KakaoOauthHelper;
import HookKiller.server.auth.helper.OIDCHelper;
import HookKiller.server.auth.helper.TokenGenerateHelper;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.RefreshTokenRepository;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final OIDCHelper oidcHelper;
    private final KakaoOauthProperties kakaoOauthProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final KakaoOauthClient kakaoOauthClient;
    
    @Transactional
    public ResponseEntity<User> registerUser(@RequestBody SingUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw AlreadyExistUserException.EXCEPTION;
        }
        
        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickName(request.getNickName())
                .role(UserRole.valueOf(request.getRole()))
                .loginType(LoginType.DEFAULT)
                .build());
        
        return ResponseEntity.ok(user);
    }
    
    @Transactional
    public OAuthResponse registerUserByOIDCToken(String idToken) {
        OIDCUserInfo oidcUserInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
//        KakaoUserInfoDto kakaoUserInfoDto = kakaoOauthHelper.getUserInfo(idToken);
        userRepository.findByEmail(oidcUserInfo.getEmail()).ifPresent(user -> {
            throw AlreadyExistUserException.EXCEPTION;
        });
        return tokenGenerateHelper.execute(userRepository.save(User.builder()
                .email(oidcUserInfo.getEmail())
                .password("testtesttest")
                .nickName(oidcUserInfo.getNickName())
                .thumbnail(oidcUserInfo.getThumbnailImg())
                .loginType(LoginType.DEFAULT)
                .role(UserRole.valueOf("USER"))
                .oauthInfo(oidcUserInfo.getOauthInfo())
                .build()));
    }
}
