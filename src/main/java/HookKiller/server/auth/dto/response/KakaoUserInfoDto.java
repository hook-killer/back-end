package HookKiller.server.auth.dto.response;

import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import HookKiller.server.user.entity.Profile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfoDto {

    private final String oauthId;

    private final String email;
    private final String profileImage;
    private final String userName;
    // oauth 제공자
    private final OauthProvider oauthProvider;

    public Profile toProfile() {
        return Profile.builder()
                .thumbNail(this.profileImage)
                .nickName(userName)
                .email(email)
                .build();
    }

    public OauthInfo toOauthInfo() {
        return OauthInfo.builder().oid(oauthId).provider(oauthProvider).build();
    }
}
