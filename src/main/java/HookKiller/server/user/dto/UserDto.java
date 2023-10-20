package HookKiller.server.user.dto;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UserDto extends AbstractTimeStamp {

    private Long id;
    private String email;
    private String nickName;
    private Timestamp createdAt;

    public static UserDto from(User user) {
        UserDto result = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .createdAt(user.getCreateAt())
                .build();
        result.setCreateAt(user.getCreateAt());
        result.setUpdateAt(user.getUpdateAt());
        return result;
    }
}
