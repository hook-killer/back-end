package HookKiller.server.auth.helper;

import HookKiller.server.auth.dto.KakaoUserInfoDto;
import HookKiller.server.outer.api.oauth.client.KakaoInfoClient;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.outer.api.oauth.dto.KakaoInfoResponse;
import HookKiller.server.outer.api.oauth.dto.KakaoTokenResponse;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.OauthProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class KakaoOauthHelper {
  
  private final KakaoOauthProperties kakaoOauthProperties;
  
  private final KakaoOauthClient kakaoOauthClient;
  
  private final KakaoInfoClient kakaoInfoClient;
  
  public KakaoTokenResponse getOauthToken(String code) {
    log.error("과연 봉세환은 병신이되는가, 내가 병신이 되는가 >>> {} ", code);
   String kakaoClientId = kakaoOauthProperties.getKakaoClientId();
    log.info("김종원은 정상이었다11 >>> {}", kakaoClientId);
    String kakaoRedirectUrl = kakaoOauthProperties.getKakaoRedirectUrl();
    log.info("김종원은 정상이었다22 >>> {}", kakaoRedirectUrl);
    
    String kakaoProperties = kakaoOauthProperties.getKakaoClientSecret();
    log.info("김종원은 정상이었다33 >>> {}", kakaoProperties);
/*
    String path = "/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&client_secret={CLIENT_SECRET}";
    path = path.replace("{CLIENT_ID}", kakaoClientId);
    path = path.replace("{REDIRECT_URI}", kakaoRedirectUrl);
    path = path.replace("{CODE}", code);
    path = path.replace("{CLIENT_SECRET}", kakaoProperties);
    ResponseEntity<String> response = new RestTemplate().postForEntity(URI.create("https://kauth.kakao.com" + path),null, String.class);
    log.error("responseEntity >>> {}", response.toString());
    */
    return kakaoOauthClient.kakaoAuth(
            kakaoOauthProperties.getKakaoClientId(),
            kakaoOauthProperties.getKakaoRedirectUrl(),
            code,
            kakaoOauthProperties.getKakaoClientSecret()
    );
  }
  
  public KakaoUserInfoDto getUserInfo(String oauthAccessToken) {
    KakaoInfoResponse response = kakaoInfoClient.kakaoUserInfo("Bearer " + oauthAccessToken);
    
    return KakaoUserInfoDto.builder()
            .oauthProvider(OauthProvider.KAKAO)
            .oauthId(response.getId())
            .email(response.getEmail())
            .profile(response.getKakaoAccount().getProfile())
            .build();
  }
}
