package HookKiller.server.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OauthProvider {

    KAKAO("KAKAO");

    private final String value;
}
