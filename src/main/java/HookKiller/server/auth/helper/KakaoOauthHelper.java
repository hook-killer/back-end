package HookKiller.server.auth.helper;

import HookKiller.server.auth.dto.KakaoUserInfoDto;
import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.common.dto.OIDCDto;
import HookKiller.server.outer.api.oauth.client.KakaoInfoClient;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.outer.api.oauth.dto.KakaoInfoResponse;
import HookKiller.server.outer.api.oauth.dto.KakaoTokenResponse;
import HookKiller.server.outer.api.oauth.dto.OIDCResponse;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KakaoOauthHelper {
  
  private final KakaoOauthProperties kakaoOauthProperties;
  
  private final KakaoOauthClient kakaoOauthClient;
  
  private final KakaoInfoClient kakaoInfoClient;
  private final OIDCHelper oidcHelper;
  
  public KakaoTokenResponse getOauthToken(String code, String referer) {
    log.info("referer 1 : {}", referer);
    return kakaoOauthClient.kakaoAuth(
            kakaoOauthProperties.getKakaoClientId(),
            referer ,
            code,
            kakaoOauthProperties.getKakaoClientSecret()
    );
  }

  public KakaoTokenResponse getOauthTokenTest(String code) {

    return kakaoOauthClient.kakaoAuth(
            kakaoOauthProperties.getKakaoClientId(),
            kakaoOauthProperties.getKakaoRedirectUrl(),
            code,
            kakaoOauthProperties.getKakaoClientSecret());
  }
  
  public OIDCDto getOIDCDecodePayload(String token) {
    OIDCResponse oidcResponse = kakaoOauthClient.getKakaoOIDCOpenKeys();
    return oidcHelper.getPayloadFromIdToken(
            token,
            kakaoOauthProperties.getKakaoBaseUrl(),
            kakaoOauthProperties.getKakaoClientId(),
            oidcResponse);
  }
  
  public OIDCUserInfo getOauthInfoByIdToken(String idToken) {
    OIDCDto oidcDecodePayload = getOIDCDecodePayload(idToken);
    OauthInfo oauthInfo = OauthInfo.builder()
            .provider(OauthProvider.KAKAO)
            .oid(oidcDecodePayload.getSub())
            .build();
    return OIDCUserInfo.builder()
            .oauthInfo(oauthInfo)
            .email(oidcDecodePayload.getEmail())
            .nickName(oidcDecodePayload.getKakaoNickName())
            .thumbnailImg(oidcDecodePayload.getKakaoThumbnailImg())
            .build();
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
