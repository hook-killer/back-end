package HookKiller.server.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OauthProvider {

    GOOGLE("GOOGLE"),
    KAKAO("KAKAO");

    private final String value;
}
