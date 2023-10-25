package HookKiller.server.auth.helper;

import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.auth.dto.response.KakaoUserInfoDto;
import HookKiller.server.common.dto.OIDCDto;
import HookKiller.server.outer.api.oauth.client.GoogleOauthClient;
import HookKiller.server.outer.api.oauth.client.KakaoInfoClient;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.outer.api.oauth.dto.response.*;
import HookKiller.server.properties.GoogleOauthProperties;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OauthHelper {

    private final KakaoOauthProperties kakaoOauthProperties;
    private final KakaoOauthClient kakaoOauthClient;
    private final KakaoInfoClient kakaoInfoClient;

    private final GoogleOauthProperties googleOauthProperties;
    private final GoogleOauthClient googleOauthClient;

    private final OIDCHelper oidcHelper;

    public GoogleTokenResponse getOauthTokenGoogle(String code) {
        return googleOauthClient.googleAuth(
                googleOauthProperties.getClientId(),
                googleOauthProperties.getRedirectUri(),
                code,
                googleOauthProperties.getClientSecret());
    }

    public KakaoTokenResponse getOauthTokenKakao(String code) {
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

    public OIDCUserInfo getKakaoInfoByIdToken(String idToken) {
        OIDCDto oidcDecodePayload = getOIDCDecodePayload(idToken);
        OauthInfo oauthInfo = OauthInfo.builder()
                .provider(OauthProvider.KAKAO)
                .oid(oidcDecodePayload.getSub())
                .build();
        return OIDCUserInfo.builder()
                .oauthInfo(oauthInfo)
                .email(oidcDecodePayload.getEmail())
                .nickName(oidcDecodePayload.getNickName())
                .thumbnailImg(oidcDecodePayload.getThumbnailImg())
                .build();
    }

    public KakaoUserInfoDto getKakaoUserInfo(String oauthAccessToken) {
        KakaoInfoResponse response = kakaoInfoClient.kakaoUserInfo("Bearer " + oauthAccessToken);

        return KakaoUserInfoDto.builder()
                .oauthProvider(OauthProvider.KAKAO)
                .userName(response.getNickName())
                .profileImage(response.getProfileUrl())
                .email(response.getEmail())
                .oauthId(response.getId())
                .build();
    }

    public OIDCUserInfo getGoogleInfoById(GoogleInfoResponse googleInfoResponse) {
        OauthInfo oauthInfo = OauthInfo.builder()
                .provider(OauthProvider.GOOGLE)
                .oid(googleInfoResponse.getId())
                .build();
        return OIDCUserInfo.builder()
                .oauthInfo(oauthInfo)
                .email(googleInfoResponse.getEmail())
                .name(googleInfoResponse.getName())
                .picture(googleInfoResponse.getPicture())
                .build();
    }


}
