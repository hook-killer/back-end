package HookKiller.server.user.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    private String nickName;

    private String email;

    private String thumbNail;

    @Builder
    public Profile(String nickName, String thumbNail, String email) {
        this.email = email;
        this.thumbNail = thumbNail;
        this.nickName = nickName;
    }

}