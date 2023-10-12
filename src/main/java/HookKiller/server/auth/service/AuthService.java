package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.KakaoUserInfoDto;
import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.dto.response.OauthLoginLinkResponse;
import HookKiller.server.auth.dto.response.OauthTokenResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.auth.helper.KakaoOauthHelper;
import HookKiller.server.auth.helper.OIDCHelper;
import HookKiller.server.auth.helper.TokenGenerateHelper;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static HookKiller.server.user.type.LoginType.*;
import static HookKiller.server.user.type.UserRole.USER;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final KakaoOauthProperties kakaoOauthProperties;
  private final KakaoOauthClient kakaoOauthClient;
  private final OIDCHelper oidcHelper;
  private final KakaoOauthHelper kakaoOauthHelper;
  private final TokenGenerateHelper tokenGenerateHelper;

  private static final String KAKAO_OAUTH_QUERY_STRING =
          "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

  private static final String BEARER = "Bearer ";
  
  public ResponseEntity<AuthResponse> loginExecute(AuthRequest request) {
    User user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(()-> UserNotFoundException.EXCEPTION );
    AuthResponse res = AuthResponse.builder()
            .token(jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail(),user.getNickName(), user.getRole()))
            .build();
    return ResponseEntity.ok(res);
  }

  // 카카오 로그인을 할 수 있게 하는 링크 받기
  public OauthLoginLinkResponse getKakaoOauthLink(String referer) {
    return new OauthLoginLinkResponse(
            kakaoOauthProperties.getKakaoBaseUrl()
                    + String.format(
            KAKAO_OAUTH_QUERY_STRING,
                    kakaoOauthProperties.getKakaoClientId(),
                    kakaoOauthProperties.getKakaoRedirectUrl()
    ));
  }

  // 카카오 로그인 후 토큰 받기
  public OauthTokenResponse getCredentialFromKaKao(String code, String referer) {
    return OauthTokenResponse.from(
            kakaoOauthClient.kakaoAuth(
            kakaoOauthProperties.getKakaoClientId(),
            referer + "kakao/callback",
                    code,
                    kakaoOauthProperties.getKakaoClientSecret()
    ));
  }

  public OAuthResponse registerUserByKakaoCode(String code) {
    String accessToken = kakaoOauthHelper.getOauthToken(code).getAccessToken();

    KakaoUserInfoDto userInfo = kakaoOauthHelper.getUserInfo(accessToken);

    User user = userRepository.findByOauthInfo(userInfo.toOauthInfo()).orElseGet(
            () -> userRepository.save(
                    User.builder()
                            .oauthInfo(userInfo.toOauthInfo())
                            .email(userInfo.getEmail())
                            .nickName(userInfo.getProfile().getNickname())
                            .password(UUID.randomUUID().toString())
                            .role(USER.getValue())
                            .loginType(KAKAO)
                            .build()
            )
    );
    return tokenGenerateHelper.execute(user);
  }
}
