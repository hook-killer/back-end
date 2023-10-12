package HookKiller.server.auth.helper;

import HookKiller.server.auth.dto.KakaoUserInfoDto;
import HookKiller.server.outer.api.oauth.client.KakaoInfoClient;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.outer.api.oauth.dto.KakaoInfoResponse;
import HookKiller.server.outer.api.oauth.dto.KakaoTokenResponse;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.OauthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class KakaoOauthHelper {

    private final KakaoOauthProperties kakaoOauthProperties;

    private final KakaoOauthClient kakaoOauthClient;

    private final KakaoInfoClient kakaoInfoClient;

    public KakaoTokenResponse getOauthToken(String code) {
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
