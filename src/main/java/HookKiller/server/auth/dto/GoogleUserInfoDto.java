package HookKiller.server.auth.dto;

import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleUserInfoDto {

    private String sub;

    private String email;

    private String name;

    private String picture;

    private OauthProvider oauthProvider;

    @Builder
    public GoogleUserInfoDto(String sub, String email, String name, String picture, OauthProvider oauthProvider) {
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.oauthProvider = oauthProvider;
    }

    public OauthInfo toOauthInfo() {
        return OauthInfo.builder()
                .provider(OauthProvider.GOOGLE)
                .oid(sub)
                .build();
    }
}
