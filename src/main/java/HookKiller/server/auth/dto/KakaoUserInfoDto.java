package HookKiller.server.auth.dto;

import HookKiller.server.outer.api.oauth.dto.KakaoInfoResponse;
import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfoDto {

    private final String oauthId;

    private final String email;

    private final KakaoInfoResponse.KakaoAccount.Profile profile;

    private final OauthProvider oauthProvider;

    public OauthInfo toOauthInfo() {
        return OauthInfo.builder().oid(oauthId).provider(oauthProvider).build();
    }
}
