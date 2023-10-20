package HookKiller.server.user.dto;

import HookKiller.server.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPageUserResponse {
    private final Long userId;
    private final String email;
    private final String thumbnail;
    private final String nickName;


    @Builder
    public MyPageUserResponse(Long userId, String email, String thumbnail, String nickName) {
        this.userId = userId;
        this.email = email;
        this.thumbnail = thumbnail;
        this.nickName = nickName;
    }



    public static MyPageUserResponse from(User user) {
        return MyPageUserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .thumbnail(user.getThumbnail())
                .nickName(user.getNickName())
                .build();
    }
}
