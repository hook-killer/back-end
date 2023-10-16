package HookKiller.server.user.dto;

import HookKiller.server.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyPageRequestDto {

    private Long userId;
    private String email;
    private String password;
    private String thumbnail;
    private String nickName;

    public static MyPageRequestDto from(User user) {
        return MyPageRequestDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .thumbnail(user.getThumbnail())
                .nickName(user.getNickName())
                .build();
    }
}
