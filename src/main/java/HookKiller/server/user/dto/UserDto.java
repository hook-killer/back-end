package HookKiller.server.user.dto;

import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String nickName;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .build();
    }
}
