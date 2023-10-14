package HookKiller.server.user.service;

import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.helper.KakaoOauthHelper;
import HookKiller.server.auth.helper.OIDCHelper;
import HookKiller.server.auth.helper.TokenGenerateHelper;
import HookKiller.server.common.dto.OIDCDto;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.outer.api.oauth.dto.OIDCResponse;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import HookKiller.server.user.entity.RefreshTokenEntity;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.RefreshTokenRepository;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.LoginType;
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
                .role(request.getRole())
                .loginType(LoginType.DEFAULT)
                .build());
        
        return ResponseEntity.ok(user);
    }

    // OauthInfo를 저장하는 유저 디비에 넣기
    public User registerUserWithOauthInfo(OauthInfo oauthInfo, SingUpRequest singUpRequest) {
        // User(String email, String password, String nickName, String role, LoginType loginType, OauthInfo oauthInfo)
        User user = User.builder()
                .email("bongsh0112@naver.com")
                .password("basdf")
                .nickName("nickname")
                .role("ROLE_USER")
                .loginType(LoginType.DEFAULT)
                .oauthInfo(oauthInfo)
                .build();
        return userRepository.save(user);
    }

    // Oauth 회원가입 시 OAuthResponse 리턴하는 메소드
    public OAuthResponse registerUserByOIDCToken(String idToken, SingUpRequest singUpRequest) {
        OIDCResponse oidcResponse = kakaoOauthClient.getKakaoOIDCOpenKeys();
        OIDCDto oidcDto = oidcHelper.getPayloadFromIdToken(idToken, kakaoOauthProperties.getKakaoBaseUrl(), kakaoOauthProperties.getKakaoAppId(), oidcResponse);
        OauthInfo oauthInfo = OauthInfo.builder()
                .provider(OauthProvider.KAKAO)
                .oid(oidcDto.getSub())
                .build();
        User user = registerUserWithOauthInfo(oauthInfo, singUpRequest);
        
        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getRole().getValue());
        
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());
        
        RefreshTokenEntity newRefreshTokenEntity =
                RefreshTokenEntity.builder()
                        .refreshToken(newRefreshToken)
                        .id(user.getId())
                        .ttl(jwtTokenProvider.getRefreshTokenTTLSecond())
                        .build();
        refreshTokenRepository.save(newRefreshTokenEntity);
        
        return OAuthResponse.builder()
                .userId(user.getId())
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
    @Transactional
    public User registerUserByOIDCToken(String idToken) {
        OIDCUserInfo oidcUserInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
//        KakaoUserInfoDto kakaoUserInfoDto = kakaoOauthHelper.getUserInfo(idToken);
        return userRepository.save(User.builder()
                        .email(oidcUserInfo.getEmail())
                        .password("testtesttest")
                        .nickName(oidcUserInfo.getNickName())
                        .thumbnail(oidcUserInfo.getThumbnailImg())
                        .loginType(LoginType.DEFAULT)
                        .role("USER")
                        .oauthInfo(oidcUserInfo.getOauthInfo())
                        .build());
    }
}
