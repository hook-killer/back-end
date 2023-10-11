package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.OauthLoginLinkResponse;
import HookKiller.server.auth.dto.response.OauthTokenResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.auth.helper.OIDCHelper;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final KakaoOauthProperties kakaoOauthProperties;
  private final KakaoOauthClient kakaoOauthClient;
  private final OIDCHelper oidcHelper;

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
    return new OauthLoginLinkResponse(kakaoOauthProperties.getKakaoBaseUrl() + String.format(
            KAKAO_OAUTH_QUERY_STRING, kakaoOauthProperties.getKakaoClientId(), kakaoOauthProperties.getKakaoRedirectUrl()
    ));
  }

  // 카카오 로그인 후 토큰 받기
  public OauthTokenResponse getCredentialFromKaKao(String code, String referer) {
    return OauthTokenResponse.from(kakaoOauthClient.kakaoAuth(
            kakaoOauthProperties.getKakaoClientId(),
            referer + "kakao/callback", code, kakaoOauthProperties.getKakaoClientSecret()
    ));
  }
}
